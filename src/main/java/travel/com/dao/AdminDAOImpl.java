package travel.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import travel.com.model.Admin;
import travel.com.model.Trip;

@SuppressWarnings(
{ "unchecked", "unused" })
public class AdminDAOImpl implements AdminDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String GET_ADMIN_CONTACTS =
			"SELECT * from admin where status =:status";
	final String ADMIN_LOGIN_VALIDATE =
			"Select * from admin where name =:name and password =:password";
	final String STATUS_ACTIVE = "active";

	@Override
	public List<Admin> getContacts() throws Exception
	{
		Map map = new HashMap();
		map.put("status", STATUS_ACTIVE);

		return namedParameterJdbcTemplate.query(GET_ADMIN_CONTACTS, map,
				new BeanPropertyRowMapper(Admin.class));

	}

	@Override
	public List<Admin> getAdminLoginValidate(Admin admin) throws Exception
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", admin.getName());
		map.put("password", admin.getPassword());

		return namedParameterJdbcTemplate.query(ADMIN_LOGIN_VALIDATE, map,
				new BeanPropertyRowMapper(Admin.class));
	}
}
