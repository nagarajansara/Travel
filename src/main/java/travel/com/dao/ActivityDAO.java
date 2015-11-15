package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface ActivityDAO
{
	List<Activity> getActivitys(Activity activity) throws Exception;
}
