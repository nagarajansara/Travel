package travel.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import travel.com.model.Enquiry;
import travel.com.model.Login;

@SuppressWarnings(
{ "unchecked", "checked" })
public class EnquiryDAOImpl implements EnquiryDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String ADD_ENQUIRY =
			"INSERT INTO enquiry (tripid, status, credits, name, email, phoneno, userid) values (:tripid, :status, :credits, :name, :email, :phoneno, :userid)";
	final String GET_ENQUIRY =
			"SELECT u.credits AS credits, u.email AS email, td.title AS title, td.id AS tripid, e.* FROM tripdetails td "
					+ "INNER JOIN enquiry e "
					+ "ON td.id = e.tripid "
					+ "INNER JOIN users u "
					+ "ON u.id = td.userid "
					+ "WHERE e.status =:status AND td.userid =:userId AND td.fromdate >= NOW() ORDER BY td.fromdate DESC LIMIT :startIndx, :endIndx";
	final String GET_NUMENTRIES_PENDINGENQUIRY =
			"SELECT count(*) AS totalpending FROM tripdetails td "
					+ "INNER JOIN enquiry e "
					+ "ON td.id = e.tripid "
					+ "INNER JOIN users u "
					+ "ON u.id = td.userid "
					+ "WHERE e.status =:status AND td.userid =:userId AND td.fromdate >= NOW()";

	final String UPDATE_ENQUIRY_STATUE =
			"Update enquiry set credits =:credits, status =:status where id =:id";

	public void addEnquiry(Enquiry enquiry) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid", "status", "credits", "email", "name", "phoneno", "userid" };
		try
		{
			baseDAO.setNamedParameter(enquiry, params, map);
			namedParameterJdbcTemplate.update(ADD_ENQUIRY, map);
		} catch (Exception ex)
		{
			throw ex;
		}
	}

	@Override
	public List<Enquiry> getPendingEnquiry(int userId, String status,
			int startIndx, int endIndx)
	{
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("startIndx", startIndx);
		map.put("endIndx", endIndx);
		List<Enquiry> list;
		list =
				namedParameterJdbcTemplate.query(GET_ENQUIRY, map,
						new BeanPropertyRowMapper(Enquiry.class));
		return list;
	}

	public void updateEnquiryStatus(Enquiry enquiry) throws Exception
	{
		Map map = new HashMap();
		map.put("status", enquiry.getStatus());
		map.put("id", enquiry.getId());
		map.put("credits", enquiry.getCredits());

		namedParameterJdbcTemplate.update(UPDATE_ENQUIRY_STATUE, map);
	}

	@Override
	public int getPendingEnquiryNumEntries(int userId, String status)
			throws Exception
	{
		int numEntries = 0;
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		paramMap.put("status", status);
		numEntries =
				namedParameterJdbcTemplate.queryForInt(
						GET_NUMENTRIES_PENDINGENQUIRY, paramMap);
		return numEntries;
	}
}
