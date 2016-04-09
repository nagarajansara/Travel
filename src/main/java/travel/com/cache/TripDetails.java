package travel.com.cache;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;

public class TripDetails
{
	private static final Logger logger = Logger.getLogger(TripDetails.class
			.getName());

	@Cacheable(value = "tripCache", key = "#id")
	public String getTripDetails(int id)
	{
		logger.info("get employee called");
		return "employee" + id;
	}

}
