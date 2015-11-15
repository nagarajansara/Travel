package travel.com.bo;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.*;

import travel.com.service.*;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

public class CityServiceBO implements CityService
{

	@Autowired
	@Qualifier("cityDAO")
	CityDAO cityDAO;

	public List<City> getCity() throws Exception
	{
		return cityDAO.getCity();
	}

	public List<City> getCityApi(String locationname, String status)
			throws Exception
	{
		return cityDAO.getCityApi(locationname, status);
	}
}
