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
			utilities.setSuccessResponse(response, login);
		} catch (Exception ex)
		{
			logger.error("getprofile :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "customer";
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
