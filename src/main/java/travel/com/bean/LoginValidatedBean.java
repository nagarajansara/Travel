package travel.com.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class LoginValidatedBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String email;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	private String password;

	public LoginValidatedBean(String email, String password)
	{
		this.email = email;
		this.password = password;
	}

}
