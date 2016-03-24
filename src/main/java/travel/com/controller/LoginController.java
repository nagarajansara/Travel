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
import org.json.simple.JSONObject;
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
import travel.com.Redis.LoginValidateService;
import travel.com.bean.LoginValidatedBean;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

@Controller
@RequestMapping("/login")
@SuppressWarnings(
{ "unused", "unchecked" })
public class LoginController extends BaseController
{

	private static final Logger logger = Logger.getLogger(LoginController.class
			.getName());

	@Autowired
	@Qualifier("cityService")
	CityService cityService;

	@Autowired
	@Qualifier("loginService")
	LoginService loginService;

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
	@Qualifier("enquiryService")
	EnquiryService enquiryService;

	@Autowired
	@Qualifier("loginValidateService")
	LoginValidateService loginValidateService;

	@RequestMapping(value = "/customerregister", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String customerRegister(HttpServletRequest request, ModelMap model)

	{

		String CUSTOMER_ROLE = "ROLE_CUSTOMER";
		try
		{

			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String cityName = request.getParameter("cityName");
			String role = CUSTOMER_ROLE;
			String ip = utilities.getIpAddress(request);

			String[] params =
			{ "email", "firstName", "lastName", "password", "cityName" };

			if (!utilities.isChkRequsetParamsNull(request, params))
			{

				String UUID = utilities.UUID();

				Login login =
						new Login(email, firstName, lastName, password,
								cityName, role, ip, UUID);

				loginService.insertCustomerData(login);

				// ADD ACTIVE_MQ FOR VERIFICATION PURPOSE
				HashMap<String, String> map = new HashMap<String, String>();
				String filePath = "";
				String content = "";
				if (!role.equals("ROLE_VENDOR"))
				{
					map.put("VC_MACRO_URL",
							"http://"
									+ utilities.getServerName()
									+ "/travel/travelapi/login/activatecustomer/"
									+ UUID);
					map.put("VC_MACRO_NAME", firstName + " " + lastName);
					map.put("VC_MACRO_EMAIL", email);
					filePath = appProp.getHTMLDir() + "activation.html";
					content = utilities.getFileContent(filePath);
					content = utilities.replaceMacro(content, map);
				}

				// ADD MAIL QUEUE JMS
				utilities.setJMS_Enqueued(email, content,
						appProp.getAdminName(), "Welcome Mail",
						JMSProducer.EMAIL_QUEUE);

				utilities.setSuccessResponse(response);
			} else
			{
				throw new ConstException(ConstException.ERR_MSG_BLANK_DATA);
			}
		} catch (Exception ex)
		{
			logger.error("customerRegister :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);

		return "customerregister";
	}

	@RequestMapping(value = "/vendorregister", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String vendorregister(HttpServletRequest request, ModelMap model)

	{

		String CUSTOMER_ROLE = "ROLE_VENDOR";
		Map<Object, Object> map = new HashMap<Object, Object>();
		try
		{

			String nameOrganization =
					request.getParameter("nameoforganization");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String stateName = request.getParameter("stateName");
			String address = request.getParameter("ctAddress");
			String mobile = request.getParameter("mobile");
			String phoneno = request.getParameter("contact");
			String pancard = request.getParameter("pancard");
			String role = CUSTOMER_ROLE;
			String ip = utilities.getIpAddress(request);

			String[] params =
					{ "nameoforganization", "email", "firstName", "lastName",
							"password", "stateName", "ctAddress", "mobile",
							"contact", "pancard" };

			if (!utilities.isChkRequsetParamsNull(request, params))
			{
				String UUID = utilities.UUID();
				Login login =
						new Login(nameOrganization, email, firstName, lastName,
								password, stateName, address, phoneno, mobile,
								role, ip, UUID);

				loginService.insertVendorData(login);

				// ADD ACTIVE_MQ FOR VERIFICATION PURPOSE
				String content =
						"Please wait untill admin verify your details. Its takes couple of hour";

				utilities.setJMS_Enqueued(email, content,
						appProp.getAdminMailId(), appProp.getMailSubject(),
						JMSProducer.EMAIL_QUEUE);

				utilities.setSuccessResponse(response);
			} else
			{
				throw new ConstException(ConstException.ERR_MSG_BLANK_DATA);
			}

		} catch (Exception ex)
		{
			logger.error("vendorregister :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "vendorregister";
	}

	@RequestMapping(value = "/fbsignupcbk", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String fbsignupcbk(HttpServletRequest req, HttpServletResponse res,
			ModelMap model) throws Exception

	{
		Login login = null;
		try
		{

			String CUSTOMER_ROLE = "ROLE_CUSTOMER";
			String code = "";
			code = req.getParameter("code");
			if (code == null || code.equals(""))
			{
				throw new RuntimeException(
						"ERROR: Didn't get code parameter in callback.");
			}

			FBConnection fbConnection = new FBConnection();
			String accessToken = fbConnection.getAccessToken(code);

			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();

			System.out.println("accessToken :" + accessToken);
			System.out.println("code :" + code);

			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

			String email = fbProfileData.get("email");
			String firstName = fbProfileData.get("first_name");
			String lastName = fbProfileData.get("last_name");
			String cityName = "";
			String role = CUSTOMER_ROLE;
			String ip = utilities.getIpAddress(req);
			String openIdData = graph;
			String signupType = "fb";
			String openId = fbProfileData.get("id");

			login =
					new Login(email, firstName, lastName, cityName, role, ip,
							openId, signupType, openIdData);

			loginService.customerSignUpFB(login);

			int userId =
					loginService.getUserId(login.getEmail(), login.getOpenid());
			login.setId(userId);

			setUserSession(req, login);

			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			System.out.println("Exception User Exits :" + ex.getMessage());
			logger.error("fbsignupcbk :" + ex.getMessage());

			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				int userId =
						loginService.getUserId(login.getEmail(),
								login.getOpenid());
				login.setId(userId);
				setUserSession(req, login);
			}

			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "home";

	}

	@RequestMapping(value = "/validate", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String validate(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String isApproved = "yes";

			String[] params =
			{ "email", "password" };

			if (!utilities.isChkRequsetParamsNull(request, params))
			{
				List<Login> list =
						loginService.validate(email, password, isApproved);
				if (list != null && list.size() > 0)
				{
					Login login = (Login) list.get(0);
					setUserSession(request, login);
					utilities.setSuccessResponse(response);
				} else
				{
					throw new ConstException("Invalid Login");
				}
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_BLANK_DATA,
						ConstException.ERR_MSG_BLANK_DATA);
			}

		} catch (Exception ex)
		{
			logger.error("validate :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "home";
	}

	@RequestMapping(value = "/logout", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			HttpSession session = request.getSession(false);
			session.invalidate();
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("logout :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "home";
	}

	@RequestMapping(value = "/sessionfailure", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String sessionfailure(HttpServletRequest req,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		return "403";
	}

	@RequestMapping(value = "/chkJMSAPI", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String chkJMSAPI(HttpServletRequest req, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", "Nagarajan");
			jsonObject.put("subject", "Test email for my accout");
			jsonObject.put("content", "Mail content");
			jsonObject.put("fromEmail", "rnagarajan.ramalingam@gmail.com");
			jsonObject.put("toEmail", "nagarajan@vidcampaign.com");
			// jMSProducer.SendJMS_Message(jsonObject.toString());
		} catch (Exception ex)
		{
			logger.error("chkJMSAPI : " + ex.getMessage());
		}
		return "403";
	}

	// Get the new enquiry
	@RequestMapping(value = "/getpendingenquiry", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getPendingEnquiry(HttpServletRequest request,
			HttpServletResponse res, ModelMap model,
			@RequestParam(value = "startIndx", required = false,
					defaultValue = "0") int startIndx,
			@RequestParam(value = "endIndx", required = false,
					defaultValue = "10") int endIndx) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int userId = getUserId(request);

			String status = Enquiry.STATUS_PENDING;
			// Get New Enquiry (STATUS = PENDING)
			List<Enquiry> list =
					enquiryService.getPendingEnquiry(userId, status, startIndx,
							utilities.getDefaultMaxIndx());
			int numEntries =
					enquiryService.getPendingEnquiryNumEntries(userId, status);

			// Get Processing Enquiry ((STATUS = SENT) GET LAST ONE WEEK
			// RECORD))
			List<Enquiry> processingEnquiryDetails =
					enquiryService.getProcessingEnquiry(userId,
							Enquiry.STATUS_SENT, startIndx,
							utilities.getDefaultMaxIndx());
			int processingEnquiryDetailsNumEntries =
					enquiryService.getProcessingEnquiryNumEntries(userId,
							Enquiry.STATUS_SENT);

			// Get New Enquiry ((STATUS = SENT) GET BEFORE TWO OR THREEE WEEK
			// RECORDS))
			List<Enquiry> expiredEnquirys =
					enquiryService.getexpiredEnquiry(userId,
							Enquiry.STATUS_SENT, startIndx,
							utilities.getDefaultMaxIndx());
			int expiredEnquirysNumEntries =
					enquiryService.getexpiredEnquiryNumEntries(userId,
							Enquiry.STATUS_SENT);

			map.put("enquiry", list);
			map.put("numEntries", numEntries);

			map.put("processingEnquiryDetails", processingEnquiryDetails);
			map.put("processingEnquiryDetailsNumEntries",
					processingEnquiryDetailsNumEntries);

			map.put("expiredEnquirys", expiredEnquirys);
			map.put("expiredEnquirysNumEntries", expiredEnquirysNumEntries);

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getpendingenquiry :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "enquirystatus";
	}

	@RequestMapping(value = "/getpendingenquiryPagination/{currPage}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getpendingenquiryPagination(HttpServletRequest request,
			HttpServletResponse res, ModelMap model,
			@PathVariable("currPage") int currPage) throws Exception
	{
		try
		{
			currPage = currPage - 1;
			int startIndx =
					getStartIdx(currPage, utilities.getDefaultMaxIndx());
			Map<String, Object> map = new HashMap<String, Object>();
			int userId = getUserId(request);
			String status = Enquiry.STATUS_PENDING;

			List<Enquiry> list =
					enquiryService.getPendingEnquiry(userId, status, startIndx,
							utilities.getDefaultMaxIndx());
			int numEntries =
					enquiryService.getPendingEnquiryNumEntries(userId, status);

			// Get Processing Enquiry ((STATUS = SENT) GET LAST ONE WEEK
			// RECORD))
			List<Enquiry> processingEnquiryDetails =
					enquiryService.getProcessingEnquiry(userId,
							Enquiry.STATUS_SENT, 0,
							utilities.getDefaultMaxIndx());
			int processingEnquiryDetailsNumEntries =
					enquiryService.getProcessingEnquiryNumEntries(userId,
							Enquiry.STATUS_SENT);

			// Get New Enquiry ((STATUS = SENT) GET BEFORE TWO OR THREEE WEEK
			// RECORDS))
			List<Enquiry> expiredEnquirys =
					enquiryService.getexpiredEnquiry(userId,
							Enquiry.STATUS_SENT, 0,
							utilities.getDefaultMaxIndx());
			int expiredEnquirysNumEntries =
					enquiryService.getexpiredEnquiryNumEntries(userId,
							Enquiry.STATUS_SENT);

			map.put("enquiry", list);
			map.put("numEntries", numEntries);

			map.put("processingEnquiryDetails", processingEnquiryDetails);
			map.put("processingEnquiryDetailsNumEntries",
					processingEnquiryDetailsNumEntries);

			map.put("expiredEnquirys", expiredEnquirys);
			map.put("expiredEnquirysNumEntries", expiredEnquirysNumEntries);

			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getpendingenquiryPagination :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "enquirystatus";
	}

	@RequestMapping(value = "/getMoreSentEnquireDetails", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getMoreSentEnquireDetails(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		try
		{
			int vendorId = getUserId(request);
			String email = getSessionAttr(request, ATTR_EMAIL);
			String name =
					getSessionAttr(request, ATTR_FNAME) + " "
							+ getSessionAttr(request, ATTR_LNAME);

			// SEND THE EMAIL FOR ADMIN
			utilities
					.setJMS_Enqueued(appProp.getAdminMailId(),
							"Please send me the more email details", name,
							"More enquiry details. Please send this email ID: "
									+ email, JMSProducer.EMAIL_QUEUE);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("getMoreSentEnquireDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "enquirystatus";
	}

	@RequestMapping(value = "/activateEnquiry", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String activateEnquiry(HttpServletRequest request,
					HttpServletResponse res, ModelMap model, @RequestParam(
							value = "enquiryid") int enquiryId, @RequestParam(
							value = "email", defaultValue = "",
							required = false) String email, @RequestParam(
							value = "tripId") int tripId, @RequestParam(
							value = "mobileNo") String mobileNo)
					throws Exception
	{
		try
		{
			int userId = getUserId(request);
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("enquiryid", enquiryId);
			jsonObject.put("tripId", tripId);
			jsonObject.put("mobileNo", mobileNo);

			List<Login> list =
					loginService.getUserDetailsBasedEnquiry(enquiryId, tripId);

			if (list != null && list.size() > 0)
			{
				Login login = (Login) list.get(0);
				int credits = login.getTotalcredits();
				if (Enquiry.DEFAULT_ENQUIRY_DEDUCTION <= credits)
				{

					jsonObject.put("tripowneremail", login.getTripowneremail());
					jsonObject.put("enquiredemail", login.getEnquiredemail());

					// JMS QUEQUE RESEND
					/*
					 * jMSProducer.SendJMS_Message(jsonObject.toString(),
					 * jMSProducer.ACTIVATE_ENQUIRY_MESSAGE_QUEQUE);
					 */
					utilities.setSuccessResponse(response);

				} else
				{
					throw new ConstException(
							ConstException.ERR_CODE_NO_CREDITS,
							ConstException.ERR_MSG_NO_CREDITS);
				}
			}

		} catch (Exception ex)
		{
			logger.error("activateEnquiry :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "enquirystatus";
	}

	@RequestMapping(value = "/activatecustomer/{UUID}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String activateCustomer(HttpServletRequest request,
			@PathVariable String UUID, HttpServletResponse res, ModelMap model)
			throws Exception
	{
		try
		{

			int updatedCount = 0;
			updatedCount = loginService.activatecustomer(UUID);
			utilities.setSuccessResponse(response, updatedCount);

		} catch (Exception ex)
		{
			logger.error("activatecustomer :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "customerregisterresponse";
	}
}
