package travel.com.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import travel.com.dao.BookingDAO;
import travel.com.model.Booking;
import travel.com.service.BookingService;
import travel.com.service.VendorService;

@SuppressWarnings("unused")
public class BookingServiceBO implements BookingService
{

	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;

	public List<Booking> getBooking(int vendorId, String sTATUS_ACTIVE)
			throws Exception
	{
		return bookingDAO.getBooking(vendorId, sTATUS_ACTIVE);
	}

	public List<Booking> getBookingDetails(Booking booking) throws Exception
	{
		return bookingDAO.getBookingDetails(booking);
	}

	public List<Booking> getLeads(Booking booking) throws Exception
	{
		return bookingDAO.getLeads(booking);
	}

	public int getNumEntries(Booking booking) throws Exception
	{
		return bookingDAO.getNumEntries(booking);
	}
}
