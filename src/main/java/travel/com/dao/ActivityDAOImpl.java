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


public class ActivityDAOImpl implements ActivityDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	final String GET_ACTIVITYS_DETAILS = "SELECT * from activity where status =:status";

	public List<Activity> getActivitys(Activity activity) throws Exception
	{
		Map map = new HashMap();
		map.put("status", activity.getStatus());

		List<Activity> list = namedParameterJdbcTemplate.query(GET_ACTIVITYS_DETAILS, map, new BeanPropertyRowMapper(Activity.class));
		return list;
	}
}

