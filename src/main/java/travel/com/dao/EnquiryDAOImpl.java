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
			"INSERT INTO enquiry (tripid, status, credits, name, email, phoneno) values (:tripid, :status, :credits, :name, :email, :phoneno)";
	final String GET_ENQUIRY =
			"SELECT u.credits AS credits, u.email AS email, td.title AS title, td.id AS tripid, e.* FROM tripdetails td "
					+ "INNER JOIN enquiry e "
					+ "ON td.id = e.tripid "
					+ "INNER JOIN users u "
					+ "ON u.id = td.userid "
					+ "WHERE e.status =:status AND td.userid =:userId AND td.fromdate >= NOW() LIMIT 0, 10";
	final String UPDATE_ENQUIRY_STATUE =
			"Update enquiry set credits =:credits, status =:status where id =:id";

	public void addEnquiry(Enquiry enquiry) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid", "status", "credits", "email", "name", "phoneno" };
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
	public List<Enquiry> getPendingEnquiry(int userId, String status)
	{
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
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
}
