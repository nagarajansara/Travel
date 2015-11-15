package travel.com.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import travel.com.util.*;
import travel.com.model.*;


public interface VendorService
{
	List<Activity> getActivitys(Activity activity) throws Exception;
}
