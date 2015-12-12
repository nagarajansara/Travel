package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface ActivityDAO
{
	List<Activity> getActivitys(Activity activity) throws Exception;

	Map<String, Object> getLeads(Booking booking) throws Exception;

	Map<String, Object> getVendorStatistic() throws Exception;
}
