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

@SuppressWarnings(
{ "unchecked", "unused" })
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

	@Autowired
	@Qualifier("pointsDAO")
	PointsDAO pointsDAO;

	@Autowired
	@Qualifier("dealsDAO")
	DealsDAO dealsDAO;

	public List<Login> getProfile(int userId) throws Exception
	{
		return consumerDAO.getProfile(userId);
	}

	public List<Callbacks> getCallBacksDetails(Callbacks callbacks)
			throws Exception
	{
		return callbacksDAO.getCallBacksDetails(callbacks);
	}

	public List<Booking> getBookingDetails(Booking booking) throws Exception
	{
		return bookingDAO.getBookingDetails(booking);
	}

	@Override
	public void updateConsumerProfile(Login login) throws Exception
	{
		consumerDAO.updateConsumerProfile(login);
	}

	@Override
	public int getConsumerTripDetailsNumEntries(Booking booking)
			throws Exception
	{
		return consumerDAO.getConsumerTripDetailsNumEntries(booking);
	}

	@Override
	public List<Trip> getConsumerTripDetails(Booking booking) throws Exception
	{
		return consumerDAO.getConsumerTripDetails(booking);
	}

	@Override
	public List<Points> getPoints(Points points) throws Exception
	{
		return pointsDAO.getPoints(points);
	}

	@Override
	public List<Deals> getAllDeals(int startIndx, int maxIndx) throws Exception
	{
		return dealsDAO.getAllDeals(startIndx, maxIndx);
	}

	@Override
	public int getAllDealsNumEntries() throws Exception
	{
		return dealsDAO.getAllDealsNumEntries();
	}

	@Override
	public void addSavedTrips(int userId, int tripId) throws Exception
	{
		consumerDAO.addSavedTrips(userId, tripId);
	}

	@Override
	public List<SavedTrips> getSavedTrips(int userId, String sTATTUS_SAVED,
			int startIndx, int endIndx) throws Exception
	{
		return consumerDAO.getSavedTrips(userId, sTATTUS_SAVED, startIndx,
				endIndx);
	}

	@Override
	public int getSavedTripNumEntries(int userId, String sTATTUS_SAVED)
			throws Exception
	{
		return consumerDAO.getSavedTripNumEntries(userId, sTATTUS_SAVED);
	}
}
