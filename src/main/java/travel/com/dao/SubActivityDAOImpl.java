package travel.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import travel.com.model.Login;
import travel.com.model.SubActivity;

@SuppressWarnings(
{ "unchecked", "unused" })
public class SubActivityDAOImpl implements SubActivityDAO
{

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String INSERT_DATA =
			"INSERT INTO subactivity (name, status) values (:name, :status)";

	final String GET_SUB_ACTIVITY = "Select * from subactivity where status =:status";

	public void addSubActivity(SubActivity subActivity) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", subActivity.getName());
		paramMap.put("status", subActivity.getStatus());

		namedParameterJdbcTemplate.update(INSERT_DATA, paramMap);
	}

	public List<SubActivity> getSubActivity(String status) throws Exception
	{
		List<SubActivity> list = null;
		Map paramMap = new HashMap();
		paramMap.put("status", status);

		return namedParameterJdbcTemplate.query(GET_SUB_ACTIVITY, paramMap,
				new BeanPropertyRowMapper(SubActivity.class));

	}
}
