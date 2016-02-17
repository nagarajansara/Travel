package travel.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Trip
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int userid;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int locationid;

	@Getter
	@Setter
	private int activityid;

	@Getter
	@Setter
	private int duration;

	@Getter
	@Setter
	private String fromdate;

	@Getter
	@Setter
	private String todate;

	@Getter
	@Setter
	private int startpoint;

	@Getter
	@Setter
	private String route;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private String guidelines;

	@Getter
	@Setter
	private String imageurls;

	@Getter
	@Setter
	private String createdat;

	@Getter
	@Setter
	private String activityname;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private int startIndx;

	@Getter
	@Setter
	private int endIndx;

	@Getter
	@Setter
	private String fromcity;

	@Getter
	@Setter
	private String tocity;

	@Getter
	@Setter
	private int tocityid;

	@Getter
	@Setter
	private int fromcityid;

	@Getter
	@Setter
	private int itenaryday;

	@Getter
	@Setter
	private String daysdesc;

	@Getter
	@Setter
	private String defaultImage;

	@Getter
	@Setter
	private int views;

	@Getter
	@Setter
	private int favourites;

	@Getter
	@Setter
	private int reviews;

	@Getter
	@Setter
	private int credits;

	@Getter
	@Setter
	private int reviewscount;

	@Getter
	@Setter
	private int viwerscount;

	@Getter
	@Setter
	private double offer_percentage;

	@Getter
	@Setter
	private int subactivityid;

	public String getSubactivityname()
	{
		return subactivityname;
	}

	public void setSubactivityname(String subactivityname)
	{
		this.subactivityname = subactivityname;
	}

	@Getter
	@Setter
	private String subactivityname;

	public int getSubactivityid()
	{
		return subactivityid;
	}

	public void setSubactivityid(int subactivityid)
	{
		this.subactivityid = subactivityid;
	}

	public double getOffer_percentage()
	{
		return offer_percentage;
	}

	public void setOffer_percentage(double offer_percentage)
	{
		this.offer_percentage = offer_percentage;
	}

	public int getViwerscount()
	{
		return viwerscount;
	}

	public void setViwerscount(int viwerscount)
	{
		this.viwerscount = viwerscount;
	}

	public int getReviewscount()
	{
		return reviewscount;
	}

	public void setReviewscount(int reviewscount)
	{
		this.reviewscount = reviewscount;
	}

	public String getStartrating()
	{
		return startrating;
	}

	public void setStartrating(String startrating)
	{
		this.startrating = startrating;
	}

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String startrating;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
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
	private int itenaryid;

	@Getter
	@Setter
	private String title;

	@Getter
	@Setter
	private String tripimage;

	@Getter
	@Setter
	private double price;

	@Getter
	@Setter
	private String tripimagename;

	@Getter
	@Setter
	private String dateformat;

	@Getter
	@Setter
	private String todateformat;

	public String getDefaultImage()
	{
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage)
	{
		this.defaultImage = defaultImage;
	}

	public String getTodateformat()
	{
		return todateformat;
	}

	public void setTodateformat(String todateformat)
	{
		this.todateformat = todateformat;
	}

	public Trip()
	{

	}

	public Trip(int userid, int activityid, int duration, int locationid,
			String fromdate, String todate, int startpoint, String route,
			String description, String guidelines, String title, double price)
	{
		this.userid = userid;
		this.activityid = activityid;
		this.duration = duration;
		this.locationid = locationid;
		this.fromdate = fromdate;
		this.todate = todate;
		this.startpoint = startpoint;
		this.route = route;
		this.description = description;
		this.guidelines = guidelines;
		this.title = title;
		this.price = price;
	}

	public Trip(int userid, int activityid, int duration, int locationid,
			String fromdate, String todate, int startpoint, String route,
			String description, String guidelines, String title, double price,
			int subactivityid)
	{
		this.userid = userid;
		this.activityid = activityid;
		this.duration = duration;
		this.locationid = locationid;
		this.fromdate = fromdate;
		this.todate = todate;
		this.startpoint = startpoint;
		this.route = route;
		this.description = description;
		this.guidelines = guidelines;
		this.title = title;
		this.price = price;
		this.subactivityid = subactivityid;
	}

	public Trip(int id, int userid, int activityid, int duration,
			int locationid, String fromdate, String todate, int startpoint,
			String route, String description, String guidelines, String title,
			double price)
	{
		this.userid = userid;
		this.activityid = activityid;
		this.duration = duration;
		this.locationid = locationid;
		this.fromdate = fromdate;
		this.todate = todate;
		this.startpoint = startpoint;
		this.route = route;
		this.description = description;
		this.guidelines = guidelines;
		this.title = title;
		this.id = id;
		this.price = price;
	}

	public Trip(int id, int userid, int activityid, int duration,
			int locationid, String fromdate, String todate, int startpoint,
			String route, String description, String guidelines, String title,
			double price, int subactivityid)
	{
		this.userid = userid;
		this.activityid = activityid;
		this.duration = duration;
		this.locationid = locationid;
		this.fromdate = fromdate;
		this.todate = todate;
		this.startpoint = startpoint;
		this.route = route;
		this.description = description;
		this.guidelines = guidelines;
		this.title = title;
		this.id = id;
		this.price = price;
		this.subactivityid = subactivityid;
	}

	public Trip(int userId, String status, int startIndx, int endIndx)
	{
		this.userid = userId;
		this.status = status;
		this.startIndx = startIndx;
		this.endIndx = endIndx;
	}

	public Trip(int tripId, int userId, String status)
	{
		this.userid = userId;
		this.status = status;
		this.id = tripId;
	}

	public Trip(int tripId, String status)
	{
		this.id = tripId;
		this.status = status;
	}

	public Trip(String status, int startIndx, int endIndx)
	{
		this.status = status;
		this.startIndx = startIndx;
		this.endIndx = endIndx;
	}

	public Trip(String status)
	{
		this.status = status;

	}

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

	public String getName()
	{
		return name;
	}

	public String getTripimage()
	{
		return tripimage;
	}

	public void setTripimage(String tripimage)
	{
		this.tripimage = tripimage;
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

	public void setName(String name)
	{
		this.name = name;
	}

	public int getLocationid()
	{
		return locationid;
	}

	public void setLocationid(int locationid)
	{
		this.locationid = locationid;
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

	public String getGuidelines()
	{
		return guidelines;
	}

	public void setGuidelines(String guidelines)
	{
		this.guidelines = guidelines;
	}

	public String getImageurls()
	{
		return imageurls;
	}

	public void setImageurls(String imageurls)
	{
		this.imageurls = imageurls;
	}

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	public String getActivityname()
	{
		return activityname;
	}

	public void setActivityname(String activityname)
	{
		this.activityname = activityname;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getStartIndx()
	{
		return startIndx;
	}

	public void setStartIndx(int startIndx)
	{
		this.startIndx = startIndx;
	}

	public int getEndIndx()
	{
		return endIndx;
	}

	public void setEndIndx(int endIndx)
	{
		this.endIndx = endIndx;
	}

	public String getFromcity()
	{
		return fromcity;
	}

	public void setFromcity(String fromcity)
	{
		this.fromcity = fromcity;
	}

	public String getTocity()
	{
		return tocity;
	}

	public void setTocity(String tocity)
	{
		this.tocity = tocity;
	}

	public int getTocityid()
	{
		return tocityid;
	}

	public void setTocityid(int tocityid)
	{
		this.tocityid = tocityid;
	}

	public int getFromcityid()
	{
		return fromcityid;
	}

	public void setFromcityid(int fromcityid)
	{
		this.fromcityid = fromcityid;
	}

	public int getItenaryday()
	{
		return itenaryday;
	}

	public void setItenaryday(int itenaryday)
	{
		this.itenaryday = itenaryday;
	}

	public String getDaysdesc()
	{
		return daysdesc;
	}

	public void setDaysdesc(String daysdesc)
	{
		this.daysdesc = daysdesc;
	}

	public int getItenaryid()
	{
		return itenaryid;
	}

	public void setItenaryid(int itenaryid)
	{
		this.itenaryid = itenaryid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}
