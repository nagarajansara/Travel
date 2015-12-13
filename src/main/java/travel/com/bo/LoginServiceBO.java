package travel.com.bo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.*;

import travel.com.service.*;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

public class LoginServiceBO implements LoginService
{

	@Autowired
	@Qualifier("loginDAO")
	LoginDAO loginDAO;

	public void insertCustomerData(Login login) throws Exception
	{
		loginDAO.insertCustomerData(login);
	}

	public void customerSignUpFB(Login login) throws Exception
	{
		loginDAO.customerSignUpFB(login);
	}

	public int getUserId(String email, String openId) throws Exception
	{
		return loginDAO.getUserId(email, openId);
	}

	public void insertVendorData(Login login) throws Exception
	{
		loginDAO.insertVendorData(login);
	}

	public
			List<Login> validate(String email, String password,
					String isApproved) throws Exception
	{
		return loginDAO.validate(email, password, isApproved);
	}

	public List<Login> getUserDetails(Login login) throws Exception
	{
		return loginDAO.getUserDetails(login);
	}

	public void updateProfile(Login login) throws Exception
	{
		loginDAO.updateProfile(login);
	}

	public int getCredits(int userId) throws Exception
	{
		return loginDAO.getCredits(userId);
	}

	public List<Login> getCreditsHistory(Login login) throws Exception
	{
		return loginDAO.getCreditsHistory(login);
	}

	public int getCreditHistoryNumEntries(Login login) throws Exception
	{
		return loginDAO.getCreditHistoryNumEntries(login);
	}

	@Override
	public void updateCredits(String email, int credits) throws Exception
	{
		loginDAO.updateCredits(email, credits);
	}

}
