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
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/admin")
@SuppressWarnings(
{ "unused", "unchecked" })
public class AdminController extends BaseController
{
	private static final Logger logger = Logger.getLogger(AdminController.class
			.getName());

	@Autowired
	@Qualifier("adminService")
	AdminService adminService;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("response")
	Response response;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@RequestMapping(value = "/getContacts", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public Response getContacts(HttpServletRequest request,
			HttpServletResponse res, ModelMap model)
	{
		try
		{
			utilities.setAccessCrossDomainResponse(res); // Give cross domain
			// access
			List<Admin> list = adminService.getContacts();
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("getContacts :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return response;
	}

	@RequestMapping(value = "/getAdminLoginValidate/{userName}/{password}",
			method =
			{ RequestMethod.GET, RequestMethod.POST })
	public Response getAdminLoginValidate(@PathVariable String userName,
			@PathVariable String password, HttpServletRequest request,
			HttpServletResponse res, ModelMap model)
	{
		try
		{
			utilities.setAccessCrossDomainResponse(res); // Give cross domain
															// access

			String STATUS_ACTIVE = "active";
			Admin admin = new Admin(userName, password, STATUS_ACTIVE);
			List<Admin> list = adminService.getAdminLoginValidate(admin);
			if (list != null && list.size() > 0)
			{
				Admin adminList = (Admin) list.get(0);
				setUserSession(request, adminList);
				String email = getSessionAttr(request, ATTR_EMAIL);
				System.out.println("ATTR_EMAIL :" + email);
				utilities.setSuccessResponse(response, list);
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_INVALID_LOGIN,
						ConstException.ERR_MSG_INVALID_LOGIN);
			}

		} catch (Exception ex)
		{
			logger.error("getAdminLoginValidate :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return response;
	}

	@RequestMapping(value = "/getAdminLoginStatus/{userName}", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			Response getAdminLoginStatus(@PathVariable String userName,
					HttpServletRequest request, HttpServletResponse res,
					ModelMap model)
	{
		try
		{
			utilities.setAccessCrossDomainResponse(res); // Give cross domain
			// access
			String email = getSessionAttr(request, ATTR_EMAIL);
			if (email != null && email.equals(userName))
			{
				utilities.setSuccessResponse(response);
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_INVALID_LOGIN,
						ConstException.ERR_MSG_INVALID_LOGIN);
			}

		} catch (Exception ex)
		{
			logger.error("getAdminLoginStatus :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return response;
	}
}
