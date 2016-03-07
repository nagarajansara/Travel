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

public class TripImageDAOImpl implements TripImageDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String ADD_IMAGE = "INSERT into tripimages (tripid, name) VALUES (:tripId, :name)";
	final String GET_IMAGE_DETAILS = "SELECT tripid, id, name, imagetype from tripimages where tripid =:tripId AND status =:status LIMIT 0, 10";
	final String DELETE_TRIP_IMAGE = "Update tripimages set status =:status where tripid =:tripid and id =:id";

	public void addTripImage(TripImage tripImage) throws Exception   //Change to bulk INSERT
	{
		Map map = new HashMap();
		map.put("tripId", tripImage.getTripid());
		map.put("name", tripImage.getName());
		namedParameterJdbcTemplate.update(ADD_IMAGE, map);
	}
	public List<TripImage> getTripImages(TripImage tripImage) throws Exception
	{
		Map map = new HashMap();
		map.put("tripId", tripImage.getTripid());
		map.put("status", tripImage.getStatus());

		List<TripImage> list = namedParameterJdbcTemplate.query(GET_IMAGE_DETAILS, map, new BeanPropertyRowMapper(TripImage.class));
		return list;
	}
	public void deleteTripImage(TripImage tripImage) throws Exception
	{
		Map map = new HashMap();
		map.put("tripid", tripImage.getTripid());
		map.put("id", tripImage.getId());
		map.put("status", tripImage.getStatus());
	
		namedParameterJdbcTemplate.update(DELETE_TRIP_IMAGE, map);
	}
	
}
