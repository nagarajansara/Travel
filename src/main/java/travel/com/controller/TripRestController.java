package travel.com.controller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ConstantException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import travel.com.service.*;
import travel.com.JMS.JMSProducer;
import travel.com.bean.ReviewsBean;
import travel.com.bean.TripDetailsBean;
import travel.com.bean.service.ReviewsBeanService;
import travel.com.bean.service.TripDetailsBeanService;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

@Controller
@RestController
@SuppressWarnings(
{ "unchecked", "unused" })
public class TripRestController
{

	private static final Logger logger = Logger
			.getLogger(TripRestController.class.getName());

	@Autowired
	@Qualifier("tripService")
	TripService tripService;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("response")
	Response response;

	@RequestMapping(value = "/getRestActivitys", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public List<Activity> getRestActivitys(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		List<Activity> activityList = null;
		try
		{
			activityList = tripService.getActivitys(new Activity("active"));
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("getRestActivitys : " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return activityList;
	}
}
