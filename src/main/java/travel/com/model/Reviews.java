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
public class Reviews
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int tripid;

	@Getter
	@Setter
	private String username;

	public int getVendorId()
	{
		return vendorId;
	}

	public void setVendorId(int vendorId)
	{
		this.vendorId = vendorId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getTripid()
	{
		return tripid;
	}

	public void setTripid(int tripid)
	{
		this.tripid = tripid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	@Getter
	@Setter
	private String comment;

	@Getter
	@Setter
	private String category;

	@Getter
	@Setter
	private String createdat;

	@Getter
	@Setter
	private String createdate;

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
	private String createdateformat;

	@Getter
	@Setter
	private String startrating;

	public String getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(String createdate)
	{
		this.createdate = createdate;
	}

	public String getCreatedateformat()
	{
		return createdateformat;
	}

	public void setCreatedateformat(String createdateformat)
	{
		this.createdateformat = createdateformat;
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

	@Getter
	@Setter
	private int startIndx;

	@Getter
	@Setter
	private int endIndx;

	@Getter
	@Setter
	private int vendorId;
	
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
	private String title;

	public Reviews()
	{

	}

	public Reviews(int tripId, int startIndx, int endindx, int vendorId)
	{
		this.tripid = tripId;
		this.startIndx = startIndx;
		this.endIndx = endindx;
		this.vendorId = vendorId;
	}

	public Reviews(int tripId, int startIndx, int endindx)
	{
		this.tripid = tripId;
		this.startIndx = startIndx;
		this.endIndx = endindx;
	}

	public Reviews(int tripid, String username, String comment,
			String startrating)
	{
		this.tripid = tripid;
		this.username = username;
		this.comment = comment;
		this.startrating = startrating;
	}
}
