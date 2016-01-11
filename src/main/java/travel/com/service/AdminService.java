package travel.com.service;

import java.util.List;

import travel.com.model.Admin;

public interface AdminService
{

	List<Admin> getContacts() throws Exception;
	List<Admin> getAdminLoginValidate(Admin admin) throws Exception;
}
