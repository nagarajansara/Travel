package travel.com.dao;

import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Field;

public class BaseDAOImpl implements BaseDAO
{

	public void setNamedParameter(Object object, String[] fields, Map map)
			throws Exception
	{

		if (fields != null && fields.length > 0)
		{
			for (int i = 0; i < fields.length; i++)
			{
				java.lang.reflect.Field field = object.getClass()
														.getDeclaredField(	fields[i]);
				field.setAccessible(true);
				if (field != null)
				{
					Object obj = field.get(object);
					map.put(fields[i], obj);
				}
			}

		}
	}

	public void
			setNamedParameter(String[] fields, String[] paramValues, Map map)
					throws Exception
	{
		if (fields != null && fields.length > 0)
		{
			for (int i = 0; i < fields.length; i++)
			{
				map.put(fields[i], paramValues[i]);
			}
		}
	}
}
