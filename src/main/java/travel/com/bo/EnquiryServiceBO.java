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

	public List<Enquiry> getPendingEnquiry(int userId, String status)
			throws Exception
	{
		return enquiryDAO.getPendingEnquiry(userId, status);
	}

	public void updateEnquiryStatus(Enquiry enquiry) throws Exception
	{
		enquiryDAO.updateEnquiryStatus(enquiry);
	}
}
