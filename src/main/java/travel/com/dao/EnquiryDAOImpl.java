package travel.com.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import travel.com.model.Enquiry;

public class EnquiryDAOImpl implements EnquiryDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String ADD_ENQUIRY =
			"INSERT INTO enquiry (tripid, status, credits, name, email, phoneno) values (:tripid, :status, :credits, :name, :email, :phoneno)";

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

}
