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
{ "unchecked", "unused" })
public class PointsDAOImpl implements PointsDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	String GET_POINTS = "SELECT points, type from points where userid =:userid";

	@Override
	public List<Points> getPoints(Points points) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", points.getUserid());
		return namedParameterJdbcTemplate.query(GET_POINTS, paramMap,
				new BeanPropertyRowMapper(Points.class));
	}

}
