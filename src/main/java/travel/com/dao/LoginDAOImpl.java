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
import travel.com.util.Response;

@SuppressWarnings(
{ "unused", "unchecked" })
public class LoginDAOImpl implements LoginDAO
{

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final int INITIAL_CREDITS = 0;

	final String INSERT_CUSTOMER_QUERY =
			"INSERT INTO users (email, firstname, lastname, password, role, city,"
					+ "ip) VALUES (:email, :firstname, :lastname, :password, :role, :city, :ip)";

	final String SIGN_UP_FB_QUERY =
			"INSERT INTO users (email, firstname, lastname, role, city, openid, openiddata, "
					+ "ip, signuptype) VALUES (:email, :firstname, :lastname, :role, :city, :openid, "
					+ ":openiddata, :ip, :signuptype)";

	final String GET_USER_ID_QUERY =
			"SELECT id, email from users where email =:email and openid =:openid";

	final String INSERT_VENDOR_QUERY =
			"INSERT into users (organizationname, email, firstname, lastname, password, role, state, mobile, phoneno, address,"
					+ "ip) VALUES (:organizationname, :email, :firstname, :lastname, :password, :role, :state, :mobile, "
					+ ":phoneno, :address, :ip)";

	final String VALIDATE_QUERY =
			"SELECT * from users where email =:email and password =:password and isapproved =:isapproved";

	final String GET_USER_DETAILS =
			"SELECT * from users where id =:id and role =:role";

	final String UPDATE_VENDOR_PROFILE_DATAS =
			"Update users set firstname =:firstname, lastname =:lastname, address =:address, mobile =:mobile, pincode =:pincode"
					+ ",state =:state, organizationname =:organizationname, city =:city, pancardno =:pancardno where id =:id";

	final String GET_CREDITS = "Select credits from users where id =:userId";

	final String GET_CREDITS_HISTORY_NUMENTRIES =
			"SELECT count(*) AS initialcredits FROM "
					+ "(SELECT tripid, STATUS, credits, createdat FROM booking where credits >:initialcredits "
					+ "UNION "
					+ "SELECT tripid, STATUS, credits, createdat FROM enquiry where credits >:initialcredits "
					+ "UNION "
					+ "SELECT tripid, STATUS, credits, createdat FROM viewers where credits >:initialcredits ORDER BY createdat DESC) AS Tab "
					+ "INNER JOIN tripdetails t ON Tab.tripid = t.id "
					+ "INNER JOIN users u ON u.id = t.userid "
					+ "WHERE u.id =:id";

	final String GET_CREDITS_HISTORY =
			"SELECT Tab.status As reason, Tab.credits AS tripcredits, DATE_FORMAT(Tab.createdat, '%d-%m-%y') AS createddate FROM "
					+ "(SELECT tripid, STATUS, credits, createdat FROM booking where credits >:initialcredits "
					+ "UNION "
					+ "SELECT tripid, STATUS, credits, createdat FROM enquiry where credits >:initialcredits "
					+ "UNION "
					+ "SELECT tripid, STATUS, credits, createdat FROM viewers where credits >:initialcredits ORDER BY createdat DESC) AS Tab "
					+ "INNER JOIN tripdetails t ON Tab.tripid = t.id "
					+ "INNER JOIN users u ON u.id = t.userid "
					+ "WHERE u.id =:id ORDER BY Tab.createdat DESC  LIMIT 0, 10";
	final String UPDATE_CREDITS =
			"Update users set credits =:credits where email =:email";

	public void insertCustomerData(Login login) throws Exception
	{
		Map map = new HashMap();
		map.put("email", login.getEmail());
		map.put("firstname", login.getFirstname());
		map.put("lastname", login.getLastname());
		map.put("password", login.getPassword());
		map.put("role", login.getRole());
		map.put("ip", login.getIp());
		map.put("city", login.getCity());
		try
		{

			namedParameterJdbcTemplate.update(INSERT_CUSTOMER_QUERY, map);
		} catch (Exception ex)
		{
			throw ex;
		}
	}

