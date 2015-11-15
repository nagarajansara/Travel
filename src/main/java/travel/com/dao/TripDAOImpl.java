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

@SuppressWarnings("unchecked")
public class TripDAOImpl implements TripDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String INSERT_TRIP_DETAILS =
			"INSERT into tripdetails (userid, locationid, activityid, duration, fromdate, todate, startpoint, route, description, guidelines, title, price "
					+ ") VALUES (:userid, :locationid, :activityid, :duration, :fromdate, :todate, :startpoint, :route, :description, "
					+ ":guidelines, :title, :price)";

	final String GET_TRIP_DETAILS =
			"SELECT td.title, td.id AS id, td.description AS description, td.guidelines AS guidelines, SUBSTRING_INDEX(GROUP_CONCAT(ti.name), ',', 1) AS imageurls, "
					+ "DATE_FORMAT(td.createdat, '%b %d, %Y') AS createdat, a.name AS activityname "
					+ "FROM tripdetails td "
					+ "INNER JOIN activity a ON td.activityid = a.id "
					+ "INNER JOIN tripimages ti ON td.id = ti.tripid "
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
					+ "DATE_FORMAT(td.createdat, '%b %d, %Y') AS createdat, a.name AS activityname "
					+ "FROM tripdetails td "
					+ "INNER JOIN activity a ON td.activityid = a.id "
					+ "INNER JOIN itenary i ON td.id = i.tripid "
					+ "INNER JOIN city fc ON td.startpoint = fc.id "
					+ "INNER JOIN city tc ON td.locationid = tc.id "
					+ "where "
					+ "td.status =:status and td.userid =:userId AND td.id =:tripId "
					+ "ORDER BY td.createdat DESC";

	final String UPDATE_TRIP_DETAILS =
			"UPDATE tripdetails set locationid =:locationid, activityid =:activityid, duration =:duration, "
					+ "fromdate =:fromdate, todate =:todate, startpoint =:startpoint, route =:route, description =:description, "
					+ "guidelines =:guidelines, title =:title, price =:price where id =:id and userid =:userId";

	final String UPDATE_TRIP_DETAILS_STATUS =
			"UPDATE tripdetails set status =:status where userid =:userid AND id =:id";

	final String GET_FILTERED_TRIP_DETAILS =
			"SELECT td.*, ti.name AS tripimagename, DATE_FORMAT(td.fromdate, '%b %d, %Y') AS dateformat FROM tripdetails td "
					+ "INNER JOIN (SELECT * FROM tripimages GROUP BY tripid) ti ON td.id = ti.tripid "
					+ "WHERE ";

	final String GET_FILTERED_TRIP_DETAILS_NUMENTRIES =
			"Select count(*) from tripdetails td where ";
	final String GET_ALL_TRIP_DETAILS =
			"SELECT td.*, ti.name AS tripimagename, DATE_FORMAT(td.fromdate, '%b %d, %Y') AS dateformat FROM tripdetails td "
					+ "INNER JOIN (SELECT * FROM tripimages GROUP BY tripid) ti ON td.id = ti.tripid "
					+ "WHERE td.fromdate >= NOW() AND td.status =:status LIMIT :startIndx, :endIndx";

	final String GET_ALL_TRIPDETAILS_NUMENTRIES =
			"Select count(*) from tripdetails where fromdate >= NOW() AND status =:status";
	final String ACTIVITY_ID_ATTRIBUTE = "activityid";

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
						.addValue("title", trip.getTitle());
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

	public List<Trip> getTripDetailsBasedId(Trip trip) throws Exception
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
		Map map = new HashMap();
		map.put("status", status);
		map.put("startIndx", START_INDEX);
		map.put("endIndx", END_INDEX);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GET_FILTERED_TRIP_DETAILS);
		stringBuffer.append(" td.status=:status");
		stringBuffer.append(" AND ");
		stringBuffer.append(" ( ");
		stringBuffer.append(_setActivityFilterValuesForPrice(priceMap));
		if (_setFilterActivityId(activityTable).length() > 0)
		{
			stringBuffer.append(" OR ");
			stringBuffer.append(_setFilterActivityId(activityTable));
		}
		if (_setActivityFilterValuesForAllFields(tripTable) != null
				&& _setActivityFilterValuesForAllFields(tripTable).length() > 0)
		{
			stringBuffer.append(" OR ");
			stringBuffer
					.append(_setActivityFilterValuesForAllFields(tripTable));
		}
		stringBuffer.append(" ) ");
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

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GET_FILTERED_TRIP_DETAILS_NUMENTRIES);
		stringBuffer.append(" status=:status");
		stringBuffer.append(" AND ");
		stringBuffer.append(" ( ");
		stringBuffer.append(_setActivityFilterValuesForPrice(priceMap));
		if (_setFilterActivityId(activityTable).length() > 0)
		{
			stringBuffer.append(" OR ");
			stringBuffer.append(_setFilterActivityId(activityTable));
		}
		if (_setActivityFilterValuesForAllFields(tripTable) != null
				&& _setActivityFilterValuesForAllFields(tripTable).length() > 0)
		{
			stringBuffer.append(" OR ");
			stringBuffer
					.append(_setActivityFilterValuesForAllFields(tripTable));
		}
		stringBuffer.append(" ) ");
		return namedParameterJdbcTemplate.queryForInt(stringBuffer.toString(),
				map);

	}

	private String _setFilterActivityId(Map<String, Object> activityTable) // TO-DO
																			// Activity
																			// Id's
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
				stringBuffer.append("td." + entry.getKey());
				stringBuffer.append(" = ");
				if (containsStringValues.contains(entry.getKey()))
				{
					stringBuffer.append("\'" + entry.getValue() + "\'");
				} else
				{
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
}
