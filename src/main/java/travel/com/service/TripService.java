package travel.com.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import travel.com.util.*;
import travel.com.model.*;

public interface TripService
{
	Long addTripDetails(Trip trip) throws Exception;

	Map<Object, Object> uploadTripDetailsImage(HttpServletRequest request)
			throws Exception;

	void addItenary(List<Itenary> itenaryList) throws Exception;

	Map<String, Object> getTripDetails(Trip trip) throws Exception; // User
																	// Based

	void addTripImage(TripImage tripImage) throws Exception;

	List<Trip> getUpdatingTripDetailsBasedId(Trip trips) throws Exception;

	void deleteItenary(Long tripId) throws Exception;

	void update(Trip trip) throws Exception;

	List<TripImage> getTripImages(TripImage tripImage) throws Exception;

	void deleteTripImage(TripImage tripImage) throws Exception;

	void updateTripDetailStatus(Trip trip) throws Exception;

	List<Trip> getFilterTripsDetails(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap, int START_INDEX, int END_INDEX);

	int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap);

	List<Trip> getTripDetails_Users(Trip trip) throws Exception; // Get all the
																	// trip
																	// details
																	// for users

	int getAllValidTripDetailsCount(Trip trip) throws Exception;

	List<Trip> getTripDetailsBasedId(Trip trip) throws Exception;

	List<Trip> getCredits_AND_Email(int tripId) throws Exception;

	List<Trip> getTripDetailsTitles_AND_Id(int userId, String status,
			String startTitle) throws Exception;

}
