package travel.com.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import travel.com.dao.EnquiryDAO;
import travel.com.model.Enquiry;
import travel.com.service.EnquiryService;

public class EnquiryServiceBO implements EnquiryService
{

	@Autowired
	@Qualifier("enquiryDAO")
	EnquiryDAO enquiryDAO;

	public void addEnquiry(Enquiry enquiry) throws Exception
	{
		enquiryDAO.addEnquiry(enquiry);
	}

	public List<Enquiry> getPendingEnquiry(int userId, String status,
			int startIndx, int endIndx) throws Exception
	{
		return enquiryDAO.getPendingEnquiry(userId, status, startIndx, endIndx);
	}

	public void updateEnquiryStatus(Enquiry enquiry) throws Exception
	{
		enquiryDAO.updateEnquiryStatus(enquiry);
	}

	public int getPendingEnquiryNumEntries(int userId, String status)
			throws Exception
	{
		return enquiryDAO.getPendingEnquiryNumEntries(userId, status);
	}

	@Override
	public List<Enquiry> getProcessingEnquiry(int userId, String statusSent,
			int startIndx, int defaultMaxIndx) throws Exception
	{
		return enquiryDAO.getProcessingEnquiry(userId, statusSent, startIndx,
				defaultMaxIndx);
	}

	@Override
	public int getProcessingEnquiryNumEntries(int userId, String statusSent)
			throws Exception
	{
		return enquiryDAO.getProcessingEnquiryNumEntries(userId, statusSent);
	}

	@Override
	public List<Enquiry> getexpiredEnquiry(int userId, String statusSent,
			int startIndx, int defaultMaxIndx) throws Exception
	{
		return enquiryDAO.getexpiredEnquiry(userId, statusSent, startIndx,
				defaultMaxIndx);
	}

	@Override
	public int getexpiredEnquiryNumEntries(int userId, String statusSent)
			throws Exception
	{
		return enquiryDAO.getexpiredEnquiryNumEntries(userId, statusSent);
	}
}
