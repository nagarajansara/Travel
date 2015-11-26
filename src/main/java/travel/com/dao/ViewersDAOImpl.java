package travel.com.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import travel.com.model.Viewers;

public class ViewersDAOImpl implements ViewersDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("baseDAO")
	BaseDAO baseDAO;

	final String UPDATE_VIEWERS_COUNT =
			"update viewers set status =:status, credits =:credits where tripid =:tripid";

	final String INSERT_VIEWERS =
			"INSERT INTO viewers (tripid, status, credits) values (:tripid, :status, :credits)";

	final String GET_VIEWERS_NUMENTRIES =
			"SELECT count(*) from viewers where tripid =:tripid";

	public void updateViewersCount(Viewers viewers) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "status", "credits" };
		try
		{
			baseDAO.setNamedParameter(viewers, params, map);
			namedParameterJdbcTemplate.update(UPDATE_VIEWERS_COUNT, map);
		} catch (Exception ex)
		{
			throw ex;
		}

	}

	public int getNumEntries(Viewers viewers) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "tripid" };
		int numEntries = 0;
		try
		{
			baseDAO.setNamedParameter(viewers, params, map);
			numEntries = namedParameterJdbcTemplate.queryForInt(GET_VIEWERS_NUMENTRIES, map);
		} catch (Exception ex)
		{
			throw ex;
		}
		return numEntries;
	}

	public void insertViewers(Viewers viewers) throws Exception
	{
		Map map = new HashMap();
		String[] params =
		{ "status", "credits", "tripid" };
		try
		{
			baseDAO.setNamedParameter(viewers, params, map);
			namedParameterJdbcTemplate.update(INSERT_VIEWERS, map);
		} catch (Exception ex)
		{
			throw ex;
		}
	}

}
