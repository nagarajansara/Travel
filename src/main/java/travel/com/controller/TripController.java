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
import org.springframework.core.ConstantException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/trip")
@SuppressWarnings(
{ "unchecked", "unused" })
public class TripController extends BaseController
{

	private static final Logger logger = Logger.getLogger(TripController.class
			.getName());
	private boolean IS_REDIS_SERVER_ENABLE = false;

	@Autowired
	@Qualifier("cityService")
	CityService cityService;

	@Autowired
	@Qualifier("vendorService")
	VendorService vendorService;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("tripService")
	TripService tripService;

	@Autowired
	@Qualifier("response")
	Response response;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@Autowired
	@Qualifier("reviewsService")
	ReviewsService reviewsService;

	@Autowired
	@Qualifier("viewersService")
	ViewersService viewersService;

	@Autowired
	@Qualifier("enquiryService")
	EnquiryService enquiryService;

	@Autowired
	@Qualifier("jMSProducer")
	JMSProducer jMSProducer;

	@Autowired
	@Qualifier("loginService")
	LoginService loginService;

	@Autowired
	@Qualifier("tripDetailsBeanService")
	TripDetailsBeanService tripDetailsBeanService;

	@Autowired
	@Qualifier("reviewsBeanService")
	ReviewsBeanService reviewsBeanService;

