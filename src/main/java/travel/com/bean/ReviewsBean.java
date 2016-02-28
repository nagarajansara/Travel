package travel.com.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ReviewsBean implements Serializable
{
	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private int tripid;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private int numEntries;

	public int getNumEntries()
	{
		return numEntries;
	}

	public void setNumEntries(int numEntries)
	{
		this.numEntries = numEntries;
	}

	public int getVendorId()
	{
		return vendorId;
	}

	public void setVendorId(int vendorId)
	{
		this.vendorId = vendorId;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
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

	public ReviewsBean()
	{

	}

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
		ReviewsBean other = (ReviewsBean) obj;
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

	public ReviewsBean(String username, String comment,
			String createdateformat, String id)
	{
		this.username = username;
		this.comment = comment;
		this.createdateformat = createdateformat;
		this.id = id;

	}
}
