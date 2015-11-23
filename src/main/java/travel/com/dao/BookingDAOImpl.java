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

import travel.com.model.*;

@SuppressWarnings("unused")
public class BookingDAOImpl implements BookingDAO
{

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	String GET_BOOKING_DETAILS_USER =
			"SELECT * from callbacks where consumerid =:consumerid";
	String GET_BOOKING_DETAILS_ADMIN =
			"SELECT * from callbacks where vendorid =:vendorid AND status =:status";

	public List<Booking> getBookingDetails(Booking booking) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "vendorid", "status" };
		List<Booking> list = null;
		try
		{
			baseDAO.setNamedParameter(booking, params, map);
			namedParameterJdbcTemplate.update(GET_BOOKING_DETAILS_ADMIN, map);
		} catch (Exception ex)
		{
			throw ex;
		}
		return list;
	}

	public List<Booking> getBooking(int vendorId, String sTATUS_ACTIVE)
			throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}
}
