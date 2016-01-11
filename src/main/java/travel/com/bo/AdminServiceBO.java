package travel.com.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import travel.com.dao.AdminDAO;
import travel.com.model.Admin;
import travel.com.service.AdminService;

public class AdminServiceBO implements AdminService
{
	@Autowired
	@Qualifier("adminDAO")
	AdminDAO adminDAO;

	@Override
	public List<Admin> getContacts() throws Exception
	{
		return adminDAO.getContacts();
	}

	@Override
	public List<Admin> getAdminLoginValidate(Admin admin) throws Exception
	{
		return adminDAO.getAdminLoginValidate(admin);
	}

}
