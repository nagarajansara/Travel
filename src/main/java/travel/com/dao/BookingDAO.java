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
}
