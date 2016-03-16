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

	List<Enquiry> getProcessingEnquiry(int userId, String statusSent,
			int startIndx, int defaultMaxIndx) throws Exception;

	int getProcessingEnquiryNumEntries(int userId, String statusSent)
			throws Exception;

	List<Enquiry> getexpiredEnquiry(int userId, String statusSent,
			int startIndx, int defaultMaxIndx) throws Exception;

	int getexpiredEnquiryNumEntries(int userId, String statusSent)
			throws Exception;

}
