package travel.com.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import travel.com.util.*;
import travel.com.model.*;

@SuppressWarnings(
{ "unused", "unchecked" })
public interface VendorService
{
	List<Activity> getActivitys(Activity activity) throws Exception;

	Map<String, Object> getLeads(Booking booking) throws Exception;

	Map<String, Object> getVendorStatistic() throws Exception;

}
