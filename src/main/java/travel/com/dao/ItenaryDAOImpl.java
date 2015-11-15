package travel.com.dao;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


import travel.com.model.*;

public class ItenaryDAOImpl implements ItenaryDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String INSERT_ITENARY_QUERY = "INSERT INTO itenary (tripid, day, daywisedescription) "+
													"VALUES (:tripid, :dayname, :daywisedescription)";

	final String DELETE_ITENARY_QUERY = "DELETE FROM itenary where tripid =:tripid";

	public void addItenary(List<Itenary> itenaryList) throws Exception
	{
		if(itenaryList != null && 
				itenaryList.size() > 0)
		{
			for(int i = 0; i < itenaryList.size(); i++)
			{
				Map map = new HashMap();
				Itenary itenary = (Itenary)itenaryList.get(i);
				map.put("tripid", itenary.getTripid());
				map.put("dayname", itenary.getDay());
				map.put("daywisedescription", itenary.getDaywisedescription());
				namedParameterJdbcTemplate.update(INSERT_ITENARY_QUERY, map);
			}	
		}	
		
	}
	public void deleteItenary(Long tripId) throws Exception
	{
		Map map = new HashMap();
		map.put("tripid", tripId);
		namedParameterJdbcTemplate.update(DELETE_ITENARY_QUERY, map);
	}
		
		
}
