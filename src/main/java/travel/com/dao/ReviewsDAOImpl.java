package travel.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import travel.com.model.Reviews;
import travel.com.model.Trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SuppressWarnings(
{ "unused", "unchecked" })
public class ReviewsDAOImpl implements ReviewsDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String GET_REVIEWS_BASED_TRIP_ID =
			"Select username, comment, DATE_FORMAT(createdate, '%d-%m-%y') AS createdateformat from reviews where tripid =:tripid  "
					+ "ORDER BY createdate DESC  LIMIT :startIndx, :endIndx";
	final String ADD_REVIEWS =
			"INSERT INTO reviews (tripid, username, comment, startrating, email) Values (:tripid, :username, :comment, :startrating, :email)";

	final String GET_REVIEWS_NUMENTRIES =
			"SELECT count(*) from reviews where tripid =:tripid";

	final String GET_VENDOR_REVIEWS =
			"SELECT r.*, t.title AS title FROM reviews r INNER JOIN tripdetails t "
					+ "ON t.id = r.tripid where "
					+ "r.tripid =:tripId AND t.userid =:userId  ORDER BY r.createdate DESC LIMIT :startIndx, :endIndx";
	final String GET_VENDOR_REVIEWS_NUMENTRIES =
			"SELECT count(*) FROM reviews r INNER JOIN tripdetails t "
					+ "ON t.id = r.tripid  where "
					+ "r.tripid =:tripId AND t.userid =:userId";
	final String GET_REVIEWS_BASED_VENDOR_ID =
			"SELECT r.*, t.title AS title FROM reviews r  "
					+ "INNER JOIN tripdetails t "
					+ "ON r.tripid = t.id "
					+ "WHERE t.userid =:vendorid ORDER BY r.createdate DESC LIMIT :startIndx, :endIndx";
	final String GET_REVIEWS_BASED_VENDOR_ID_NUMENTRIES =
			"SELECT count(*)  FROM reviews r  " + "INNER JOIN tripdetails t "
					+ "ON r.tripid = t.id " + "WHERE t.userid =:vendorid";
	// HERE CONSUMER AND VENDOR CAN GIVE THE REVIEWS
	final String GET_REVIEWS_BASED_CONSUMER =
			"SELECT IFNULL(r.comment, 'false') AS isreviewed, r.username, r.email, temp.userid, "
					+ "temp.tripid, t.title FROM(SELECT b.userid, b.tripid, b.status FROM booking b "
					+ "UNION "
					+ "SELECT e.userid, e.tripid, e.status FROM enquiry e) "
					+ "AS temp "
					+ "INNER JOIN tripdetails t "
					+ "ON "
					+ "t.id = temp.tripid "
					+ "LEFT OUTER JOIN "
					+ "reviews r "
					+ "ON "
					+ "r.tripid = temp.tripid "
					+ "WHERE temp.userid =:userId "
					+ "AND "
					+ "r.comment IS NULL "
					+ "GROUP BY temp.tripid "
					+ "LIMIT :startIndx, :endIndx";
	final String GET_REVIEWS_NUMENTRIES_BASED_CONSUMER =
			"SELECT count(*) FROM(SELECT b.userid, b.tripid, b.status FROM booking b "
					+ "UNION "
					+ "SELECT e.userid, e.tripid, e.status FROM enquiry e) "
					+ "AS temp " + "LEFT OUTER JOIN " + "reviews r " + "ON "
					+ "r.tripid = temp.tripid " + "WHERE temp.userid =:userId "
					+ "AND " + "r.comment IS NULL ";

	public List<Reviews> getReviewsBasedTripId(Reviews reviews)
			throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid", "startIndx", "endIndx" };
		List<Reviews> reviewsList;
		try
		{
			baseDAO.setNamedParameter(reviews, params, map);
			reviewsList =
					namedParameterJdbcTemplate.query(GET_REVIEWS_BASED_TRIP_ID,
							map, new BeanPropertyRowMapper(Reviews.class));
		} catch (Exception ex)
		{
			throw ex;
		}
		return reviewsList;
	}

	public void addComments(Reviews reviews) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid", "username", "comment", "startrating", "email" };
		try
		{
			baseDAO.setNamedParameter(reviews, params, map);
			namedParameterJdbcTemplate.update(ADD_REVIEWS, map);

		} catch (Exception ex)
		{
			throw ex;
		}

	}

	public int getNumEntries(Reviews reviews) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid" };
		int numEntries = 0;
		try
		{
			baseDAO.setNamedParameter(reviews, params, map);
			numEntries =
					namedParameterJdbcTemplate.queryForInt(
							GET_REVIEWS_NUMENTRIES, map);
		} catch (Exception ex)
		{
			throw ex;
		}
		return numEntries;
	}

	public List<Reviews> getCommentsPagno(Reviews reviews) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid", "startIndx", "endIndx" };
		List<Reviews> list;
		try
		{
			baseDAO.setNamedParameter(reviews, params, map);
			list =
					namedParameterJdbcTemplate.query(GET_REVIEWS_BASED_TRIP_ID,
							map, new BeanPropertyRowMapper(Reviews.class));
		} catch (Exception ex)
		{
			throw ex;
		}
		return list;
	}

	@Override
	public List<Reviews> getVendorReviews(Reviews reviews) throws Exception
	{
		Map map = new HashMap();
		map.put("tripId", reviews.getTripid());
		map.put("startIndx", reviews.getStartIndx());
		map.put("endIndx", reviews.getEndIndx());
		map.put("userId", reviews.getVendorId());

		List<Reviews> list =
				namedParameterJdbcTemplate.query(GET_VENDOR_REVIEWS, map,
						new BeanPropertyRowMapper(Reviews.class));
		return list;
	}

	@Override
	public int getVendorReviewsNumEntries(int tripId, int userId)
			throws Exception
	{
		Map map = new HashMap();
		map.put("tripId", tripId);
		map.put("userId", userId);
		return namedParameterJdbcTemplate.queryForInt(
				GET_VENDOR_REVIEWS_NUMENTRIES, map);
	}

	@Override
	public List<Reviews> getReviews(int vendorId, int startIndx, int endIndx)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("vendorid", vendorId);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", endIndx);

		return namedParameterJdbcTemplate.query(GET_REVIEWS_BASED_VENDOR_ID,
				paramMap, new BeanPropertyRowMapper(Reviews.class));

	}

	@Override
	public int getReviewsNumEntries(int vendorId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("vendorid", vendorId);

		return namedParameterJdbcTemplate.queryForInt(
				GET_REVIEWS_BASED_VENDOR_ID_NUMENTRIES, paramMap);
	}

	@Override
	public List<Reviews> getConsumerReview(int userId, int startIndx,
			int endIndx) throws Exception
	{
		Map paraMap = new HashMap();
		paraMap.put("userId", userId);
		paraMap.put("startIndx", startIndx);
		paraMap.put("endIndx", endIndx);
		return namedParameterJdbcTemplate.query(GET_REVIEWS_BASED_CONSUMER,
				paraMap, new BeanPropertyRowMapper(Reviews.class));

	}

	@Override
	public int getConsumerReviewNumEntries(int userId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		return namedParameterJdbcTemplate.queryForInt(
				GET_REVIEWS_NUMENTRIES_BASED_CONSUMER, paramMap);
	}
}
