package travel.com.dao;

import java.util.HashMap;
import java.util.Map;

public interface BaseDAO
{
	void setNamedParameter(Object object, String[] fields, Map map)
			throws Exception;

	void setNamedParameter(String[] fields, String[] paramValues, Map map)
			throws Exception;
}
