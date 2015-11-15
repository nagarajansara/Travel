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

import org.json.simple.parser.JSONParser;



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
@RequestMapping("/city")
public class CityController
{


	private static final Logger logger = Logger.getLogger(CityController.class.getName());


	@Autowired
	@Qualifier("cityService")
	CityService cityService;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("response")
	Response response;


	@RequestMapping(value="/home", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCity(
		HttpServletRequest request,
		ModelMap model)
	 
	{
		try
		{
			List<City> cityList = cityService.getCity();		
			utilities.setSuccessResponse(response, cityList);	
		}
		catch(Exception ex)
		{
			logger.error("getCity :" +ex.getMessage());
		}
		model.addAttribute("model", response);
        return "home";
	}
	@RequestMapping(value="/getCityApi", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCityApi(
		HttpServletRequest request,
		@RequestParam(value="locationname") String locationname,
		ModelMap model)
	 
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray  jSONArray = new JSONArray();
		try
		{
			String status = "active";
			List<City> cityList = cityService.getCityApi(locationname, status);
			if(cityList != null && cityList.size() > 0)
			{
				for(int i = 0; i < cityList.size(); i++)
				{
					City city = (City)cityList.get(i);
					String cityName = (String)city.getCity();
					String stateName = (String)city.getState();

					int ID = (Integer)(city.getId());
					JSONObject jSONObject = new JSONObject();

					jSONObject.put("id", ID);
					jSONObject.put("text", cityName);
					jSONArray.put(jSONObject);
					
				}
				utilities.setSuccessResponse(response, jSONArray.toString());	
			}
			else
			{
				throw new ConstException(ConstException.ERR_CODE_NO_DATA, ConstException.ERR_MSG_NO_DATA);		
			}
		}
		catch(Exception ex)
		{
			logger.error("getCity :" +ex.getMessage());
			utilities.setErrResponse(ex, response);	
		}
		model.addAttribute("model", jSONArray.toString());
        return "home";
	}
	
}

