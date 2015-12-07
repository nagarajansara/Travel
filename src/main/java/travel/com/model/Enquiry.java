package travel.com.model;

import lombok.Getter;
import lombok.Setter;

public class Enquiry
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
	private String status;

	@Getter
	@Setter
	private int credits;

	@Getter
	@Setter
	private int tripid;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Getter
	@Setter
	private String createdat;

	@Getter
	@Setter
	private String title;

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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public int getTripid()
	{
		return tripid;
	}

	public void setTripid(int tripid)
	{
		this.tripid = tripid;
	}

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	public static final String STATUS_ENQUIRY = "enquiry";
	public static final String STATUS_PENDING = "pending";
	public static final String STATUS_SENT = "sent";
	public static final String STATUS_REJECTED = "rejected";
	public static final int DEFAULT_ENQUIRY_DEDUCTION = 10; // 10 credits
	public static final int DEFAULT_ENQUIRY_NON_DEDUCTION = 0; // 10 credits

	public Enquiry()
	{

	}

	public Enquiry(int tripId, String name, String status, String phoneno,
			int credits, String email)
	{
		this.tripid = tripId;
		this.name = name;
		this.status = status;
		this.phoneno = phoneno;
		this.credits = credits;
		this.email = email;
	}

	public Enquiry(int credits, int id, String status)
	{
		this.id = id;
		this.status = status;
		this.credits = credits;
	}

}
