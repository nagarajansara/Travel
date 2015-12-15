package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

@SuppressWarnings("unused")
public interface BookingDAO
{
	List<Booking> getBookingDetails(Booking booking) throws Exception;

	List<Booking> getBooking(int vendorId, String sTATUS_ACTIVE)
			throws Exception;

	List<Booking> getLeads(Booking booking) throws Exception;

	int getNumEntries(Booking booking) throws Exception;

	Map<String, Object> getVendorStatistic(String status, int vendorId) throws Exception;
}
