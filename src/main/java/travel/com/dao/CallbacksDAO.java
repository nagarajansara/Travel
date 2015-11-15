package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface CallbacksDAO
{
	List<Callbacks> getCallBacksDetails(Callbacks callbacks) throws Exception;	
}
