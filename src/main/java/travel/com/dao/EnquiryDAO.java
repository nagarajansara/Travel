package travel.com.dao;

import java.util.List;

import travel.com.model.Enquiry;

public interface EnquiryDAO
{

	void addEnquiry(Enquiry enquiry) throws Exception;

	List<Enquiry> getPendingEnquiry(int userId, String status, int startIndx,
			int endIndx);

	void updateEnquiryStatus(Enquiry enquiry) throws Exception;

	int getPendingEnquiryNumEntries(int userId, String status) throws Exception;

}