	@RequestMapping(value = "/addTripDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addTripDetails(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception

	{
		String successResponse = "true";
		try
		{
			int userId = getUserId(request);
			Map<Object, Object> map = new HashMap<Object, Object>();
			map = tripService.uploadTripDetailsImage(request);
			if (map != null && map.size() > 0)
			{

				String fromDate = (String) map.get("fromdate");
				String toDate = (String) map.get("todate");
				String subActivityType = (String) map.get("subactivitytype");
				int startPoint =
						Integer.parseInt((String) map.get("startpoint")); // Start
																			// point
				String description = (String) map.get("description");
				String guideLines = (String) map.get("guideline");
				String totalDuration = (String) map.get("totalduration");
				String locationId = (String) map.get("city"); // Destination
				String activityId = (String) map.get("activitytype");
				String routecount = (String) map.get("routecount");
				String durationcount = (String) map.get("durationcount");
				String imageURLS = (String) map.get("urls");
				String[] imageURL = imageURLS.split(",");
				String tripTitle = (String) map.get("triptitle");
				double price = Double.parseDouble((String) map.get("price"));
				int routeCount = Integer.parseInt(routecount);
				String route = "";
				for (int i = 1; i <= routeCount; i++)
				{
					route = route + map.get("route" + i) + "->";
				}
				route = route.substring(0, route.lastIndexOf("->"));

				Trip trip =
						new Trip(userId, Integer.parseInt(activityId),
								Integer.parseInt(totalDuration),
								Integer.parseInt(locationId), fromDate, toDate,
								startPoint, route, description, guideLines,
								tripTitle, price,
								Integer.parseInt(subActivityType));

				Long tripId = tripService.addTripDetails(trip);
				for (int i = 0; i < imageURL.length; i++)
				{
					TripImage tripImage =
							new TripImage(tripId.intValue(), imageURL[i]);
					tripService.addTripImage(tripImage);
				}
				int durationCounts = Integer.parseInt(durationcount);
				List<Itenary> itenaryList = new ArrayList<Itenary>();
				for (int i = 1; i <= durationCounts; i++)
				{
					String daywiseDescription =
							(String) map.get("duration" + i);
					Itenary itenary =
							new Itenary(tripId, i, daywiseDescription);
					itenaryList.add(itenary);
				}

				tripService.addItenary(itenaryList);
			}
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("uploadTripDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
			successResponse = ex.getMessage();
		}
		model.addAttribute("model", response);
		return ("redirect:/travelapi/vendor/listing?success=" + successResponse + "");
	}

	@RequestMapping(value = "/updateTripDetailStatus", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public void updateTripDetailStatus(HttpServletRequest request,
			@RequestParam(value = "status") String status, @RequestParam(
					value = "tripId") int tripId, ModelMap model)
			throws Exception
	{
		try
		{
			int userId = getUserId(request);
			Trip trip = new Trip(tripId, userId, status);
			tripService.updateTripDetailStatus(trip);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("updateTripDetailStatus :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
	}

	@RequestMapping(value = "/uploadTripDetailsImage", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public void uploadTripDetailsImage(HttpServletRequest request)
			throws Exception
	{
		try
		{
			tripService.uploadTripDetailsImage(request);
		} catch (Exception ex)
		{
			logger.error("uploadTripDetailsImage :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}

	}

	@RequestMapping(value = "/getTripDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getTripDetails(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception

	{
		try
		{
			int START_INDEX = utilities.getDefaultMinIndx();
			int END_INDEX = utilities.getDefaultMaxIndx();
			Map<String, Object> map = new HashMap<String, Object>();
			CommonMthds commonMthds = new CommonMthds();
			map = commonMthds.getTripDetails(START_INDEX, END_INDEX);
			map.put("istripDetails", true);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getTripDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "listingview";
	}

	@RequestMapping(value = "/getTripDetailsPageno/{currPageNo}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getTripDetailsPageno(HttpServletRequest request,
			HttpServletResponse res, ModelMap model,
			@PathVariable int currPageNo) throws Exception
	{
		try
		{
			currPageNo = currPageNo - 1;
			currPageNo = getStartIdx(currPageNo, utilities.getDefaultMaxIndx());
			Map<String, Object> map = new HashMap<String, Object>();
			CommonMthds commonMthds = new CommonMthds();
			map =
					commonMthds.getTripDetails(currPageNo,
							utilities.getDefaultMaxIndx());
			map.put("istripDetails", true);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getTripDetailsPageno :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "listingview";
	}

	@RequestMapping(value = "/getFilterTripsDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getFilterTripsDetails(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception

	{
		try
		{
			int START_INDEX = utilities.getDefaultMinIndx();
			int END_INDEX = utilities.getDefaultMaxIndx();
			Map<String, Object> map = new HashMap<String, Object>();
			CommonMthds commonMthds = new CommonMthds();
			map =
					commonMthds.getTripDetailsBasedFilters(request, null,
							START_INDEX, END_INDEX);
			map.put("istripDetails", false);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getFilterTripsDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "listingview";
	}

	@RequestMapping(
			value = "/getFilterTripsDetailsPageNo/{requestedFilteredParam}/{currPageNo}",
			method =
			{ RequestMethod.GET, RequestMethod.POST })
	public
			String getFilterTripsDetailsPageNo(HttpServletRequest request,
					HttpServletResponse res, ModelMap model,
					@PathVariable int currPageNo,
					@PathVariable String requestedFilteredParam)
					throws Exception
	{
		try
		{
			currPageNo = currPageNo - 1;
			currPageNo = getStartIdx(currPageNo, utilities.getDefaultMaxIndx());
			Map<String, Object> map = new HashMap<String, Object>();
			CommonMthds commonMthds = new CommonMthds();
			org.json.JSONObject jsonObject =
					new org.json.JSONObject(requestedFilteredParam);

			map =
					commonMthds.getTripDetailsBasedFilters(null, jsonObject,
							currPageNo, utilities.getDefaultMaxIndx());
			map.put("istripDetails", false);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getFilterTripsDetailsPageNo :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "listingview";
	}

	@RequestMapping(value = "/getTripDetailsBasedId/{tripId}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getTripDetailsBasedId(HttpServletRequest request,
			@PathVariable("tripId") int tripId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{

			Map<String, Object> map = new HashMap<String, Object>();
			String STATUS_ACTIVE = "active";
			int credits = 0;
			Viewers viewers =
					new Viewers(tripId, credits, Viewers.STATUS_VIEWED);
			viewersService.insertViewers(viewers);
			Trip trip = new Trip(tripId, STATUS_ACTIVE);
			String tripIds = String.valueOf(tripId);

			// tripDetailsBeanService.find(tripIds) == null
			if (true)
			{
				List<Trip> list = new ArrayList<Trip>();
				list = tripService.getTripDetailsBasedId(trip);
				map.put("tripdetails", list);
				if (IS_REDIS_SERVER_ENABLE)
				{
					if (list != null && list.size() > 0)
					{
						for (int index = 0; index < list.size(); index++)
						{
							Trip tripObj = (Trip) list.get(index);
							String startrating =
									(tripObj.getStartrating() != null ? tripObj
											.getStartrating() : "0");
							String tripIdTemp = String.valueOf(tripObj.getId());
							TripDetailsBean tripDetailsBean =
									new TripDetailsBean(tripObj.getUserid(),
											startrating, tripIdTemp,
											tripObj.getTitle(),
											tripObj.getLocationid(),
											tripObj.getSubactivityid(),
											tripObj.getActivityid(),
											tripObj.getDuration(),
											tripObj.getFromdate(),
											tripObj.getTodate(),
											tripObj.getStartpoint(),
											tripObj.getRoute(),
											tripObj.getDescription(),
											tripObj.getPrice(),
											tripObj.getGuidelines(),
											tripObj.getStatus(),
											tripObj.getCreatedat(),
											tripObj.getOffer_percentage(),
											tripObj.getTocity(),
											tripObj.getTripimagename(),
											tripObj.getDaysdesc(),
											tripObj.getDateformat(),
											tripObj.getTodateformat(),
											tripObj.getViews(),
											tripObj.getViews(),
											tripObj.getReviews());
							tripDetailsBeanService.save(tripDetailsBean);

							logger.info("Data added in REDIS server : "
									+ tripIdTemp);

						}
					}
				}
			} else
			{
				logger.info("Tripdetails data extract from REDIS server");

				List<TripDetailsBean> list = new ArrayList<TripDetailsBean>();
				list.add(tripDetailsBeanService.find(String.valueOf(tripId)));
				map.put("tripdetails", list);
			}

			Reviews reviews =
					new Reviews(tripId, utilities.getDefaultMinIndx(),
							utilities.getDefaultMaxIndx());

			List<Reviews> reviewList =
					reviewsService.getReviewsBasedTripId(reviews);
			int numEntries = reviewsService.getNumEntries(reviews);
			map.put("reviewsdetails", reviewList);
			map.put("reviewsNumEntries", numEntries);

			map.put("reviewsCurrentPage", utilities.getDefaultMinIndx());
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getTripDetailsBasedId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "viewlisting";
	}

	@RequestMapping(value = "/addComments", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addComments(HttpServletRequest request,
			HttpServletResponse res,
			@RequestParam(value = "tripId") int tripId, @RequestParam(
					value = "username") String username, @RequestParam(
					value = "comment") String comment, @RequestParam(
					value = "startrating") String startrating, ModelMap model)
			throws Exception
	{
		try
		{
			Reviews reviews =
					new Reviews(tripId, username, comment, startrating);
			reviewsService.addComments(reviews);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addComments : " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "viewlisting";
	}

	@RequestMapping(value = "/addEnquiry", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addEnquiry(HttpServletRequest request,
			HttpServletResponse res,
			@RequestParam(value = "tripId") int tripId, @RequestParam(
					value = "username") String username, @RequestParam(
					value = "phoneno") String phoneno, @RequestParam(
					value = "email") String cutomerEmail, ModelMap model)
			throws Exception
	{
		try
		{
			if (true)
			{
				if (true)
				{

					{
						List<Trip> list =
								tripService.getCredits_AND_Email(tripId); // TODO

						String enquiryStatus = "";
						int enquiryDeduction = 0;
						if (list != null && list.size() > 0)
						{
							Trip trip = (Trip) list.get(0);
							int totalCredist = trip.getCredits();
							String tripOwnerEmail = trip.getEmail();
							String content = "";
							String toEmail = trip.getEmail();
							if (totalCredist >= Enquiry.DEFAULT_ENQUIRY_DEDUCTION)
							{
								try
								{

									HashMap<String, String> map =
											new HashMap<String, String>();
									String tripURL =
											(utilities.isServer()) ? ("<a href=\"http://www.saratravel.tk/travel/travelapi/trip/getTripDetailsBasedId/"
													+ tripId + "\">Trip Details</a>")
													: "<a href=\"http://localhost:8082/travel/travelapi/trip/getTripDetailsBasedId/"
															+ tripId
															+ "\">Trip Details</a>";

									map.put("CT_TRIP_URL_MACRO", tripURL);
									map.put("CT_CUST_EMAIL_MACRO", cutomerEmail);
									map.put("CT_MOBILE_MACRO", phoneno);

									String filePath =
											appProp.getHTMLDir()
													+ "enquiry.html";
									content =
											utilities.getFileContent(filePath);
									content =
											utilities
													.replaceMacro(content, map);

									String[] toAddr =
									{ toEmail };
									enquiryStatus = Enquiry.STATUS_SENT;
									enquiryDeduction =
											Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
									totalCredist =
											totalCredist
													- Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
									loginService.updateCredits(tripOwnerEmail,
											totalCredist);

									utilities.setJMS_Enqueued(toEmail, content,
											appProp.getAdminName(),
											appProp.getMailSubject(),
											JMSProducer.EMAIL_QUEUE);

								} catch (Exception ex)
								{
									throw ex;
								}

							} else
							{

								HashMap<String, String> map =
										new HashMap<String, String>();

								String filePath =
										appProp.getHTMLDir()
												+ "pendingenquiry.html";
								content = utilities.getFileContent(filePath);
								content = utilities.replaceMacro(content, map);

								// SEND EMAIL
								String[] toAddr =
								{ toEmail };

								enquiryStatus = Enquiry.STATUS_PENDING;
								enquiryDeduction =
										Enquiry.DEFAULT_ENQUIRY_NON_DEDUCTION;

								utilities.setJMS_Enqueued(toEmail, content,
										appProp.getAdminName(),
										"Enquiry pending",
										JMSProducer.EMAIL_QUEUE);
							}

							Enquiry enquiry =
									new Enquiry(tripId, username,
											enquiryStatus, phoneno,
											enquiryDeduction, cutomerEmail);
							enquiryService.addEnquiry(enquiry);
						}

					}

				} else
				{
					throw new ConstException(
							ConstException.ERR_CODE_NO_CREDITS,
							ConstException.ERR_MSG_NO_CREDITS);
				}
			}
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addEnquiry : " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "viewlisting";
	}

	@RequestMapping(value = "/getCommentsPagno", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getCommentsPagno(HttpServletRequest request,
			HttpServletResponse res,
			@RequestParam(value = "tripId") int tripId, @RequestParam(
					value = "startIndx") int startIndx, ModelMap model)
			throws Exception
	{
		try
		{
			int currentPage =
					getStartIdx(startIndx, utilities.getDefaultMaxIndx());
			Reviews reviews =
					new Reviews(tripId, currentPage,
							utilities.getDefaultMaxIndx());
			List<Reviews> reviewList = reviewsService.getCommentsPagno(reviews);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("reviewList", reviewList);
			map.put("currentPage", startIndx);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getCommentsPagno : " + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "viewlisting";
	}

	class CommonMthds
	{
		public
				Map<String, Object> getTripDetails(int START_INDEX,
						int END_INDEX) throws Exception
		{
			Map<String, Object> map = new HashMap<String, Object>();
			String STATUS_ACTIVE = "active";
			try
			{
				Trip trip = new Trip(STATUS_ACTIVE, START_INDEX, END_INDEX);
				List<Trip> list = tripService.getTripDetails_Users(trip);
				Trip trip_NUMENTRIES = new Trip(STATUS_ACTIVE);
				int numEntries =
						tripService
								.getAllValidTripDetailsCount(trip_NUMENTRIES);

				map.put("tripDetails", list);
				map.put("numEntries", numEntries);

				/******************************************* Common Code For List Viewing Page ***********/

				Activity activity = new Activity(STATUS_ACTIVE);
				List<Activity> lists = vendorService.getActivitys(activity);
				List<SubActivity> subActivity =
						vendorService.getSubActivity(STATUS_ACTIVE);
				map.put("activityType", lists);
				map.put("subActivityType", subActivity);

				/******************************************* End ********************************/
			} catch (Exception ex)
			{
				throw ex;
			}
			return map;
		}

		public Map<String, Object> getTripDetailsBasedFilters(
				HttpServletRequest request, org.json.JSONObject jsonObject,
				int START_INDEX, int END_INDEX) throws Exception
		{
			String STATUS_ACTIVE = "active";
			Map<String, Object> tripTable = new HashMap<String, Object>();
			Map<String, Object> activityTable = new HashMap<String, Object>();
			Map<String, Object> subActivityTable =
					new HashMap<String, Object>();
			Map<String, Object> priceMap = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			try
			{
				String[] requestParams_tripTable =
				{ "startpoint", "fromdate" };

				String[] requestParam_activity =
				{ "activitytype" };

				String[] requestParam_subActivity =
				{ "subactivitytype" };

				String[] requestParam_price =
				{ "fromprice", "toprice" };

				if (request != null) // check request have the parameter
				{

					for (int i = 0; i < requestParams_tripTable.length; i++)
					{
						String requestValues =
								request.getParameter(requestParams_tripTable[i]);
						if (requestValues != null
								&& !requestValues.equals(utilities
										.getDefaultWord())
								&& !requestValues.equals("")
								&& requestValues.length() > 0)
						{
							tripTable.put(requestParams_tripTable[i],
									requestValues);
						}
					}
					for (int i = 0; i < requestParam_activity.length; i++)
					{
						String[] requestValues =
								request.getParameterValues(requestParam_activity[i]);
						if (requestValues != null && requestValues.length > 0)
						{
							activityTable
									.put(requestParam_activity[i],
											utilities
													.convertToCommaDelimited(requestValues));
						}
					}
					for (int i = 0; i < requestParam_subActivity.length; i++)
					{
						String[] requestValues =
								request.getParameterValues(requestParam_subActivity[i]);
						if (requestValues != null && requestValues.length > 0)
						{
							subActivityTable
									.put(requestParam_subActivity[i],
											utilities
													.convertToCommaDelimited(requestValues));
						}
					}
					for (int i = 0; i < requestParam_price.length; i++)
					{
						String requestValues =
								request.getParameter(requestParam_price[i]);
						if (requestValues != null
								&& !requestValues.equals(utilities
										.getDefaultWord()))
						{
							priceMap.put(requestParam_price[i], requestValues);
						}
					}
				} else
				// Filter pagination have the parameter
				{
					for (int i = 0; i < requestParams_tripTable.length; i++)
					{
						String requestValues =
								(String) jsonObject
										.get(requestParams_tripTable[i]);
						if (requestValues != null
								&& requestValues.length() > 0
								&& !requestValues.equals(utilities
										.getDefaultWord()))
						{
							tripTable.put(requestParams_tripTable[i],
									requestValues);
						}
					}
					for (int i = 0; i < requestParam_activity.length; i++)
					{
						String requestValues =
								(String) jsonObject
										.get(requestParam_activity[i]);

						if (!requestValues.equals(utilities.getDefaultWord())
								&& requestValues != null
								&& requestValues.length() > 0)
						{
							activityTable.put(requestParam_activity[i],
									requestValues);
						}
					}
					for (int i = 0; i < requestParam_subActivity.length; i++)
					{
						String requestValues =
								(String) jsonObject
										.get(requestParam_subActivity[i]);

						if (!requestValues.equals(utilities.getDefaultWord())
								&& requestValues != null
								&& requestValues.length() > 0)
						{
							subActivityTable.put(requestParam_subActivity[i],
									requestValues);
						}
					}
					for (int i = 0; i < requestParam_price.length; i++)
					{
						String requestValues =
								(String) jsonObject.get(requestParam_price[i]);
						if (requestValues != null
								&& !requestValues.equals(utilities
										.getDefaultWord()))
						{
							priceMap.put(requestParam_price[i], requestValues);
						}

					}
				}

				List<Trip> list =
						tripService.getFilterTripsDetails(tripTable,
								activityTable, subActivityTable, STATUS_ACTIVE,
								priceMap, START_INDEX, END_INDEX);

				int numEntries =
						tripService.getFilterTripsDetailsnumEntries(tripTable,
								activityTable, subActivityTable, STATUS_ACTIVE,
								priceMap);

				/******************************************* Common Code For List Viewing Page ***********/

				Activity activity = new Activity(STATUS_ACTIVE);
				List<Activity> lists = vendorService.getActivitys(activity);
				List<SubActivity> subActivity =
						vendorService.getSubActivity(STATUS_ACTIVE);
				map.put("activityType", lists);
				map.put("subActivityType", subActivity);

				/******************************************* END ********************************/
				Map requestedParam =
						_getRequestedFilteredParam(request, jsonObject,
								tripTable, activityTable, subActivityTable,
								priceMap, numEntries);

				map.put("requestedParam", requestedParam);
				map.put("tripDetails", list);
				map.put("numEntries", numEntries);

			} catch (Exception ex)
			{
				throw ex;
			}
			return map;
		}

		private Map _getRequestedFilteredParam(HttpServletRequest request,
				org.json.JSONObject jsonObject, Map<String, Object> tripTable,
				Map<String, Object> activityTable,
				Map<String, Object> subActivityTable,
				Map<String, Object> priceMap, int numEntries) throws Exception
		{
			Map requestedParam = new HashMap();

			try
			{
				/*************************** REQUESTED PARAMS *************************************/

				if (request != null)
				{
					requestedParam.put("locationId",
							request.getParameter("startpoint"));
					requestedParam.put("locationName",
							request.getParameter("startLocation"));
					requestedParam
							.put("activityIds",
									(activityTable.get("activitytype") == null ? utilities
											.getDefaultWord() : activityTable
											.get("activitytype")));
					requestedParam
							.put("subActivityIds",
									(subActivityTable.get("subactivitytype") == null ? utilities
											.getDefaultWord()
											: subActivityTable
													.get("subactivitytype")));
					requestedParam
							.put("fromdate",
									request.getParameter("fromdate") == null ? utilities
											.getDefaultWord() : request
											.getParameter("fromdate"));

					requestedParam.put("numEntries", numEntries);
					requestedParam.put("fromPrice", priceMap.get("fromprice"));
					requestedParam.put("toPrice", priceMap.get("toprice"));

				} else
				{
					requestedParam.put("locationId",
							jsonObject.get("startpoint"));
					requestedParam.put("locationName",
							jsonObject.get("startLocation"));

					requestedParam
							.put("activityIds",
									(activityTable.get("activitytype") == null ? utilities
											.getDefaultWord() : activityTable
											.get("activitytype")));
					requestedParam
							.put("subActivityIds",
									(subActivityTable.get("subactivitytype") == null ? utilities
											.getDefaultWord()
											: subActivityTable
													.get("subactivitytype")));
					requestedParam.put(
							"fromdate",
							jsonObject.get("fromdate") == null ? utilities
									.getDefaultWord() : jsonObject
									.get("fromdate"));

					requestedParam.put("numEntries", numEntries);
					requestedParam.put("fromPrice", priceMap.get("fromprice"));
					requestedParam.put("toPrice", priceMap.get("toprice"));

				}

				/*************************** REQUESTED PARAMS ENDS *************************************/
			} catch (Exception ex)
			{
				throw ex;
			}
			return requestedParam;
		}
	}

}
