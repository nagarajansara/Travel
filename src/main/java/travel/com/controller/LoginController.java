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
@RequestMapping("/login")
@SuppressWarnings("unused")
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

	@RequestMapping(value = "/customerregister", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String customerRegister(HttpServletRequest request, ModelMap model)

	{

		String CUSTOMER_ROLE = "ROLE_CUSTOMER";
		Map<Object, Object> map = new HashMap<Object, Object>();
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
				Login login =
						new Login(email, firstName, lastName, password,
								cityName, role, ip);

				loginService.insertCustomerData(login);

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
				Login login =
						new Login(nameOrganization, email, firstName, lastName,
								password, stateName, address, phoneno, mobile,
								role, ip);

				loginService.insertVendorData(login);

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
			
			System.out.println("graph :" + graph);
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
			;
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

}
