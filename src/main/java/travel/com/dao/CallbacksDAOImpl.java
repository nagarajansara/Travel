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

public class CallbacksDAOImpl implements CallbacksDAO
{


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	String GET_CALLBACKS_DETAILS = "SELECT c.*, u.email FROM callbacks c, users u WHERE c.vendorid = u.id  and c.consumerid =:consumerid";

	public List<Callbacks> getCallBacksDetails(Callbacks callbacks) throws Exception
	{
		Map map = new HashMap();
		map.put("consumerid", callbacks.getConsumerid());
		List<Callbacks> list = namedParameterJdbcTemplate.query(GET_CALLBACKS_DETAILS, map, new BeanPropertyRowMapper(Callbacks.class));		
		return list;
	}
	
}
