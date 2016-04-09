package travel.com.cache;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;

import travel.com.model.Activity;
import travel.com.service.TripService;

public class ActivityCache
{

	private static final Logger logger = Logger.getLogger(ActivityCache.class
			.getName());

	@Autowired
	@Qualifier("tripService")
	TripService tripService;

	@Cacheable(value = "activityCache")
	public List<Activity> getActivityDetails() throws Exception
	{
		List<Activity> activityList = null;
		try
		{
			activityList = tripService.getActivitys(new Activity("active"));
			logger.info("activityCache :" + activityList.size());
		} catch (Exception ex)
		{
			throw ex;
		}
		return activityList;
	}
}
