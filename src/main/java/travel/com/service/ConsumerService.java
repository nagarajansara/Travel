package travel.com.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import travel.com.util.*;
import travel.com.model.*;

public interface ConsumerService
{
	List<Login> getProfile(int userId) throws Exception;

	List<Callbacks> getCallBacksDetails(Callbacks callbacks) throws Exception;

	List<Booking> getBookingDetails(Booking booking) throws Exception;

	void updateConsumerProfile(Login login) throws Exception;

	int getConsumerTripDetailsNumEntries(Booking booking) throws Exception;

	List<Trip> getConsumerTripDetails(Booking booking) throws Exception;

	List<Points> getPoints(Points points) throws Exception;

	List<Deals> getAllDeals(int startIndx, int maxIndx) throws Exception;

	int getAllDealsNumEntries() throws Exception;

	void addSavedTrips(int userId, int tripId) throws Exception;

	List<SavedTrips> getSavedTrips(int userId, String sTATTUS_SAVED,
			int startIndx, int endIndx) throws Exception;

	int getSavedTripNumEntries(int userId, String sTATTUS_SAVED)
			throws Exception;

	List<Reviews> getConsumerReview(int userId, int startIndx, int endIndx) throws Exception;

	int getConsumerReviewNumEntries(int userId) throws Exception;
}
