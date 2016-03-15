package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.controller.LoginController;
import travel.com.model.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@SuppressWarnings(
{ "unchecked", "unused" })
public class ConsumerDAOImpl implements ConsumerDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String STATUS_BOOKED = "booked";

	final String CONSUMER_ROLE = "ROLE_CUSTOMER";

	final String DEFAULT_TRIP_IMAGE_PATH =
			"defaulttripimage/default_tripimage.jpg";

	final String GET_PROFILE_BASED_USERID =
			"Select * from users where id =:userId AND role =:role";

	final String UPDATE_CONSUMER_PROFILE_DATA =
			"Update users set firstname =:firstname,  lastname =:lastname, city =:city where id =:id";

	final String GET_CONSUMER_TRIP_DETAILS =
			"SELECT b.id, b.status, td.title, td.id AS id, td.description AS description, td.guidelines AS guidelines, "
					+ "IF(SUBSTRING_INDEX(GROUP_CONCAT(ti.name), ',', 1) IS NULL, :defaultImage, "
					+ "SUBSTRING_INDEX(GROUP_CONCAT(ti.name), ',', 1))AS imageurls, "
					+ "IFNULL(r.reviewscount, 0) AS reviewscount, IFNULL(v.viwerscount, 0) AS viwerscount, "
					+ "DATE_FORMAT(td.fromdate, '%b %d, %Y') AS fromdate, a.name AS activityname "
					+ "FROM booking b "
					+ "INNER JOIN (SELECT title, createdat, description, guidelines, fromdate, activityid, id FROM tripdetails) AS td "
					+ "ON td.id = b.tripid "
					+ "INNER JOIN activity a ON td.activityid = a.id "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS reviewscount FROM reviews GROUP BY tripid) AS r "
					+ "ON r.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS viwerscount FROM viewers GROUP BY tripid) AS v "
					+ "ON v.tripid = td.id "
					+ "LEFT OUTER JOIN(SELECT * FROM tripimages WHERE STATUS =:status) AS ti ON td.id = ti.tripid "
					+ "WHERE "
					+ "b.consumerid =:consumerid  AND b.status =:status  "
					+ "GROUP BY b.id, td.description, "
					+ "td.guidelines, td.createdat, a.name "
					+ "ORDER BY td.createdat DESC LIMIT :startIndx, :maxIndx";

	final String GET_CONSUMER_TRIPS_NUMENTRIES =
			"Select count(*) from booking where consumerid =:consumerid AND status =:status";
	final String ADD_SAVED_TRIPS =
			"INSERT IGNORE INTO savedtrips (userid, tripid) values(:userid, :tripid)";
	final String GET_SAVED_TRIPS_NUMENTRIES =
			"SELECT count(*) from savedtrips where userid =:userId AND status =:status";
	final String GET_SAVED_TRIPS =
			"SELECT s.*, td.title,td.description, td.price, td.duration, td.guidelines, IFNULL(r.reviewscount, 0) AS reviewscount, IFNULL(v.viwerscount, 0) AS viewrscount, "
					+ "IF(ti.name IS NULL, '', ti.name) AS tripimagename, DATE_FORMAT(td.fromdate, '%b %d, %Y') AS dateformat FROM savedtrips s "
					+ "INNER JOIN tripdetails td "
					+ "ON td.id = s.tripid "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS reviewscount FROM reviews GROUP BY tripid) AS r "
					+ "ON r.tripid = s.tripid "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS viwerscount FROM viewers GROUP BY tripid) AS v "
					+ "ON v.tripid = s.tripid "
					+ "LEFT OUTER JOIN (SELECT IFNULL(t.name, i.name) AS NAME, i.tripid, IFNULL(t.imagetype, i.imagetype) FROM tripimages i "
					+ "LEFT OUTER JOIN "
					+ "( "
					+ "SELECT NAME, imagetype, tripid FROM tripimages WHERE imagetype = 'coverimage') AS t "
					+ "ON i.tripid = t.tripid GROUP BY i.tripid) AS ti ON td.id = ti.tripid "
					+ "WHERE td.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d' "
					+ ") "
					+ "AND td.status ='active' AND s.userid =:userId ORDER BY s.created_at DESC LIMIT :startIndx, :endIndx";

	public List<Login> getProfile(int userId) throws Exception
	{
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("role", CONSUMER_ROLE);

		List<Login> list =
				namedParameterJdbcTemplate.query(GET_PROFILE_BASED_USERID, map,
						new BeanPropertyRowMapper(Login.class));

		return list;
	}

	@Override
	public void updateConsumerProfile(Login login) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("firstname", login.getFirstname());
		paramMap.put("lastname", login.getLastname());
		paramMap.put("city", login.getCity());
		paramMap.put("id", login.getId());

		namedParameterJdbcTemplate.update(UPDATE_CONSUMER_PROFILE_DATA,
				paramMap);

	}

	public List<Trip> getConsumerTripDetails(Booking booking)
	{
		List<Trip> list = null;
		Map paramMap = new HashMap();
		paramMap.put("status", STATUS_BOOKED);
		paramMap.put("consumerid", booking.getVendorid());
		paramMap.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);
		paramMap.put("startIndx", booking.getStartIndx());
		paramMap.put("maxIndx", booking.getEndIndx());
		list =
				namedParameterJdbcTemplate.query(GET_CONSUMER_TRIP_DETAILS,
						paramMap, new BeanPropertyRowMapper(Trip.class));
		return list;
	}

	public int getConsumerTripDetailsNumEntries(Booking booking)
	{

		Map paramMap = new HashMap();
		paramMap.put("consumerid", booking.getVendorid());
		paramMap.put("status", booking.getStatus());

		return namedParameterJdbcTemplate.queryForInt(
				GET_CONSUMER_TRIPS_NUMENTRIES, paramMap);

	}

	@Override
	public void addSavedTrips(int userId, int tripId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", userId);
		paramMap.put("tripid", tripId);

		namedParameterJdbcTemplate.update(ADD_SAVED_TRIPS, paramMap);

	}

	@Override
	public List<SavedTrips> getSavedTrips(int userId, String sTATTUS_SAVED,
			int startIndx, int endIndx) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		paramMap.put("status", sTATTUS_SAVED);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", endIndx);
		return namedParameterJdbcTemplate.query(GET_SAVED_TRIPS, paramMap,
				new BeanPropertyRowMapper(SavedTrips.class));

	}

	@Override
	public int getSavedTripNumEntries(int userId, String sTATTUS_SAVED)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		paramMap.put("status", sTATTUS_SAVED);

		return namedParameterJdbcTemplate.queryForInt(
				GET_SAVED_TRIPS_NUMENTRIES, paramMap);

	}

}
