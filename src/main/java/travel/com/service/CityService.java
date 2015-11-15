package travel.com.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import travel.com.util.*;
import travel.com.model.*;

public interface CityService
{
	List<City> getCity() throws Exception;	
	List<City> getCityApi(String locationname, String status) throws Exception;
}


