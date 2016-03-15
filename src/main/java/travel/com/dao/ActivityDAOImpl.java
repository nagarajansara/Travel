package travel.com.dao;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import travel.com.model.*;

@SuppressWarnings(
{ "unused", "unchecked" })
public class ActivityDAOImpl implements ActivityDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String GET_ACTIVITYS_DETAILS =
			"SELECT * from activity where status =:status";
	final String GET_VENDOR_STATISTIC_BOOKING =
			"SELECT  UNIX_TIMESTAMP(createdat)*1000, COUNT(*), DATE_FORMAT(createdat, '%m/%d/%Y') "
					+ "FROM booking "
					+ "WHERE createdat BETWEEN CURDATE() - INTERVAL 7 DAY AND CURDATE() GROUP BY DATE_FORMAT(createdat, '%m/%d/%Y')";
	final String INSERT_ACTIVITY = "INSERT INTO activity (name) values (:name)";

	public List<Activity> getActivitys(Activity activity) throws Exception
	{
		Map map = new HashMap();
		map.put("status", activity.getStatus());

		List<Activity> list =
				namedParameterJdbcTemplate.query(GET_ACTIVITYS_DETAILS, map,
						new BeanPropertyRowMapper(Activity.class));
		return list;
	}

	@Override
	public Map<String, Object> getVendorStatistic(String status, int vendorId)
			throws Exception
	{
		Map<String, Object> mapList = new HashMap<String, Object>();
		Map params = new HashMap();
		List<Booking> list =
				namedParameterJdbcTemplate.query(GET_VENDOR_STATISTIC_BOOKING,
						params, new BeanPropertyRowMapper(Activity.class));
		List<Enquiry> lists =
				namedParameterJdbcTemplate.query(GET_VENDOR_STATISTIC_BOOKING,
						params, new BeanPropertyRowMapper(Enquiry.class));
		mapList.put("booking", list);
		mapList.put("enquiry", lists);
		return mapList;
	}

	@Override
	public Map<String, Object> getLeads(Booking booking) throws Exception
	{
		return null;
	}

	@Override
	public void addNewActivity(String activityName) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", activityName);

		namedParameterJdbcTemplate.update(INSERT_ACTIVITY, paramMap);

	}
}
