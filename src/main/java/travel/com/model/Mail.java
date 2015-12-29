package travel.com.model;

import lombok.Getter;
import lombok.Setter;

public class Mail
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String toemail;

	@Getter
	@Setter
	private String fromemail;

	@Getter
	@Setter
	private String content;

	@Getter
	@Setter
	private String subject;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private String email_type;

	@Getter
	@Setter
	private String createdat;
	
	final String EMAIL_TYPE_ENQUIRY = "enquiry";

	public Mail()
	{

	}

	public Mail(String toemail, String fromemail, String content,
			String subject, String status, String email_type)
	{
		this.toemail = toemail;
		this.fromemail = fromemail;
		this.content = content;
		this.subject = subject;
		this.status = status;
		this.email_type = email_type;

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getToemail()
	{
		return toemail;
	}

	public void setToemail(String toemail)
	{
		this.toemail = toemail;
	}

	public String getFromemail()
	{
		return fromemail;
	}

	public void setFromemail(String fromemail)
	{
		this.fromemail = fromemail;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getEmail_type()
	{
		return email_type;
	}

	public void setEmail_type(String email_type)
	{
		this.email_type = email_type;
	}

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

}
