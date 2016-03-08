package travel.com.model;

import lombok.Getter;
import lombok.Setter;

public class SavedTrips
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int userid;

	@Getter
	@Setter
	private int tripid;

	@Getter
	@Setter
	private String title;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private int duration;

	@Getter
	@Setter
	private int reviewscount;

	public String getGuidelines()
	{
		return guidelines;
	}

	public void setGuidelines(String guidelines)
	{
		this.guidelines = guidelines;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	@Getter
	@Setter
	private int viewrscount;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private String guidelines;

	@Getter
	@Setter
	private double price;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public int getTripid()
	{
		return tripid;
	}

	public void setTripid(int tripid)
	{
		this.tripid = tripid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public int getReviewscount()
	{
		return reviewscount;
	}

	public void setReviewscount(int reviewscount)
	{
		this.reviewscount = reviewscount;
	}

	public int getViewrscount()
	{
		return viewrscount;
	}

	public void setViewrscount(int viewrscount)
	{
		this.viewrscount = viewrscount;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getTripimagename()
	{
		return tripimagename;
	}

	public void setTripimagename(String tripimagename)
	{
		this.tripimagename = tripimagename;
	}

	public String getDateformat()
	{
		return dateformat;
	}

	public void setDateformat(String dateformat)
	{
		this.dateformat = dateformat;
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
	private String tripimagename;

	@Getter
	@Setter
	private String dateformat;

	@Getter
	@Setter
	private String created_at;

	public SavedTrips()
	{

	}

}
