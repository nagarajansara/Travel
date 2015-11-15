package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;



public class ConsumerDAOImpl implements ConsumerDAO
{


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	final String GET_PROFILE_BASED_USERID = "Select * from users where id =:userId";

	public List<Login> getProfile(int userId) throws Exception
	{
		Map map = new HashMap();
		map.put("userId", userId);

		List<Login> list = namedParameterJdbcTemplate.query(GET_PROFILE_BASED_USERID, map, new BeanPropertyRowMapper(Login.class));
		
		return list;
	}
}



