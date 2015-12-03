package travel.com.util;

public class AppProp
{

	private String adminMailId;
	private String mailSubject;
	private String uploadedCsvPath;
	private String FbAppId;
	private String FbAppSecret;
	private String FbAppURI;
	private String uploadImagePath;
	private String uploadImageCompressPath;
	private String serverDomain;
	private String enquriryContent;
	private String enqurirySubject;

	public String getEnquriryContent()
	{
		return enquriryContent;
	}

	public void setEnquriryContent(String enquriryContent)
	{
		this.enquriryContent = enquriryContent;
	}

	public String getEnqurirySubject()
	{
		return enqurirySubject;
	}

	public void setEnqurirySubject(String enqurirySubject)
	{
		this.enqurirySubject = enqurirySubject;
	}

	public String getServerDomain()
	{
		return serverDomain;
	}

	public void setServerDomain(String serverDomain)
	{
		this.serverDomain = serverDomain;
	}

	public void setAdminMailId(String adminMailId)
	{
		this.adminMailId = adminMailId;
	}

	public String getAdminMailId()
	{
		return adminMailId;
	}

	public void setMailSubject(String mailSubject)
	{
		this.mailSubject = mailSubject;
	}

	public String getMailSubject()
	{
		return mailSubject;
	}

	public void setUploadedCsvPath(String uploadedCsvPath)
	{
		this.uploadedCsvPath = uploadedCsvPath;
	}

	public String getUploadedCsvPath()
	{
		return uploadedCsvPath;
	}

	public void setFbAppId(String FbAppId)
	{
		this.FbAppId = FbAppId;
	}

	public void setFbAppSecret(String FbAppSecret)
	{
		this.FbAppSecret = FbAppSecret;
	}

	public void setFbAppURI(String FbAppURI)
	{
		this.FbAppURI = FbAppURI;
	}

	public void setUploadImagePath(String uploadImagePath)
	{
		this.uploadImagePath = uploadImagePath;
	}

	public String getFbAppId()
	{
		return FbAppId;
	}

	public String getFbAppSecret()
	{
		return FbAppSecret;
	}

	public String getFbAppURI()
	{
		return FbAppURI;
	}

	public String getUploadImagePath()
	{
		return uploadImagePath;
	}

	public String getUploadImageCompressPath()
	{
		return uploadImageCompressPath;
	}

	public void setUploadImageCompressPath(String uploadImageCompressPath)
	{
		this.uploadImageCompressPath = uploadImageCompressPath;
	}

}
