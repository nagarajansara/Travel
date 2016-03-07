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

	List<Trip> getFilterTripsDetails(Map<String, Object> tripTable,
			Map<String, Object> activityTable,
			Map<String, Object> subActivityTable, String status,
			Map<String, Object> priceMap, int START_INDEX, int END_INDEX);

	int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap);

	List<Trip> getTripDetails_Users(Trip trip) throws Exception;

	int getAllValidTripDetailsCount(Trip trip) throws Exception;

	List<Trip> getTripDetailsBasedId(Trip trip) throws Exception;

	List<Trip> getCredits_AND_Email(int tripId) throws Exception;

	List<Trip> getTripDetailsTitles_AND_Id(int userId, String status,
			String startTitle) throws Exception;

	List<Trip> getAllTripDetails(int userId, String sTATUS_ACTIVE,
			String startTitle) throws Exception;

	int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable,
			Map<String, Object> subActivityTable, String status,
			Map<String, Object> priceMap);

	List<Trip> getTopActivitys(int visibleTopActivitys) throws Exception;

	void updateTripImgType(int tripId, int imageId, String iMAGE_TYPE)
			throws Exception;

	void updateTripImgType_ViaTripId(int tripId, int imageId,
			String iMAGE_TYPE_PROFILE) throws Exception;

}