	public void customerSignUpFB(Login login) throws Exception
	{
		Map map = new HashMap();
		map.put("email", login.getEmail());
		map.put("firstname", login.getFirstname());
		map.put("lastname", login.getLastname());
		map.put("role", login.getRole());
		map.put("city", login.getCity());
		map.put("ip", login.getIp());
		map.put("openid", login.getOpenid());
		map.put("openiddata", login.getOpeniddata());
		map.put("signuptype", login.getSignuptype());

		namedParameterJdbcTemplate.update(SIGN_UP_FB_QUERY, map);

	}

	public int getUserId(String email, String openId) throws Exception
	{
		int userId = 0;
		Map map = new HashMap();
		map.put("email", email);
		map.put("openid", openId);

		List<Login> list =
				namedParameterJdbcTemplate.query(GET_USER_ID_QUERY, map,
						new BeanPropertyRowMapper(Login.class));
		if (list != null && list.size() > 0)
		{
			Login login = (Login) list.get(0);
			userId = login.getId();
		}
		return userId;
	}

	public void insertVendorData(Login login) throws Exception
	{
		Map map = new HashMap();
		String[] params =
				{ "organizationname", "email", "state", "password", "phoneno",
						"mobile", "firstname", "lastname", "role", "address",
						"ip" };
		try
		{
			baseDAO.setNamedParameter(login, params, map);
			namedParameterJdbcTemplate.update(INSERT_VENDOR_QUERY, map);
		} catch (Exception ex)
		{
			throw ex;
		}

	}

	public
			List<Login> validate(String email, String password,
					String isApproved) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "email", "password", "isapproved" };
		String[] paramValues =
		{ email, password, isApproved };
		List<Login> list = null;
		try
		{
			baseDAO.setNamedParameter(params, paramValues, map);
			list =
					namedParameterJdbcTemplate.query(VALIDATE_QUERY, map,
							new BeanPropertyRowMapper(Login.class));
		} catch (Exception ex)
		{
			throw ex;
		}
		return list;
	}

	public List<Login> getUserDetails(Login login) throws Exception
	{
		Map map = new HashMap();
		map.put("id", login.getId());
		map.put("role", login.getRole());

		List<Login> list =
				namedParameterJdbcTemplate.query(GET_USER_DETAILS, map,
						new BeanPropertyRowMapper(Login.class));
		return list;
	}

	public void updateProfile(Login login) throws Exception
	{
		Map map = new HashMap();
		String[] params =
				{ "firstname", "lastname", "address", "mobile", "pincode",
						"state", "organizationname", "city", "pancardno", "id" };
		try
		{
			baseDAO.setNamedParameter(login, params, map);
			namedParameterJdbcTemplate.update(UPDATE_VENDOR_PROFILE_DATAS, map);
		} catch (Exception ex)
		{
			throw ex;
		}
	}

	public int getCredits(int userId) throws Exception
	{
		int credits = 0;
		Map map = new HashMap();
		map.put("userId", userId);
		List<Login> list =
				namedParameterJdbcTemplate.query(GET_CREDITS, map,
						new BeanPropertyRowMapper(Login.class));
		if (list != null && list.size() > 0)
		{
			Login login = (Login) list.get(0);
			credits = login.getCredits();
		}
		return credits;
	}

	public List<Login> getCreditsHistory(Login login) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "id" };
		try
		{
			baseDAO.setNamedParameter(login, params, map);
			map.put("initialcredits", INITIAL_CREDITS);
			return namedParameterJdbcTemplate.query(GET_CREDITS_HISTORY, map,
					new BeanPropertyRowMapper(Login.class));
		} catch (Exception ex)
		{
			throw ex;
		}

	}

	public int getCreditHistoryNumEntries(Login login) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "id" };
		int numEntries = 0;
		try
		{
			baseDAO.setNamedParameter(login, params, map);
			map.put("initialcredits", INITIAL_CREDITS);
			numEntries =
					namedParameterJdbcTemplate.queryForInt(
							GET_CREDITS_HISTORY_NUMENTRIES, map);

		} catch (Exception ex)
		{
			throw ex;
		}
		return numEntries;
	}

	@Override
	public void updateCredits(String email)
	{
		Map map = new HashMap();
		map.put("email", email);
		namedParameterJdbcTemplate.update(UPDATE_CREDITS, map);
	}
}
