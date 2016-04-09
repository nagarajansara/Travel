package travel.com.controller;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;
import org.hibernate.HibernateException;
import org.springframework.security.access.method.P;
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
import travel.com.JMS.JMSProducer;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

@Controller
@RequestMapping("/vendor")
@SuppressWarnings(
{ "unused", "unchecked" })
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

	@Autowired
	@Qualifier("reviewsService")
	ReviewsService reviewsService;

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
							utilities.getDefaultMinIndx(),
							utilities.getDefaultMaxIndx(), isSuccess);

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
			currPageNo = getStartIdx(currPageNo, utilities.getDefaultMaxIndx());
			int userId = getUserId(request);

			CommonMtd commonMtd = new CommonMtd();
			map =
					commonMtd.getListingDetails(userId, STATUS_ACTIVE,
							currPageNo, utilities.getDefaultMaxIndx(),
							isSuccess);

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
			List<SubActivity> subActivityList = commonMtd.getSubActivity();

			TripImage tripImage = new TripImage(listingId, "", "active");
			List<TripImage> tripImagesList =
					tripService.getTripImages(tripImage);

			map.put("activityList", activityList);
			map.put("subActivityList", subActivityList);
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
				String subactivityId =
						(String) request.getParameter("subactivitytype");

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
								guideLines, tripTitle, price,
								Integer.parseInt(subactivityId));

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
			int numEntries =
					loginService.getCreditHistoryNumEntries(new Login(userId));
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

	@RequestMapping(value = "/getDeals", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getDeals(HttpServletRequest request,
			@RequestParam(value = "startTitle", required = false,
					defaultValue = "CT_EMPTY") String startTitle,
			@RequestParam(value = "startIndx", required = false,
					defaultValue = "0") int startIndx,
			@RequestParam(value = "endIndx", required = false,
					defaultValue = "10") int endIndx, ModelMap model)
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray jSONArray = new JSONArray();
		String STATUS_ACTIVE = "active";
		List<Deals> dealsList = null;
		int numEntries = 0;
		try
		{
			int userId = getUserId(request);
			List<Trip> list = null;
			if (startTitle.equals("CT_EMPTY"))
			{
				Deals deals =
						new Deals(userId, STATUS_ACTIVE,
								utilities.getDefaultMinIndx(),
								utilities.getDefaultMaxIndx());
				dealsList = vendorService.getDeals(deals);
				numEntries = vendorService.getDealsEntries(deals);
				map.put("dealsList", dealsList);
				map.put("numEntries", numEntries);
			}
			if (startTitle != null && !startTitle.equals("CT_EMPTY"))
			{
				list =
						tripService.getTripDetailsTitles_AND_Id(userId,
								STATUS_ACTIVE, startTitle); // Trip Details
															// based userId
			}
			if (list != null && list.size() > 0
					&& !startTitle.equals("CT_EMPTY"))
			{
				for (int i = 0; i < list.size(); i++)
				{
					Trip trips = (Trip) list.get(i);
					String title = (String) trips.getTitle();
					int tripID = trips.getId();
					JSONObject jSONObject = new JSONObject();
					jSONObject.put("id", tripID);
					jSONObject.put("text", title);
					jSONArray.put(jSONObject);
				}
			} else
			{
				if (!startTitle.equals("CT_EMPTY"))
				{
					throw new ConstException(ConstException.ERR_CODE_NO_DATA,
							ConstException.ERR_MSG_NO_DATA);
				}
			}
			if (startTitle.equals("CT_EMPTY"))
			{
				utilities.setSuccessResponse(response, map);
			} else
			{
				utilities.setSuccessResponse(response, jSONArray.toString());
			}

		} catch (Exception ex)
		{
			logger.error("getDeals :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		if (startTitle.equals("CT_EMPTY"))
		{
			model.addAttribute("model", response);
		} else
		{
			model.addAttribute("model", jSONArray.toString());
		}
		return "deals";
	}

	@RequestMapping(value = "/getAllTripDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getAllTripDetails(HttpServletRequest request, @RequestParam(
			value = "startTitle") String startTitle, ModelMap model)
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray jSONArray = new JSONArray();
		String STATUS_ACTIVE = "active";
		int numEntries = 0;
		try
		{
			int userId = getUserId(request);
			List<Trip> list =
					tripService.getAllTripDetails(userId, STATUS_ACTIVE,
							startTitle); // Trip Details
											// based userId

			if (list != null && list.size() > 0)
			{
				for (int i = 0; i < list.size(); i++)
				{
					Trip trips = (Trip) list.get(i);
					String title = (String) trips.getTitle();
					int tripID = trips.getId();
					JSONObject jSONObject = new JSONObject();
					jSONObject.put("id", tripID);
					jSONObject.put("text", title);
					jSONArray.put(jSONObject);
				}
			} else
			{

				throw new ConstException(ConstException.ERR_CODE_NO_DATA,
						ConstException.ERR_MSG_NO_DATA);

			}
		} catch (Exception ex)
		{
			logger.error("getAllTripDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", jSONArray.toString());
		return "deals";
	}

	@RequestMapping(value = "/getDealsPageNo/{currPageNo}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getDealsPageNo(HttpServletRequest request,
			@PathVariable int currPageNo, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int userId = getUserId(request);
			int MAX_INDX = 10;
			String STATUS_ACTIVE = "active";
			List<Deals> dealsList = null;
			int numEntries = 0;
			currPageNo = currPageNo - 1;
			int startIndx =
					getStartIdx(currPageNo, utilities.getDefaultMaxIndx());
			Deals deals =
					new Deals(userId, STATUS_ACTIVE, startIndx,
							utilities.getDefaultMaxIndx());
			dealsList = vendorService.getDeals(deals);
			numEntries = vendorService.getDealsEntries(deals);
			map.put("dealsList", dealsList);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("updateDeals :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "deals";
	}

	@RequestMapping(value = "/updateDeals", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateDeals(HttpServletRequest request, @RequestParam(
			value = "offer_percentage") int offerPercentage, @RequestParam(
			value = "dealId") int dealId, ModelMap model)
	{
		try
		{
			vendorService.updateDeals(offerPercentage, dealId);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("updateDeals :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "deals";
	}

	@RequestMapping(value = "/addDeals", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addDeals(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int numEntries = 0;
			int vedorId = getUserId(request);
			String STATUS_ACTIVE = "active";
			int startIndx = 0;
			int endIndx = 10;
			Deals deals =
					new Deals(vedorId, STATUS_ACTIVE,
							utilities.getDefaultMinIndx(),
							utilities.getDefaultMaxIndx());

			List<Deals> dealsList = vendorService.getDeals(deals);
			numEntries = vendorService.getDealsEntries(deals);

			map.put("dealsList", dealsList);
			map.put("numEntries", numEntries);

			String[] requested_param =
			{ "offerPercentage", "offerdesc", "tripID" };

			if (!utilities.isChkRequsetParamsNull(request, requested_param))
			{
				int offerPercentage =
						Integer.parseInt(request
								.getParameter("offerPercentage"));
				String offerDesc = request.getParameter("offerdesc");
				int tripID = Integer.parseInt(request.getParameter("tripID"));

				deals = new Deals(vedorId, offerPercentage, offerDesc, tripID);
				vendorService.addDeals(deals);
			}

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("updateDeals :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "deals";
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

	@RequestMapping(value = "/getVendorStatistic", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String
			getVendorStatistic(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> responseMap = new HashMap<String, Object>();
			String STATUS_BOOKED = "booked";

			String[][] responseBookingList = null;
			String[][] responseEnquiryList = null;
			String[][] responseViewersList = null;

			int vendorId = getUserId(request);

			map = vendorService.getVendorStatistic(STATUS_BOOKED, vendorId);
			if (map != null && !map.isEmpty())
			{
				List<Booking> listBookings = (List<Booking>) map.get("booking");
				List<Enquiry> enquiries = (List<Enquiry>) map.get("enquiry");
				List<Viewers> viewers = (List<Viewers>) map.get("viewers");
				if (listBookings != null && listBookings.size() > 0)
				{
					responseBookingList = new String[listBookings.size()][2];
					for (int i = 0; i < listBookings.size(); i++)
					{
						Booking booking = (Booking) listBookings.get(i);
						int j = 0;
						responseBookingList[i][j] =
								(String) (booking.getCreatedat());
						j = j + 1;
						responseBookingList[i][j] =
								String.valueOf(booking.getTotals());
					}
				}
				if (enquiries != null && enquiries.size() > 0)
				{
					responseEnquiryList = new String[enquiries.size()][2];
					for (int i = 0; i < enquiries.size(); i++)
					{
						Enquiry enquiry = (Enquiry) enquiries.get(i);
						int j = 0;
						responseEnquiryList[i][j] =
								(String) (enquiry.getCreatedat());
						j = j + 1;
						responseEnquiryList[i][j] =
								String.valueOf(enquiry.getTotals());
					}
				}
				if (viewers != null && viewers.size() > 0)
				{
					responseViewersList = new String[viewers.size()][2];
					for (int i = 0; i < viewers.size(); i++)
					{
						Viewers viewers2 = (Viewers) viewers.get(i);
						int j = 0;
						responseViewersList[i][j] =
								(String) (viewers2.getCreatedat());
						j = j + 1;
						responseViewersList[i][j] =
								String.valueOf(viewers2.getTotals());
					}
				}
				responseMap.put(
						"bookingStatistic",
						(responseBookingList == null) ? utilities
								.getDefaultWord() : Arrays
								.deepToString(responseBookingList));
				responseMap.put(
						"enquiryStatistic",
						(responseEnquiryList == null) ? utilities
								.getDefaultWord() : Arrays
								.deepToString(responseEnquiryList));
				responseMap.put(
						"viewersStatistic",
						(responseViewersList == null) ? utilities
								.getDefaultWord() : Arrays
								.deepToString(responseViewersList));

				utilities.setSuccessResponse(response, responseMap);
			} else
			{
				throw new Exception();
			}

		} catch (Exception ex)
		{
			logger.error("getVendorStatistic :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "vendorstatistic";
	}

	@RequestMapping(value = "/getVendorReviews", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getVendorReviews(HttpServletRequest request, @RequestParam(
			value = "tripId") int tripId, ModelMap model)
	{
		try
		{
			int userId = getUserId(request);
			Map<String, Object> map = new HashMap<String, Object>();
			int numEntries = 0;
			Reviews reviews =
					new Reviews(tripId, utilities.getDefaultMinIndx(),
							utilities.getDefaultMaxIndx(), userId);
			List<Reviews> list = reviewsService.getVendorReviews(reviews);
			numEntries =
					reviewsService.getVendorReviewsNumEntries(tripId, userId);
			map.put("reviews", list);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getVendorReviews :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "getVendorReviews";
	}

	@RequestMapping(value = "/getVendorReviewsPageNo", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getVendorReviewsPageNo(HttpServletRequest request,
			@RequestParam(value = "tripId") int tripId, @RequestParam(
					value = "startIndx") int startIndx, ModelMap model)
	{
		try
		{
			int userId = getUserId(request);
			Map<String, Object> map = new HashMap<String, Object>();
			int numEntries = 0;
			Reviews reviews =
					new Reviews(tripId, getStartIdx(startIndx,
							utilities.getDefaultMaxIndx()),
							utilities.getDefaultMaxIndx(), userId);
			List<Reviews> list = reviewsService.getVendorReviews(reviews);
			numEntries =
					reviewsService.getVendorReviewsNumEntries(tripId, userId);
			map.put("reviews", list);
			map.put("numEntries", numEntries);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getVendorReviewsPageNo :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "getVendorReviews";

	}

	@RequestMapping(value = "/getCreditsReductionMoreDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getCreditsReductionMoreDetails(HttpServletRequest request,
			ModelMap model)

	{
		try
		{
			int vendorId = getUserId(request);
			String email = getSessionAttr(request, ATTR_EMAIL);
			String name =
					getSessionAttr(request, ATTR_FNAME) + " "
							+ getSessionAttr(request, ATTR_LNAME);

			// SEND NOFIFICATION FOR ADMIN
			utilities.setJMS_Enqueued(appProp.getAdminMailId(),
					"I need all the credits reduction details. This is my email ID :"
							+ email, name, "Request for more credits details",
					JMSProducer.EMAIL_QUEUE);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("requestCreditsreductionMoreDetails :"
					+ ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "credits";

	}

	@RequestMapping(value = "/addNewActivity", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public void addNewActivity(HttpServletRequest request, @RequestParam(
			value = "activityName") String activityName, ModelMap model)
	{
		try
		{
			int vendorId = getUserId(request);
			vendorService.addNewActivity(activityName);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addNewActivity :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

	}

	@RequestMapping(value = "/addNewSubActivity", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public void addNewSubActivity(HttpServletRequest request, @RequestParam(
			value = "activityName") String activityName, ModelMap model)
	{
		try
		{
			int vendorId = getUserId(request);
			vendorService.addNewSubActivity(activityName);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addNewSubActivity :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

	}

	@RequestMapping(value = "/getReviews", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getReviews(HttpServletRequest request, ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int vendorId = getUserId(request);
			int startIndx = utilities.getDefaultMinIndx();
			int endIndx = utilities.getDefaultMaxIndx();
			List<Reviews> list =
					vendorService.getReviews(vendorId, startIndx, endIndx);
			int numEntries = vendorService.getReviewsNumEntries(vendorId);
			map.put("numEntries", numEntries);
			map.put("reviewList", list);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getReviews :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "vendorreview";
	}

	@RequestMapping(value = "/getReviewsPagination/{currPage}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getReviewsPagination(HttpServletRequest request,
			@PathVariable("currPage") int currPage, ModelMap model)

	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			currPage = currPage - 1;
			int vendorId = getUserId(request);
			currPage = getStartIdx(currPage, utilities.getDefaultMaxIndx());
			int endIndx = utilities.getDefaultMaxIndx();
			List<Reviews> list =
					vendorService.getReviews(vendorId, currPage, endIndx);
			int numEntries = vendorService.getReviewsNumEntries(vendorId);
			map.put("numEntries", numEntries);
			map.put("reviewList", list);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getReviewsPagination :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "vendorreview";
	}

	@RequestMapping(value = "/getVendorDetailsBasedId/{vendorId}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getVendorDetailsBasedId(HttpServletRequest request,
			@PathVariable(value = "vendorId") int vendorId, ModelMap model)

	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<Login> list = vendorService.getVendorDetailsBasedId(vendorId);
			utilities.setSuccessResponse(response, list);
		} catch (Exception ex)
		{
			logger.error("getVendorDetailsBasedId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "viewvendor";
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
				List<SubActivity> subActivityList = getSubActivity();
				map.put("activityList", activityList);
				map.put("subActivityList", subActivityList);

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

		public List<SubActivity> getSubActivity() throws Exception
		{
			List<SubActivity> list = null;
			String STATUS_ACTIVE = "active";
			try
			{
				list = vendorService.getSubActivity(STATUS_ACTIVE);
			} catch (Exception ex)
			{
				throw ex;
			}
			return list;
		}
	}

}