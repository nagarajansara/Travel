package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

public interface CityDAO
{
	List<City> getCity() throws Exception;

	List<City> getCityApi(String locationname, String status) throws Exception;
}
