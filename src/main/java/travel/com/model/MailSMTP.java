package travel.com.model;

import lombok.Getter;
import lombok.Setter;

public class MailSMTP
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String smtpusername;

	@Getter
	@Setter
	private String smtppassword;

	@Getter
	@Setter
	private String smtpport;

	@Getter
	@Setter
	private String status;

	public MailSMTP()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getSmtpusername()
	{
		return smtpusername;
	}

	public void setSmtpusername(String smtpusername)
	{
		this.smtpusername = smtpusername;
	}

	public String getSmtppassword()
	{
		return smtppassword;
	}

	public void setSmtppassword(String smtppassword)
	{
		this.smtppassword = smtppassword;
	}

	public String getSmtpport()
	{
		return smtpport;
	}

	public void setSmtpport(String smtpport)
	{
		this.smtpport = smtpport;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
