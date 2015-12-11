package travel.com.controller;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import travel.com.service.*;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

@Controller
@RequestMapping("/booking")
@SuppressWarnings("unused")
public class BookingController extends BaseController
{
	private static final Logger logger = Logger
			.getLogger(BookingController.class.getName());

	@Autowired
	@Qualifier("vendorService")
	VendorService vendorService;

	@Autowired
	@Qualifier("tripService")
	TripService tripService;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("response")
	Response response;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@Autowired
	@Qualifier("bookingService")
	BookingService bookingService;

	@RequestMapping(value = "/getBookingDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getBookingDetails(HttpServletRequest request, ModelMap model)
	{
		List<Booking> list = null;
		try
		{
			String STATUS_ACTIVE = "active";
			int vendorId = getUserId(request);
			Booking booking = new Booking(vendorId, STATUS_ACTIVE);
			list = bookingService.getBookingDetails(booking);
			utilities.setSuccessResponse(response, list);
		} catch (Exception ex)
		{
			logger.error("getBookingDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "booking";
	}

	@RequestMapping(value = "/getLeads", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getLeads(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int vendorId = getUserId(request);
			int numEntries = 0;
			Booking booking =
					new Booking(vendorId, Booking.BOOKING_STATUS_BOOKED,
							utilities.getDefaultMinIndx(),
							utilities.getDefaultMaxIndx());
			Booking tempBooking =
					new Booking(vendorId, Booking.BOOKING_STATUS_BOOKED);
			numEntries = bookingService.getNumEntries(tempBooking);
			List<Booking> list = bookingService.getLeads(booking);
			map.put("leads", list);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getLeads: " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "myleads";
	}

	@RequestMapping(value = "/getLeadsPagination/{currPageNo}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getLeads(HttpServletRequest request,
			@PathVariable int currPageNo, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int vendorId = getUserId(request);
			int numEntries = 0;
			currPageNo = currPageNo - 1;
			Booking booking =
					new Booking(vendorId, Booking.BOOKING_STATUS_BOOKED,
							getStartIdx(currPageNo,
									utilities.getDefaultMaxIndx()),
							utilities.getDefaultMaxIndx());

			Booking tempBooking =
					new Booking(vendorId, Booking.BOOKING_STATUS_BOOKED);
			numEntries = bookingService.getNumEntries(tempBooking);
			List<Booking> list = bookingService.getLeads(booking);
			map.put("leads", list);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getLeads: " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "myleads";
	}
}
