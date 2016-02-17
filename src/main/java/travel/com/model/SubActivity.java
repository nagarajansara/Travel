package travel.com.model;

import lombok.Getter;
import lombok.Setter;

public class SubActivity
{
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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private String created_at;

	public SubActivity()
	{

	}

	public SubActivity(String name, String status)
	{
		this.name = name;
		this.status = status;
	}

}
