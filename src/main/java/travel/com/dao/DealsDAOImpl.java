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
public class DealsDAOImpl implements DealsDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	String GET_DEALS =
			"SELECT d.id, d.offer_percentage AS offer_percentage, d.offerdesc AS offerdesc, t.title AS title, t.price AS price FROM deals d "
					+ "INNER JOIN tripdetails t "
					+ "ON t.id = d.tripid "
					+ "WHERE d.vendorid =:vendorid AND d.status =:status AND t.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d') ORDER BY d.createdat DESC LIMIT :startIndx, :endIndx";
	String GET_NUMENTRIES_DEALS =
			"SELECT count(*) From deals d "
					+ "INNER JOIN tripdetails t "
					+ "ON t.id = d.tripid "
					+ "WHERE d.vendorid =:vendorid AND d.status =:status AND t.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d')";
	String UPDATE_DEALS =
			"update deals set offer_percentage =:offerPercentage where id =:id";
	String ADD_DEALS =
			"INSERT IGNORE into deals (offer_percentage, offerdesc, vendorid, tripid) VALUES (:offer_percentage, :offerdesc, :vendorid, "
					+ ":tripid)";

	String GET_ALL_DEALS =
			"SELECT d.id, d.tripid AS tripid, d.offer_percentage AS offer_percentage, d.offerdesc AS offerdesc, t.title AS title, t.price AS price FROM deals d "
					+ "INNER JOIN tripdetails t "
					+ "ON t.id = d.tripid "
					+ "WHERE d.status =:status AND t.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d') ORDER BY d.createdat DESC LIMIT :startIndx, :endIndx";

	String GET_ALL_DEALS_NUMENTRIES =
			"SELECT count(*) as totaldeals FROM deals d "
					+ "INNER JOIN tripdetails t "
					+ "ON t.id = d.tripid "
					+ "WHERE d.status =:status AND t.fromdate >= DATE_FORMAT(NOW(), '%y-%m-%d')";

	public List<Deals> getDeals(Deals deals) throws Exception
	{
		Map map = new HashMap();
		map.put("vendorid", deals.getVendorid());
		map.put("status", deals.getStatus());
		map.put("startIndx", deals.getStartIndx());
		map.put("endIndx", deals.getEndIndx());
		return namedParameterJdbcTemplate.query(GET_DEALS, map,
				new BeanPropertyRowMapper(Deals.class));
	}

	@Override
	public void updateDeals(int offerPercentage, int id) throws Exception
	{
		Map map = new HashMap();
		map.put("offerPercentage", offerPercentage);
		map.put("id", id);
		namedParameterJdbcTemplate.update(UPDATE_DEALS, map);
	}

	@Override
	public int getDealsEntries(Deals deals) throws Exception
	{
		Map map = new HashMap();
		map.put("vendorid", deals.getVendorid());
		map.put("status", deals.getStatus());
		return namedParameterJdbcTemplate
				.queryForInt(GET_NUMENTRIES_DEALS, map);
	}

	@Override
	public void addDeals(Deals deals) throws Exception
	{
		Map map = new HashMap();
		map.put("offer_percentage", deals.getOffer_percentage());
		map.put("offerdesc", deals.getOfferdesc());
		map.put("vendorid", deals.getVendorid());
		map.put("tripid", deals.getTripid());

		namedParameterJdbcTemplate.update(ADD_DEALS, map);

	}

	@Override
	public List<Deals> getAllDeals(int startIndx, int maxIndx) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("status", "active");

		return namedParameterJdbcTemplate.query(GET_ALL_DEALS, paramMap,
				new BeanPropertyRowMapper(Deals.class));
	}

	@Override
	public int getAllDealsNumEntries() throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", "active");
		return namedParameterJdbcTemplate.queryForInt(GET_ALL_DEALS_NUMENTRIES,
				paramMap);
	}

}
