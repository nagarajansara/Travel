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

public class BookingDAOImpl implements BookingDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	String GET_BOOKING_DETAILS = "SELECT * from callbacks where consumerid =:consumerid";

	public List<Booking> getBookingDetails(Booking booking) throws Exception
	{
		Map map = new HashMap();
		map.put("consumerid", booking.getConsumerid());

		List<Booking> list = namedParameterJdbcTemplate.query(GET_BOOKING_DETAILS, map, new BeanPropertyRowMapper(Booking.class));
		return list;
	}
}
