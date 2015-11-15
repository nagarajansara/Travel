package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface ItenaryDAO
{
	void addItenary(List<Itenary> itenaryList) throws Exception;
	void deleteItenary(Long tripId) throws Exception;
}
