package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

@SuppressWarnings("unused")
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

	int getCredits(int userId) throws Exception;

	List<Login> getCreditsHistory(Login login) throws Exception;

	int getCreditHistoryNumEntries(Login login) throws Exception;

	void updateCredits(String email, int credits);

	List<Login> getUserDetailsBasedTripId(int tripId) throws Exception;

}
