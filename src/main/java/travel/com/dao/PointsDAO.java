package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

public interface PointsDAO
{

	List<Points> getPoints(Points points) throws Exception;

}
