package travel.com.service;

import java.util.List;

import travel.com.model.Enquiry;

public interface EnquiryService
{
	void addEnquiry(Enquiry enquiry) throws Exception;

	List<Enquiry> getPendingEnquiry(int userId, String status) throws Exception;

	void updateEnquiryStatus(Enquiry enquiry) throws Exception;

}
