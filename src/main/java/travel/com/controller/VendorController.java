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
@RequestMapping("/vendor")
@SuppressWarnings("unused")
public class VendorController extends BaseController
{
	private static final Logger logger = Logger
			.getLogger(VendorController.class.getName());

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
	@Qualifier("cityService")
	CityService cityService;

	@Autowired
	@Qualifier("loginService")
	LoginService loginService;

	@RequestMapping(value = "/listing", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request,
			@RequestParam(value = "success", required = false,
					defaultValue = "false") boolean isSuccess,
			@RequestParam(value = "startIndx", required = false,
					defaultValue = "0") int startIndx,
			@RequestParam(value = "endIndx", required = false,
					defaultValue = "10") int endIndx, ModelMap model)

	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			String STATUS_ACTIVE = "active";
			int userId = getUserId(request);

			CommonMtd commonMtd = new CommonMtd();
			map =
					commonMtd.getListingDetails(userId, STATUS_ACTIVE,
							startIndx, endIndx, isSuccess);

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("vendor listing :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "vendor";
	}

	@RequestMapping(value = "/listing/{currPageNo}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getOrder(@PathVariable int currPageNo,
			HttpServletRequest request,
			@RequestParam(value = "success", required = false,
					defaultValue = "false") boolean isSuccess,
			@RequestParam(value = "endIndx", required = false,
					defaultValue = "10") int endIndx, ModelMap model)
	{
		String STATUS_ACTIVE = "active";
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			currPageNo = currPageNo - 1;
			currPageNo = getStartIdx(currPageNo, endIndx);
			int userId = getUserId(request);

			CommonMtd commonMtd = new CommonMtd();
			map =
					commonMtd.getListingDetails(userId, STATUS_ACTIVE,
							currPageNo, endIndx, isSuccess);

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("listing Pagination :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "vendor";
	}

	@RequestMapping(value = "/editlisting/{listingId}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String editlisting(@PathVariable int listingId,
			HttpServletRequest request, ModelMap model)
	{
		String status = "active";
		try
		{
			int userId = getUserId(request);

			Map<String, Object> map = new HashMap<String, Object>();
			Trip trip = new Trip(listingId, userId, status);
			List<Trip> trips = tripService.getUpdatingTripDetailsBasedId(trip);

			CommonMtd commonMtd = new CommonMtd();
			List<Activity> activityList = commonMtd.getActivityList();

			TripImage tripImage = new TripImage(listingId, "", "active");
			List<TripImage> tripImagesList =
					tripService.getTripImages(tripImage);

			map.put("activityList", activityList);
			map.put("trip", trips);
			map.put("tripImagesList", tripImagesList);

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("editlisting :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "editlisting";
	}

	@RequestMapping(value = "/updatelisting/{tripId}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updatelisting(@PathVariable Long tripId,
			HttpServletRequest request, ModelMap model)
	{
		String status = "active";
		boolean isUpdateSuccess = false;
		try
		{
			int userId = getUserId(request);
			Map<Object, Object> map = new HashMap<Object, Object>();
			String[] params =
			{ "fromdate", "todate", "startpoint", "description", "guideline" }; // ADD
			if (!utilities.isChkRequsetParamsNull(request, params))
			{
				String fromDate = (String) request.getParameter("fromdate");
				String toDate = (String) request.getParameter("todate");
				int startPoint =
						Integer.parseInt((String) request
								.getParameter("startpoint"));
				String description =
						(String) request.getParameter("description");
				String guideLines = (String) request.getParameter("guideline");
				String totalDuration =
						(String) request.getParameter("totalduration");
				String locationId = (String) request.getParameter("city");
				String activityId =
						(String) request.getParameter("activitytype");
				String routeDetails = (String) request.getParameter("route1");
				String durationcount =
						(String) request.getParameter("totalduration");
				String tripTitle =
						(String) request.getParameter("edittriptitle");
				int durationCounts = Integer.parseInt(durationcount);
				double price =
						Double.parseDouble(request.getParameter("price"));
				List<Itenary> itenaryList = new ArrayList<Itenary>();
				for (int i = 1; i <= durationCounts; i++)
				{
					String daywiseDescription =
							(String) request.getParameter("duration" + i);
					Itenary itenary =
							new Itenary(tripId, i, daywiseDescription);
					itenaryList.add(itenary);
				}
				Trip trip =
						new Trip(tripId.intValue(), userId,
								Integer.parseInt(activityId),
								Integer.parseInt(totalDuration),
								Integer.parseInt(locationId), fromDate, toDate,
								startPoint, routeDetails, description,
								guideLines, tripTitle, price);

				tripService.update(trip);
				tripService.deleteItenary(tripId);
				tripService.addItenary(itenaryList);
			}
			isUpdateSuccess = true;
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("updatelisting :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return (isUpdateSuccess ? "updatesuccess" : "updateerror");
	}

	@RequestMapping(value = "/updatelistingimage/{tripId}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updatelistingimage(@PathVariable int tripId,
			HttpServletRequest request, ModelMap model)
	{
		String status = "active";
		boolean isUpdateSuccess = false;
		try
		{
			Map<Object, Object> map = new HashMap<Object, Object>();
			map = tripService.uploadTripDetailsImage(request);
			String imageURLS = (String) map.get("urls");
			String[] imageURL = imageURLS.split(",");
			if (imageURL != null && imageURL.length > 0)
			{
				for (int i = 0; i < imageURL.length; i++)
				{
					TripImage tripImage = new TripImage(tripId, imageURL[i]);
					tripService.addTripImage(tripImage);
				}
			}
			utilities.setSuccessResponse(response);
			isUpdateSuccess = true;
		} catch (Exception ex)
		{
			logger.error("updatelistingimage :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return (isUpdateSuccess ? "updatesuccess" : "updateerror");
	}

	@RequestMapping(value = "/deleteTripImage", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String deleteTripImage(@RequestParam(value = "tripId") int tripId,
			@RequestParam(value = "id") int id, HttpServletRequest request,
			ModelMap model)
	{
		try
		{
			TripImage tripImage = new TripImage(tripId, id, "deactive");
			tripService.deleteTripImage(tripImage);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("deleteTripImage :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "updatesuccess";
	}

	@RequestMapping(value = "/getProfile", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getProfile(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			final String role_vendor = "ROLE_VENDOR";
			Login login = new Login((getUserId(request)), role_vendor);
			List<Login> userDetails = loginService.getUserDetails(login);
			map.put("userDetails", userDetails);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getVendorProfile : " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "profile";
	}

	@RequestMapping(value = "/getCredits", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getCredits(HttpServletRequest request, ModelMap model)
	{
		try
		{
			int userId = getUserId(request);
			int MAX_CREDITS_HISTORY = 10;
			Map<String, Object> map = new HashMap<String, Object>();
			int credits = loginService.getCredits(userId);
			List<Login> list =
					loginService.getCreditsHistory(new Login(userId));
			int numEntries = loginService.getCreditHistoryNumEntries(new Login(userId));
			map.put("credits", credits);
			map.put("history", list);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getCredits_VENDOR: " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "credits";
	}

	@RequestMapping(value = "/updateProfile", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateProfile(HttpServletRequest request, ModelMap model)
	{
		try
		{
			String[] requestParam =
					{ "fname", "lname", "addressTxt", "phoneTxt", "pincode",
							"state", "organization", "city", "pancardno" };
			if (!utilities.isChkRequsetParamsNull(request, requestParam))
			{
				int userId = getUserId(request);
				Login login =
						new Login(request.getParameter("fname"),
								request.getParameter("lname"),
								request.getParameter("addressTxt"),
								request.getParameter("phoneTxt"),
								request.getParameter("pincode"),
								request.getParameter("state"),
								request.getParameter("organization"),
								request.getParameter("city"),
								request.getParameter("pancardno"), userId);

				loginService.updateProfile(login);
				Map<String, Object> map = new HashMap<String, Object>();
				final String role_vendor = "ROLE_VENDOR";
				Login logins = new Login((getUserId(request)), role_vendor);
				List<Login> userDetails = loginService.getUserDetails(logins);
				map.put("userDetails", userDetails);
				utilities.setSuccessResponse(response, map);
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_NO_DATA,
						ConstException.ERR_MSG_NO_DATA);
			}
		} catch (Exception ex)
		{
			logger.error("updateVendorProfile : " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "profile";
	}

	class CommonMtd
	{
		public Map<String, Object> getListingDetails(int userId, String status,
				int startIndx, int maxIndx, boolean isSuccess) throws Exception
		{
			Map<String, Object> map = new HashMap<String, Object>();
			try
			{

				List<Activity> activityList = getActivityList();
				map.put("activityList", activityList);
				Trip trip = new Trip(userId, status, startIndx, maxIndx);
				Map<String, Object> tripMap = tripService.getTripDetails(trip);
				List<Trip> trips = (List<Trip>) tripMap.get("trips");
				int numEntries = (Integer) tripMap.get("numEntries");
				map.put("tipDetails", trips);
				map.put("numEntries", numEntries);
				if (isSuccess)
				{
					map.put("added", "Successfully added");

				}

			} catch (Exception ex)
			{
				throw ex;
			}
			return map;
		}

		public List<Activity> getActivityList() throws Exception
		{
			List<Activity> activityList = null;
			String STATUS_ACTIVE = "active";
			try
			{
				Activity activity = new Activity(STATUS_ACTIVE);
				activityList = vendorService.getActivitys(activity);
			} catch (Exception ex)
			{
				throw ex;
			}
			return activityList;
		}
	}

}