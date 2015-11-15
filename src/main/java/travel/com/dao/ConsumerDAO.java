package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;


public interface ConsumerDAO
{
	List<Login> getProfile(int userId) throws Exception;
}


