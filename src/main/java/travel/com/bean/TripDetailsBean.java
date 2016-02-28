package travel.com.bean;

import java.io.Serializable;

@SuppressWarnings(
{ "unused", "unchecked" })
public class TripDetailsBean implements Serializable
{
	private static final long serialVersionUID = -8243145429438016231L;

	private int userid;
	private String startrating;
	private String id;
	private String title;
	private int locationid;
	private int subactivityid;
	private int activityid;
	private int duration;
	private String fromdate;
	private String todate;
	private int startpoint;
	private String route;
	private String description;
	private double price;
	private String guidelines;
	private String status;
	private String createdat;
	private double offer_percentage;
	private String tocity;

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public String getStartrating()
	{
		return startrating;
	}

	public void setStartrating(String startrating)
	{
		this.startrating = startrating;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getLocationid()
	{
		return locationid;
	}

	public void setLocationid(int locationid)
	{
		this.locationid = locationid;
	}

	public int getSubactivityid()
	{
		return subactivityid;
	}

	public void setSubactivityid(int subactivityid)
	{
		this.subactivityid = subactivityid;
	}

	public int getActivityid()
	{
		return activityid;
	}

	public void setActivityid(int activityid)
	{
		this.activityid = activityid;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public String getFromdate()
	{
		return fromdate;
	}

	public void setFromdate(String fromdate)
	{
		this.fromdate = fromdate;
	}

	public String getTodate()
	{
		return todate;
	}

	public void setTodate(String todate)
	{
		this.todate = todate;
	}

	public int getStartpoint()
	{
		return startpoint;
	}

	public void setStartpoint(int startpoint)
	{
		this.startpoint = startpoint;
	}

	public String getRoute()
	{
		return route;
	}

	public void setRoute(String route)
	{
		this.route = route;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getGuidelines()
	{
		return guidelines;
	}

	public void setGuidelines(String guidelines)
	{
		this.guidelines = guidelines;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	public double getOffer_percentage()
	{
		return offer_percentage;
	}

	public void setOffer_percentage(double offer_percentage)
	{
		this.offer_percentage = offer_percentage;
	}

	public String getTocity()
	{
		return tocity;
	}

	public void setTocity(String tocity)
	{
		this.tocity = tocity;
	}

	public String getTripimagename()
	{
		return tripimagename;
	}

	public void setTripimagename(String tripimagename)
	{
		this.tripimagename = tripimagename;
	}

	public String getDaysdesc()
	{
		return daysdesc;
	}

	public void setDaysdesc(String daysdesc)
	{
		this.daysdesc = daysdesc;
	}

	public String getDateformat()
	{
		return dateformat;
	}

	public void setDateformat(String dateformat)
	{
		this.dateformat = dateformat;
	}

	public String getTodateformat()
	{
		return todateformat;
	}

	public void setTodateformat(String todateformat)
	{
		this.todateformat = todateformat;
	}

	public int getViews()
	{
		return views;
	}

	public void setViews(int views)
	{
		this.views = views;
	}

	public int getFavourites()
	{
		return favourites;
	}

	public void setFavourites(int favourites)
	{
		this.favourites = favourites;
	}

	public int getReviews()
	{
		return reviews;
	}

	public void setReviews(int reviews)
	{
		this.reviews = reviews;
	}

	private String tripimagename;
	private String daysdesc;
	private String dateformat;
	private String todateformat;
	private int views;
	private int favourites;
	private int reviews;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj == null)
		{
			return false;
		}

		if (getClass() != obj.getClass())
		{
			return false;
		}
		TripDetailsBean other = (TripDetailsBean) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}

		} else if (!id.equals(other.id))
		{
			return false;
		}

		return true;
	}

	public TripDetailsBean()
	{

	}

	public TripDetailsBean(int userid, String startrating, String id,
			String title, int locationid, int subactivityid, int activityid,
			int duration, String fromdate, String todate, int startpoint,
			String route, String description, double price, String guidelines,
			String status, String createdat, double offer_percentage,
			String tocity, String tripimagename, String daysdesc,
			String dateformat, String todateformat, int views, int favourites,
			int reviews)
	{
		super();
		this.userid = userid;
		this.startrating = startrating;
		this.id = id;
		this.title = title;
		this.locationid = locationid;
		this.subactivityid = subactivityid;
		this.activityid = activityid;
		this.duration = duration;
		this.fromdate = fromdate;
		this.todate = todate;
		this.startpoint = startpoint;
		this.route = route;
		this.description = description;
		this.price = price;
		this.guidelines = guidelines;
		this.status = status;
		this.createdat = createdat;
		this.offer_percentage = offer_percentage;
		this.tocity = tocity;
		this.tripimagename = tripimagename;
		this.daysdesc = daysdesc;
		this.dateformat = dateformat;
		this.todateformat = todateformat;
		this.views = views;
		this.favourites = favourites;
		this.reviews = reviews;
	}

}
