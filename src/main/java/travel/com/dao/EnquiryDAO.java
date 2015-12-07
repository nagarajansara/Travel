package travel.com.dao;

import java.util.List;

import travel.com.model.Enquiry;

public interface EnquiryDAO
{

	void addEnquiry(Enquiry enquiry) throws Exception;

	List<Enquiry> getPendingEnquiry(int userId, String status);

	void updateEnquiryStatus(Enquiry enquiry) throws Exception;

}
