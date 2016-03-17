package travel.com.dao;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.*;
import org.springframework.jdbc.core.namedparam.*;

import travel.com.model.*;

@SuppressWarnings(
{ "unchecked", "unused" })
public class TripDAOImpl implements TripDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String DEFAULT_TRIP_IMAGE_PATH =
			"defaulttripimage/default_tripimage.jpg";
	final String ACTIVITY_ID_ATTRIBUTE = "activityid";
	final String SUB_ACTIVITY_ID_ATTRIBUTE = "subactivityid";

	final String INSERT_TRIP_DETAILS =
			"INSERT into tripdetails (userid, locationid, activityid, duration, fromdate, todate, startpoint, route, description, guidelines, title, price, subactivityid"
					+ ") VALUES (:userid, :locationid, :activityid, :duration, :fromdate, :todate, :startpoint, :route, :description, "
					+ ":guidelines, :title, :price, :subactivityid)";

	// USER BASED
	final String GET_TRIP_DETAILS =
			"SELECT td.title, td.id AS id, td.description AS description, td.guidelines AS guidelines, IF(SUBSTRING_INDEX(GROUP_CONCAT(ti.name), ',', 1) IS NULL , :defaultImage, "
					+ "SUBSTRING_INDEX(GROUP_CONCAT(ti.name), ',', 1))AS imageurls, "
					+ "IFNULL(r.reviewscount, 0) AS reviewscount, IFNULL(v.viwerscount, 0) AS viwerscount, "
					+ "DATE_FORMAT(td.fromdate, '%b %d, %Y') AS fromdate, a.name AS activityname "
					+ "FROM tripdetails td "
					+ "INNER JOIN activity a ON td.activityid = a.id "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS reviewscount FROM reviews GROUP BY tripid) AS r "
					+ "ON r.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS viwerscount FROM viewers GROUP BY tripid) AS v "
					+ "ON v.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT IFNULL(t.name, i.name) AS NAME, i.tripid, IFNULL(t.imagetype, i.imagetype) FROM tripimages i "
					+ "LEFT OUTER JOIN(SELECT name, imagetype, tripid FROM tripimages WHERE imagetype = 'coverimage' AND STATUS ='active') AS t "
					+ "ON i.tripid = t.tripid GROUP BY i.tripid) AS ti ON td.id = ti.tripid "
					// +
					// "LEFT OUTER JOIN(SELECT * FROM tripimages WHERE STATUS =:status ORDER BY imagetype) AS ti ON td.id = ti.tripid "
					+ "where "
					+ "td.status =:status and td.userid =:userId "
					+ "GROUP BY td.description, td.guidelines, td.createdat, a.name "
					+ "ORDER BY td.createdat DESC Limit :startIndx, :endIndx";

	final String GET_TOTAL_TRIPS =
			"Select count(*) from tripdetails where status =:status and userid =:userId";

	final String GET_EDIT_TRIPS_DETAILS =
			"SELECT td.id AS id, td.locationid AS locationid, td.startpoint AS startpoint, td.fromdate AS fromdate, td.todate AS todate, td.description AS description, td.guidelines AS guidelines, "
					+ "i.id AS itenaryid, i.day AS itenaryday, i.daywisedescription AS daysdesc, td.price AS price, "
					+ "fc.city AS fromcity, tc.city AS tocity, td.route AS route, td.duration AS duration, "
					+ "DATE_FORMAT(td.createdat, '%b %d, %Y') AS createdat, a.name AS activityname, "
					+ "sa.name AS subactivityname "
					+ "FROM tripdetails td "
					+ "INNER JOIN activity a ON td.activityid = a.id "
					+ "INNER JOIN subactivity sa ON td.subactivityid = sa.id "
					+ "INNER JOIN itenary i ON td.id = i.tripid "
					+ "INNER JOIN city fc ON td.startpoint = fc.id "
					+ "INNER JOIN city tc ON td.locationid = tc.id "
					+ "where "
					+ "td.status =:status and td.userid =:userId AND td.id =:tripId "
					+ "ORDER BY td.createdat DESC";

	final String UPDATE_TRIP_DETAILS =
			"UPDATE tripdetails set status =:status, locationid =:locationid, activityid =:activityid, duration =:duration, "
					+ "fromdate =:fromdate, todate =:todate, startpoint =:startpoint, route =:route, description =:description, "
					+ "guidelines =:guidelines, title =:title, price =:price, subactivityid =:subactivityid where id =:id and userid =:userId";

	final String UPDATE_TRIP_DETAILS_STATUS =
			"UPDATE tripdetails set status =:status where userid =:userid AND id =:id";

	final String GET_FILTERED_TRIP_DETAILS =
			"SELECT td.*, IFNULL(r.reviewscount, 0) AS reviewscount, IFNULL(v.viwerscount, 0) AS viwerscount, IF(ti.name IS NULL, :defaultImage, ti.name) AS tripimagename, DATE_FORMAT(td.fromdate, '%b %d, %Y') AS dateformat FROM tripdetails td "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS reviewscount FROM reviews GROUP BY tripid) AS r "
					+ "ON r.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS viwerscount FROM viewers GROUP BY tripid) AS v "
					+ "ON v.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT IFNULL(t.name, i.name) AS NAME, i.tripid, IFNULL(t.imagetype, i.imagetype) FROM tripimages i "
					+ "LEFT OUTER JOIN(SELECT name, imagetype, tripid FROM tripimages WHERE imagetype = 'coverimage') AS t "
					+ "ON i.tripid = t.tripid GROUP BY i.tripid) AS ti ON td.id = ti.tripid "
					// +
					// "LEFT OUTER JOIN (SELECT * FROM tripimages WHERE STATUS =:status  GROUP BY tripid ORDER BY imagetype) AS ti  ON td.id = ti.tripid "
					+ "WHERE td.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d')  AND ";

	final String GET_FILTERED_TRIP_DETAILS_NUMENTRIES =
			"Select count(*) from tripdetails td where td.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d') ";

	// NON USER BASED
	final String GET_ALL_TRIP_DETAILS =
			"SELECT td.*, IFNULL(r.reviewscount, 0) AS reviewscount, IFNULL(v.viwerscount, 0) AS viwerscount, IF(ti.name IS NULL, :defaultImage, ti.name) AS tripimagename, DATE_FORMAT(td.fromdate, '%b %d, %Y') AS dateformat FROM tripdetails td "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS reviewscount FROM reviews GROUP BY tripid) AS r "
					+ "ON r.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT tripid, COUNT(*) AS viwerscount FROM viewers GROUP BY tripid) AS v "
					+ "ON v.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT IFNULL(t.name, i.name) AS NAME, i.tripid, IFNULL(t.imagetype, i.imagetype) FROM tripimages i "
					+ "LEFT OUTER JOIN(SELECT name, imagetype, tripid FROM tripimages WHERE imagetype = 'coverimage') AS t "
					+ "ON i.tripid = t.tripid GROUP BY i.tripid) AS ti ON td.id = ti.tripid "
					// +
					// "LEFT OUTER JOIN (SELECT * FROM tripimages WHERE STATUS =:status  GROUP BY tripid ORDER BY imagetype) AS ti ON td.id = ti.tripid "
					+ "WHERE td.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d') AND td.status =:status ORDER BY td.createdat DESC LIMIT :startIndx, :endIndx ";

	final String GET_ALL_TRIPDETAILS_NUMENTRIES =
			"Select count(*) from tripdetails where fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d') AND status =:status";

	final String GET_TRIP_DETAILS_BASED_ID =
			"SELECT u.id AS userid, u.*, td.*, td.id AS tripid, IFNULL(r.startrating, 0) AS startrating, td.price, (td.price - (td.price * (IFNULL(d.offer_percentage, 0)/100))) AS offer_percentage, c.city AS tocity, IFNULL(ti.tripimagename, :defaultImage) AS tripimagename, GROUP_CONCAT(it.daywisedescription) AS daysdesc, "
					+ "DATE_FORMAT(td.fromdate, '%b %d, %Y') AS dateformat, DATE_FORMAT(td.todate, '%b %d, %Y') AS todateformat,  "
					+ " IFNULL(v.views, 0) AS views, IFNULL(f.favourites, 0) AS favourites "
					+ " FROM tripdetails td "
					+ " INNER JOIN (SELECT * FROM itenary ORDER BY DAY) AS it ON it.tripid = td.id "
					+ "INNER JOIN city c ON c.id = td.locationid "
					+ "INNER JOIN users u "
					+ "ON "
					+ "u.id = td.userid "
					+ " LEFT OUTER JOIN (SELECT tripid, GROUP_CONCAT(NAME) AS tripimagename FROM tripimages WHERE STATUS =:status GROUP BY tripid) AS ti ON ti.tripid = td.id "
					+ "LEFT OUTER JOIN (SELECT tripid,COUNT(*) AS views FROM viewers WHERE STATUS = 'viewed' GROUP BY tripid) v ON td.id = v.tripid "
					+ " LEFT OUTER JOIN (SELECT tripid,COUNT(*) AS favourites FROM viewers WHERE STATUS = 'liked' GROUP BY tripid) f ON td.id = f.tripid "
					+ "LEFT OUTER JOIN (SELECT t.userid, COUNT(*) AS reviews, AVG(startrating) AS startrating FROM reviews rs "
					+ "INNER JOIN "
					+ "tripdetails t "
					+ "ON  "
					+ "t.id = rs.tripid "
					+ "GROUP BY t.userid ) r "
					+ "ON td.userid = r.userid "
					+ "LEFT OUTER JOIN  (SELECT tripid, offer_percentage AS offer_percentage FROM deals) d ON td.id = d.tripid "
					+ "WHERE td.id =:id";
	final String GET_CREDITS_BASED_TRIPID =
			"Select u.email, u.credits FROM users u "
					+ "INNER JOIN tripdetails "
					+ "td ON u.id = td.userid WHERE td.id =:id";
	final String GET_TRIPDETAILS_AND_TITLE =
			"Select title, id from tripdetails where fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d') AND status =:status AND userid =:userid AND title like :startLetter";
	final String GET_ALL_TRIPDETAILS =
			"Select title, id from tripdetails where status =:status AND userid =:userid AND title like :startLetter";
	final String GET_TOP_ACTIVITYS =
			"SELECT SUBSTRING_INDEX(GROUP_CONCAT(IFNULL(tm.name, :defaultImage)), ',', 1) AS defaultImage, a.id, t.title FROM (SELECT tripid AS id, 'a' AS titles FROM admin_topactivity "
					+ "UNION "
					+ "SELECT id, 'b' AS titles FROM tripdetails) AS a "
					+ "INNER JOIN tripdetails t "
					+ "ON t.id = a.id "
					+ "LEFT OUTER JOIN "
					+ "tripimages tm "
					+ "ON tm.tripid = a.id "
					+ "GROUP BY a.id "
					+ "ORDER BY a.titles " + "LIMIT :visibleTopActivitys";
	final String UPDATE_TRIP_CONVER_IMAGE =
			"Update tripimages set imagetype =:iMAGE_TYPE where id =:id and tripid =:tripId";
	final String UPDATE_TRIP_CONVER_IMAGE_VIA_TRIPID =
			"Update tripimages set imagetype =:iMAGE_TYPE where id <>:id and tripid =:tripId";

	public Long addTripDetails(Trip trip) throws Exception
	{

		MapSqlParameterSource parameters =
				new MapSqlParameterSource()
						.addValue("userid", trip.getUserid())
						.addValue("locationid", trip.getLocationid())
						.addValue("activityid", trip.getActivityid())
						.addValue("duration", trip.getDuration())
						.addValue("fromdate", trip.getFromdate())
						.addValue("todate", trip.getTodate())
						.addValue("startpoint", trip.getStartpoint())
						.addValue("route", trip.getRoute())
						.addValue("description", trip.getDescription())
						.addValue("guidelines", trip.getGuidelines())
						.addValue("price", trip.getPrice())
						.addValue("title", trip.getTitle())
						.addValue("subactivityid", trip.getSubactivityid());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(INSERT_TRIP_DETAILS, parameters,
				keyHolder, new String[]
				{ "id" });
		return (Long) keyHolder.getKey();
	}

	public Map<String, Object> getTripDetails(Trip trip) throws Exception
	{
		Map map = new HashMap();
		map.put("status", trip.getStatus());
		map.put("startIndx", trip.getStartIndx());
		map.put("endIndx", trip.getEndIndx());
		map.put("userId", trip.getUserid());
		map.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);

		List<Trip> list =
				namedParameterJdbcTemplate.query(GET_TRIP_DETAILS, map,
						new BeanPropertyRowMapper(Trip.class));
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("trips", list);
		listMap.put("numEntries",
				getTotalTrips(trip.getUserid(), trip.getStatus()));
		return listMap;
	}

	private int getTotalTrips(int userId, String status) throws Exception
	{
		Map map = new HashMap();
		map.put("status", status);
		map.put("userId", userId);

		return namedParameterJdbcTemplate.queryForInt(GET_TOTAL_TRIPS, map);
	}

	public List<Trip> getUpdatingTripDetailsBasedId(Trip trip) throws Exception
	{
		Map map = new HashMap();
		map.put("status", trip.getStatus());
		map.put("userId", trip.getUserid());
		map.put("tripId", trip.getId());
		List<Trip> list =
				namedParameterJdbcTemplate.query(GET_EDIT_TRIPS_DETAILS, map,
						new BeanPropertyRowMapper(Trip.class));

		return list;

	}

	public void update(Trip trip) throws Exception
	{
		Map map = new HashMap();
		map.put("locationid", trip.getLocationid());
		map.put("activityid", trip.getActivityid());
		map.put("duration", trip.getDuration());
		map.put("fromdate", trip.getFromdate());
		map.put("todate", trip.getTodate());
		map.put("startpoint", trip.getStartpoint());
		map.put("route", trip.getRoute());
		map.put("description", trip.getDescription());
		map.put("guidelines", trip.getGuidelines());
		map.put("title", trip.getTitle());
		map.put("id", trip.getId());
		map.put("userId", trip.getUserid());
		map.put("price", trip.getPrice());
		map.put("subactivityid", trip.getSubactivityid());

		// SET STATUS DEACTIVE
		map.put("status", "deactive");

		namedParameterJdbcTemplate.update(UPDATE_TRIP_DETAILS, map);
	}

	public void updateTripDetailStatus(Trip trip) throws Exception
	{
		Map map = new HashMap();
		map.put("status", trip.getStatus());
		map.put("userid", trip.getUserid());
		map.put("id", trip.getId());

		namedParameterJdbcTemplate.update(UPDATE_TRIP_DETAILS_STATUS, map);
	}

	public List<Trip> getFilterTripsDetails(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap, int START_INDEX, int END_INDEX)
	{
		boolean isFilterValues = false;
		Map map = new HashMap();
		map.put("status", status);
		map.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);
		map.put("startIndx", START_INDEX);
		map.put("endIndx", END_INDEX);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GET_FILTERED_TRIP_DETAILS);
		stringBuffer.append(" td.status=:status");

		if (_setActivityFilterValuesForPrice(priceMap) != null
				&& _setActivityFilterValuesForPrice(priceMap).length() > 0)
		{
			isFilterValues = true;
			stringBuffer.append(" AND ");
			stringBuffer.append(" ( ");
			stringBuffer.append(_setActivityFilterValuesForPrice(priceMap));
		}

		if (_setFilterActivityId(activityTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer.append(_setFilterActivityId(activityTable));
		}
		if (_setActivityFilterValuesForAllFields(tripTable) != null
				&& _setActivityFilterValuesForAllFields(tripTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer
					.append(_setActivityFilterValuesForAllFields(tripTable));
		}
		if (isFilterValues)
		{
			stringBuffer.append(" ) ");
		}
		stringBuffer.append(" ");
		stringBuffer.append(" LIMIT ");
		stringBuffer.append(" :startIndx ");
		stringBuffer.append(" , ");
		stringBuffer.append(" :endIndx ");

		return namedParameterJdbcTemplate.query(stringBuffer.toString(), map,
				new BeanPropertyRowMapper(Trip.class));

	}

	public int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap)

	{

		Map map = new HashMap();
		map.put("status", status);
		boolean isFilterValues = false;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GET_FILTERED_TRIP_DETAILS_NUMENTRIES);
		stringBuffer.append(" AND status=:status");

		if (_setActivityFilterValuesForPrice(priceMap) != null
				&& _setActivityFilterValuesForPrice(priceMap).length() > 0)
		{
			isFilterValues = true;
			stringBuffer.append(" AND ");
			stringBuffer.append(" ( ");
			stringBuffer.append(_setActivityFilterValuesForPrice(priceMap));
		}
		if (_setFilterActivityId(activityTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer.append(_setFilterActivityId(activityTable));
		}
		if (_setActivityFilterValuesForAllFields(tripTable) != null
				&& _setActivityFilterValuesForAllFields(tripTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer
					.append(_setActivityFilterValuesForAllFields(tripTable));
		}
		if (isFilterValues)
		{
			stringBuffer.append(" ) ");
		}

		return namedParameterJdbcTemplate.queryForInt(stringBuffer.toString(),
				map);

	}

	// TO-DO
	// Activity
	// Id's
	private String _setFilterActivityId(Map<String, Object> activityTable)
	{
		StringBuffer stringBuffer = new StringBuffer("");
		if (activityTable != null && activityTable.size() > 0)
		{
			for (Map.Entry<String, Object> entry : activityTable.entrySet())
			{
				stringBuffer.append("( ");
				stringBuffer.append("td." + ACTIVITY_ID_ATTRIBUTE);
				stringBuffer.append(" IN ");
				stringBuffer.append("( ");
				stringBuffer.append(entry.getValue());
				stringBuffer.append(" )");
				stringBuffer.append(" )");
			}
		}
		return stringBuffer.toString();
	}

	private
			String
			_setFilterSubActivityId(Map<String, Object> subActivityTable)
	{
		StringBuffer stringBuffer = new StringBuffer("");
		if (subActivityTable != null && subActivityTable.size() > 0)
		{
			for (Map.Entry<String, Object> entry : subActivityTable.entrySet())
			{
				stringBuffer.append("( ");
				stringBuffer.append("td." + SUB_ACTIVITY_ID_ATTRIBUTE);
				stringBuffer.append(" IN ");
				stringBuffer.append("( ");
				stringBuffer.append(entry.getValue());
				stringBuffer.append(" )");
				stringBuffer.append(" )");
			}
		}
		return stringBuffer.toString();
	}

	private
			String
			_setActivityFilterValuesForPrice(Map<String, Object> priceMap) // TO_DO
	// Price
	{
		StringBuffer stringBuffer = new StringBuffer("");
		if (priceMap != null && priceMap.size() > 0)
		{
			stringBuffer.append("( ");
			stringBuffer.append("td.price");
			stringBuffer.append(" >= ");
			stringBuffer.append(priceMap.get("fromprice"));
			stringBuffer.append(" AND ");
			stringBuffer.append("td.price");
			stringBuffer.append(" <= ");
			stringBuffer.append(priceMap.get("toprice"));
			stringBuffer.append(" )");
		}
		return stringBuffer.toString();
	}

	private String _setActivityFilterValuesForAllFields(
			Map<String, Object> tripCommonFields)
	{
		StringBuffer stringBuffer = new StringBuffer("");
		String[] values =
		{ "fromdate" }; // This is string type
		List containsStringValues = Arrays.asList(values);
		if (tripCommonFields != null && tripCommonFields.size() > 0)
		{
			stringBuffer.append("( ");
			for (Map.Entry<String, Object> entry : tripCommonFields.entrySet())
			{

				if (containsStringValues.contains(entry.getKey()))
				{
					stringBuffer.append("DATE_FORMAT(td." + entry.getKey()
							+ ", '%Y-%m')");
					stringBuffer.append(" = ");
					stringBuffer.append("\'" + entry.getValue() + "\'");
				} else
				{
					stringBuffer.append("td." + entry.getKey());
					stringBuffer.append(" = ");
					stringBuffer.append(entry.getValue());
				}
				stringBuffer.append(" OR ");
			}
			stringBuffer
					.replace(
							0,
							stringBuffer.length(),
							stringBuffer.substring(0,
									stringBuffer.lastIndexOf(" OR ")));
			stringBuffer.append(" )");
		}
		return stringBuffer.toString();
	}

	public List<Trip> getTripDetails_Users(Trip trip) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "status", "startIndx", "endIndx" };
		List<Trip> list = null;
		try
		{
			baseDAO.setNamedParameter(trip, params, map);
			map.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);
			list =
					namedParameterJdbcTemplate.query(GET_ALL_TRIP_DETAILS, map,
							new BeanPropertyRowMapper(Trip.class));
		} catch (Exception ex)
		{
			throw ex;
		}
		return list;
	}

	public int getAllValidTripDetailsCount(Trip trip) throws Exception
	{
		Map paramMap = new HashMap();
		String[] param =
		{ "status" };
		int numEntries = 0;
		try
		{
			baseDAO.setNamedParameter(trip, param, paramMap);
			numEntries =
					namedParameterJdbcTemplate.queryForInt(
							GET_ALL_TRIPDETAILS_NUMENTRIES, paramMap);
		} catch (Exception ex)
		{
			throw ex;
		}
		return numEntries;
	}

	public List<Trip> getTripDetailsBasedId(Trip trip) throws Exception
	{
		Map paramMap = new HashMap();
		String[] param =
		{ "status", "id" };
		List<Trip> list = null;
		try
		{
			baseDAO.setNamedParameter(trip, param, paramMap);
			paramMap.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);
			list =
					namedParameterJdbcTemplate.query(GET_TRIP_DETAILS_BASED_ID,
							paramMap, new BeanPropertyRowMapper(Trip.class));
		} catch (Exception ex)
		{
			throw ex;
		}
		return list;
	}

	@Override
	public List<Trip> getCredits_AND_Email(int tripId) throws Exception
	{
		Map map = new HashMap();
		List<Trip> list = null;
		try
		{
			map.put("id", tripId);
			list =
					namedParameterJdbcTemplate.query(GET_CREDITS_BASED_TRIPID,
							map, new BeanPropertyRowMapper(Trip.class));

		} catch (Exception ex)
		{
			throw ex;
		}
		return list;
	}

	public List<Trip> getTripDetailsTitles_AND_Id(int userId, String status,
			String startTitle) throws Exception
	{
		Map map = new HashMap();
		map.put("userid", userId);
		map.put("status", status);
		map.put("startLetter", startTitle + "%");

		List<Trip> list = null;
		list =
				namedParameterJdbcTemplate.query(GET_TRIPDETAILS_AND_TITLE,
						map, new BeanPropertyRowMapper(Trip.class));
		return list;
	}

	@Override
	public List<Trip> getAllTripDetails(int userId, String sTATUS_ACTIVE,
			String startTitle) throws Exception
	{
		Map map = new HashMap();
		map.put("userid", userId);
		map.put("status", sTATUS_ACTIVE);
		map.put("startLetter", startTitle + "%");

		return namedParameterJdbcTemplate.query(GET_ALL_TRIPDETAILS, map,
				new BeanPropertyRowMapper(Trip.class));
	}

	public List<Trip> getFilterTripsDetails(Map<String, Object> tripTable,
			Map<String, Object> activityTable,
			Map<String, Object> subActivityTable, String status,
			Map<String, Object> priceMap, int START_INDEX, int END_INDEX)
	{
		boolean isFilterValues = false;
		Map map = new HashMap();
		map.put("status", status);
		map.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);
		map.put("startIndx", START_INDEX);
		map.put("endIndx", END_INDEX);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GET_FILTERED_TRIP_DETAILS);
		stringBuffer.append(" td.status=:status");

		if (_setActivityFilterValuesForPrice(priceMap) != null
				&& _setActivityFilterValuesForPrice(priceMap).length() > 0)
		{
			isFilterValues = true;
			stringBuffer.append(" AND ");
			stringBuffer.append(" ( ");
			stringBuffer.append(_setActivityFilterValuesForPrice(priceMap));
		}

		if (_setFilterActivityId(activityTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer.append(_setFilterActivityId(activityTable));
		}
		if (_setFilterSubActivityId(subActivityTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer.append(_setFilterSubActivityId(subActivityTable));
		}
		if (_setActivityFilterValuesForAllFields(tripTable) != null
				&& _setActivityFilterValuesForAllFields(tripTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer
					.append(_setActivityFilterValuesForAllFields(tripTable));
		}
		if (isFilterValues)
		{
			stringBuffer.append(" ) ");
		}
		stringBuffer.append(" ");
		stringBuffer.append(" LIMIT ");
		stringBuffer.append(" :startIndx ");
		stringBuffer.append(" , ");
		stringBuffer.append(" :endIndx ");

		return namedParameterJdbcTemplate.query(stringBuffer.toString(), map,
				new BeanPropertyRowMapper(Trip.class));
	}

	public int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable,
			Map<String, Object> subActivityTable, String status,
			Map<String, Object> priceMap)
	{
		Map map = new HashMap();
		map.put("status", status);
		boolean isFilterValues = false;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GET_FILTERED_TRIP_DETAILS_NUMENTRIES);
		stringBuffer.append(" AND status=:status");

		if (_setActivityFilterValuesForPrice(priceMap) != null
				&& _setActivityFilterValuesForPrice(priceMap).length() > 0)
		{
			isFilterValues = true;
			stringBuffer.append(" AND ");
			stringBuffer.append(" ( ");
			stringBuffer.append(_setActivityFilterValuesForPrice(priceMap));
		}
		if (_setFilterActivityId(activityTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer.append(_setFilterActivityId(activityTable));
		}
		if (_setFilterSubActivityId(subActivityTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer.append(_setFilterSubActivityId(subActivityTable));
		}
		if (_setActivityFilterValuesForAllFields(tripTable) != null
				&& _setActivityFilterValuesForAllFields(tripTable).length() > 0)
		{
			stringBuffer.append(" AND ");
			stringBuffer
					.append(_setActivityFilterValuesForAllFields(tripTable));
		}
		if (isFilterValues)
		{
			stringBuffer.append(" ) ");
		}

		return namedParameterJdbcTemplate.queryForInt(stringBuffer.toString(),
				map);
	}

	@Override
	public List<Trip> getTopActivitys(int visibleTopActivitys) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("visibleTopActivitys", visibleTopActivitys);
		paramMap.put("defaultImage", DEFAULT_TRIP_IMAGE_PATH);
		return namedParameterJdbcTemplate.query(GET_TOP_ACTIVITYS, paramMap,
				new BeanPropertyRowMapper(Trip.class));
	}

	@Override
	public void updateTripImgType(int tripId, int imageId, String iMAGE_TYPE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("tripId", tripId);
		paramMap.put("id", imageId);
		paramMap.put("iMAGE_TYPE", iMAGE_TYPE);

		namedParameterJdbcTemplate.update(UPDATE_TRIP_CONVER_IMAGE, paramMap);

	}

	@Override
	public void updateTripImgType_ViaTripId(int tripId, int imageId,
			String iMAGE_TYPE_PROFILE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("tripId", tripId);
		paramMap.put("id", imageId);
		paramMap.put("iMAGE_TYPE", iMAGE_TYPE_PROFILE);

		namedParameterJdbcTemplate.update(UPDATE_TRIP_CONVER_IMAGE_VIA_TRIPID,
				paramMap);
	}
}
