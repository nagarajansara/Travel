package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface BookingDAO
{
	List<Booking> getBookingDetails(Booking booking) throws Exception;
}
