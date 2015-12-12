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

@SuppressWarnings(
{ "unused", "unchecked" })
public class BookingDAOImpl implements BookingDAO
{

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String GET_BOOKING_DETAILS_USER =
			"SELECT * from callbacks where consumerid =:consumerid";
	final String GET_BOOKING_DETAILS_ADMIN =
			"SELECT * from callbacks where vendorid =:vendorid AND status =:status";
	final String GET_MY_LEADS =
			"SELECT td.title AS title, u.email AS email, b.* FROM booking b "
					+ "INNER JOIN tripdetails td  ON "
					+ "b.tripid = td.id "
					+ "INNER JOIN users u ON "
					+ "u.id = b.consumerid "
					+ "WHERE td.userid =:vendorid AND b.status =:status ORDER BY b.createdat LIMIT :startIndx, :endIndx";

	final String GET_NUMENTRIES_MYLEADS = "SELECT count(*) FROM booking b "
			+ "INNER JOIN tripdetails td  ON " + "b.tripid = td.id "
			+ "INNER JOIN users u ON " + "u.id = b.consumerid "
			+ "WHERE td.userid =:vendorid AND b.status =:status";

	final String GET_VENDOR_STATISTIC_BOOKING =
			"SELECT  UNIX_TIMESTAMP(createdat)*1000 AS createdat, COUNT(*) AS totals "
					+ "FROM booking "
					+ "WHERE createdat BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() GROUP BY DATE_FORMAT(createdat, '%m/%d/%Y')";

	final String GET_VENDOR_STATISTIC_ENQUIRY =
			"SELECT  UNIX_TIMESTAMP(createdat)*1000 AS createdat, COUNT(*) AS totals "
					+ "FROM enquiry "
					+ "WHERE createdat BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() GROUP BY DATE_FORMAT(createdat, '%m/%d/%Y') AND "
					+ " status =:status";

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
		return null;
	}

	public List<Booking> getLeads(Booking booking) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "vendorid", "status", "startIndx", "endIndx" };
		List<Booking> list = null;
		try
		{
			baseDAO.setNamedParameter(booking, params, map);
			list =
					namedParameterJdbcTemplate.query(GET_MY_LEADS, map,
							new BeanPropertyRowMapper(Booking.class));
		} catch (Exception ex)
		{
			throw ex;
		}
		return list;

	}

	public int getNumEntries(Booking booking) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "vendorid", "status" };
		int numEntries = 0;
		try
		{
			baseDAO.setNamedParameter(booking, params, map);
			numEntries =
					namedParameterJdbcTemplate.queryForInt(
							GET_NUMENTRIES_MYLEADS, map);

		} catch (Exception ex)
		{
			throw ex;
		}
		return numEntries;
	}

	@Override
	public Map<String, Object> getVendorStatistic() throws Exception
	{
		Map<String, Object> mapList = new HashMap<String, Object>();
		Map params = new HashMap();
		List<Booking> bookingList =
				namedParameterJdbcTemplate.query(GET_VENDOR_STATISTIC_BOOKING,
						params, new BeanPropertyRowMapper(Booking.class));
		params.put("status", "enquiry"); // Enquire Status
		List<Enquiry> enquiryList =
				namedParameterJdbcTemplate.query(GET_VENDOR_STATISTIC_ENQUIRY,
						params, new BeanPropertyRowMapper(Enquiry.class));
		mapList.put("booking", bookingList);
		mapList.put("enquiry", enquiryList);
		return mapList;
	}
}
