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

	Map<String, Object> getVendorStatistic(String status, int vendorId)
			throws Exception;

	List<Deals> getDeals(Deals deals) throws Exception;

	void updateDeals(int offerPercentage, int id) throws Exception;

	int getDealsEntries(Deals deals) throws Exception;
	
	void addDeals(Deals deals) throws Exception;
}
