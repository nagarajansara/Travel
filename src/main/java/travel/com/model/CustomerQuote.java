package travel.com.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings(
{ "unused", "unchecked" })
public class CustomerQuote implements Serializable
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String phoneno;

	@Getter
	@Setter
	private String created_at;

	public String getRequirement()
	{
		return requirement;
	}

	public void setRequirement(String requirement)
	{
		this.requirement = requirement;
	}

	@Getter
	@Setter
	private String requirement;

	public CustomerQuote()
	{

	}

	public CustomerQuote(String email, String name, String phoneno,
			String requirement)
	{
		this.name = name;
		this.email = email;
		this.phoneno = phoneno;
		this.requirement = requirement;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneno()
	{
		return phoneno;
	}

	public void setPhoneno(String phoneno)
	{
		this.phoneno = phoneno;
	}

	public String getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}

}
