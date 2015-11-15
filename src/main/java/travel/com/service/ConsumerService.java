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
}

