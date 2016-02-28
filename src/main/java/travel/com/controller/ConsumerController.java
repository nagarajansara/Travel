package travel.com.controller;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;
import org.hibernate.HibernateException;
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
@RequestMapping("/consumer")
@SuppressWarnings("unused")
public class ConsumerController extends BaseController
{
	private static final Logger logger = Logger
			.getLogger(ConsumerController.class.getName());

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("consumerService")
	ConsumerService consumerService;

	@Autowired
	@Qualifier("response")
	Response response;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@RequestMapping(value = "/getprofile", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getprofile(HttpServletRequest request, ModelMap model)

	{
		try
		{
			int userId = getUserId(request);
			List<Login> login = consumerService.getProfile(userId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userDetails", login);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getprofile consumer :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumerprofile";
	}

	@RequestMapping(value = "/updateConsumerProfile", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateConsumerProfile(HttpServletRequest request,
			ModelMap model)

	{
		try
		{
			int userId = getUserId(request);

			String firstName = request.getParameter("fname");
			String lastName = request.getParameter("lname");
			String city = request.getParameter("city");

			if (firstName != null && lastName != null && city != null
					&& firstName.length() > 0 && lastName.length() > 0
					&& city.length() > 0)
			{
				Login login = new Login(firstName, lastName, city, userId);
				consumerService.updateConsumerProfile(login);
				List<Login> loginTemp = consumerService.getProfile(userId);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userDetails", loginTemp);
				utilities.setSuccessResponse(response, map);
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_NO_DATA,
						ConstException.ERR_MSG_NO_DATA);
			}

		} catch (Exception ex)
		{
			logger.error("updateProfile consumer :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumerprofile";
	}

	@RequestMapping(value = "/getConsumerTravels", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String
			getConsumerTravels(HttpServletRequest request, ModelMap model)
	{
		try
		{
			int startIndx = utilities.getDefaultMinIndx();
			int maxIndx = utilities.getDefaultMaxIndx();
			Map<String, Object> map = new HashMap<String, Object>();
			int consumerId = getUserId(request);
			Booking booking =
					new Booking(consumerId, Booking.BOOKING_STATUS_BOOKED,
							startIndx, maxIndx);
			int numEntries =
					consumerService.getConsumerTripDetailsNumEntries(booking);
			map.put("numEntries", numEntries);

			List<Trip> list = consumerService.getConsumerTripDetails(booking);
			map.put("tripDetails", list);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getConsumerTravels :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumermytravels";
	}

	@RequestMapping(value = "/getConsumerTravelsPagination/{startIndx}",
			method =
			{ RequestMethod.GET, RequestMethod.POST })
	public String getConsumerTravelsPagination(HttpServletRequest request,
			@PathVariable int startIndx, ModelMap model)
	{
		try
		{
			startIndx = startIndx - 1;
			startIndx = getStartIdx(startIndx, utilities.getDefaultMaxIndx());
			Map<String, Object> map = new HashMap<String, Object>();
			int consumerId = getUserId(request);
			Booking booking =
					new Booking(consumerId, Booking.BOOKING_STATUS_BOOKED,
							startIndx, utilities.getDefaultMaxIndx());
			int numEntries =
					consumerService.getConsumerTripDetailsNumEntries(booking);
			map.put("numEntries", numEntries);

			List<Trip> list = consumerService.getConsumerTripDetails(booking);
			map.put("tripDetails", list);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getConsumerTravelsPagination :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumermytravels";
	}

	@RequestMapping(value = "/getConsumerPoints", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getConsumerPoints(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int consumerId = getUserId(request);
			Points points = new Points(consumerId);
			List<Points> pList = consumerService.getPoints(points);
			map.put("points", pList);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getConsumerPoints :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumerearnpoints";
	}

	@RequestMapping(value = "/getAllDeals", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getAllDeals(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int consumerId = getUserId(request);
			List<Deals> deals =
					consumerService.getAllDeals(utilities.getDefaultMinIndx(),
							utilities.getDefaultMaxIndx());
			int numEntries = consumerService.getAllDealsNumEntries();
			map.put("deals", deals);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getAllDeals :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumerdeals";
	}

	@RequestMapping(value = "/getAllDealsPagination/{startIndx}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getAllDeals(HttpServletRequest request,
			@PathVariable int startIndx, ModelMap model)
	{
		try
		{
			startIndx = startIndx - 1;
			startIndx = getStartIdx(startIndx, utilities.getDefaultMaxIndx());
			Map<String, Object> map = new HashMap<String, Object>();
			int consumerId = getUserId(request);
			List<Deals> deals =
					consumerService.getAllDeals(startIndx,
							utilities.getDefaultMaxIndx());
			int numEntries = consumerService.getAllDealsNumEntries();
			map.put("deals", deals);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getAllDeals :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "consumerdeals";
	}

	@RequestMapping(value = "/getcallbacksdetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String getcallbacksdetails(HttpServletRequest request,
					ModelMap model)

	{
		try
		{
			int userId = getUserId(request);
			Callbacks callbacks = new Callbacks(userId);
			List<Callbacks> callbacksList =
					consumerService.getCallBacksDetails(callbacks);
			utilities.setSuccessResponse(response, callbacksList);
		} catch (Exception ex)
		{
			logger.error("getcallbacksdetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "customer";
	}

	@RequestMapping(value = "/getbookingdetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getbookingdetails(HttpServletRequest request, ModelMap model)

	{
		try
		{
			int userId = getUserId(request);
			Booking booking = new Booking(userId);
			List<Booking> bookingList =
					consumerService.getBookingDetails(booking);
			utilities.setSuccessResponse(response, bookingList);
		} catch (Exception ex)
		{
			logger.error("getcallbacksdetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "customer";
	}

	@RequestMapping(value = "/getmytravels", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getmytravels(HttpServletRequest request, ModelMap model)

	{
		try
		{
			Map map = new HashMap();

			int userId = getUserId(request);

			Booking booking = new Booking(userId);
			Callbacks callbacks = new Callbacks(userId);

			List<Callbacks> callbacksList =
					consumerService.getCallBacksDetails(callbacks);
			List<Booking> bookingList =
					consumerService.getBookingDetails(booking);

			map.put("callback", callbacksList);
			map.put("booking", bookingList);

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getcallbacksdetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "customer";
	}

}
