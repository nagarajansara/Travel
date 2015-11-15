package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface TripImageDAO
{
	void addTripImage(TripImage tripImage) throws Exception;
	List<TripImage> getTripImages(TripImage tripImage) throws Exception;
	void deleteTripImage(TripImage tripImage) throws Exception;
}
