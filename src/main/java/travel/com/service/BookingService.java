package travel.com.service;

import java.util.List;
import java.util.Map;

import travel.com.model.Booking;

@SuppressWarnings("unused")
public interface BookingService
{

	List<Booking> getBooking(int vendorId, String sTATUS_ACTIVE)
			throws Exception;

	List<Booking> getBookingDetails(Booking booking) throws Exception;

	List<Booking> getLeads(Booking booking) throws Exception;
	
	int getNumEntries(Booking booking) throws Exception;

}
