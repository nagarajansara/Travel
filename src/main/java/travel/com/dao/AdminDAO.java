package travel.com.dao;

import java.util.List;

import travel.com.model.Admin;

public interface AdminDAO
{

	List<Admin> getContacts() throws Exception;

	List<Admin> getAdminLoginValidate(Admin admin) throws Exception;

}
