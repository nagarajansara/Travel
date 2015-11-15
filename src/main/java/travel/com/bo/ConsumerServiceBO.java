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


public class ConsumerServiceBO implements ConsumerService
{

	
	
	@Autowired
	@Qualifier("consumerDAO")
	ConsumerDAO consumerDAO;

	@Autowired
	@Qualifier("callbacksDAO")
	CallbacksDAO callbacksDAO;

	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;
	

	public List<Login> getProfile(int userId) throws Exception
	{
		return consumerDAO.getProfile(userId);
	}
	public List<Callbacks> getCallBacksDetails(Callbacks callbacks) throws Exception
	{
		return callbacksDAO.getCallBacksDetails(callbacks);			
	}
	public List<Booking> getBookingDetails(Booking booking) throws Exception
	{
		return bookingDAO.getBookingDetails(booking);
	}
}

