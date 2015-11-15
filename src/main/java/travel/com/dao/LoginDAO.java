package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

public interface LoginDAO
{
	void insertCustomerData(Login login) throws Exception;

	void customerSignUpFB(Login login) throws Exception;

	int getUserId(String email, String openId) throws Exception;

	void insertVendorData(Login login) throws Exception;

	List<Login> validate(String email, String password, String isApproved)
			throws Exception;

	List<Login> getUserDetails(Login login) throws Exception;

	void updateProfile(Login login) throws Exception;
}
