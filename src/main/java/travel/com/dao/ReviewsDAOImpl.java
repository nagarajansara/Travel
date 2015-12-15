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

@SuppressWarnings("unused")
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
			"INSERT INTO reviews (tripid, username, comment, startrating) Values (:tripid, :username, :comment, :startrating)";

	final String GET_REVIEWS_NUMENTRIES =
			"SELECT count(*) from reviews where tripid =:tripid";

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
		{ "tripid", "username", "comment", "startrating" };
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
}
