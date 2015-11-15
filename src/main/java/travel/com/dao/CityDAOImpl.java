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

public class CityDAOImpl implements CityDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	String GET_CITY_QUERY = "SELECT id, city, state from city where status =:status ORDER BY city ASC LIMIT 1, 50";
	String GET_CITY = "SELECT id, city, state from city where status =:status AND (city like :city OR state =:state)";

	String STATUS_ACTIVE = "active";
	
	public List<City> getCity() throws Exception
	{
		List<City> list = null;	
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", STATUS_ACTIVE);

			list = namedParameterJdbcTemplate.query(GET_CITY_QUERY, map, new BeanPropertyRowMapper(City.class));

		}
		catch(Exception ex)
		{
			throw ex;
		}

		return list;
	}
	public List<City> getCityApi(String locationName, String status) throws Exception
	{
		Map map = new HashMap();
		map.put("status", status);
		map.put("city", locationName + "%");
		map.put("state", locationName + "%");

		List<City> list = namedParameterJdbcTemplate.query(GET_CITY, map, new BeanPropertyRowMapper(City.class));
		return list;
	}
}
