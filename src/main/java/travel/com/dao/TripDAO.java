package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

public interface TripDAO
{
	Long addTripDetails(Trip trip) throws Exception;

	Map<String, Object> getTripDetails(Trip trip) throws Exception;

	List<Trip> getUpdatingTripDetailsBasedId(Trip trips) throws Exception;

	void update(Trip trip) throws Exception;

	void updateTripDetailStatus(Trip trip) throws Exception;

	List<Trip> getFilterTripsDetails(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap, int START_INDEX, int END_INDEX);

	int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap);

	List<Trip> getTripDetails_Users(Trip trip) throws Exception;

	int getAllValidTripDetailsCount(Trip trip) throws Exception;

	List<Trip> getTripDetailsBasedId(Trip trip) throws Exception;

}
