package travel.com.bo;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import travel.com.util.*;
import travel.com.model.*;

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

@SuppressWarnings("unused")
public class VendorServiceBO implements VendorService
{

	@Autowired
	@Qualifier("activityDAO")
	ActivityDAO activityDAO;

	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;

	public List<Activity> getActivitys(Activity activity) throws Exception
	{
		return activityDAO.getActivitys(activity);
	}

	@Override
	public Map<String, Object> getLeads(Booking booking) throws Exception
	{
		return activityDAO.getLeads(booking);
	}

	@Override
	public Map<String, Object> getVendorStatistic() throws Exception
	{
		return bookingDAO.getVendorStatistic();
	}

}
