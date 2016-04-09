package travel.com.model;

import lombok.Getter;
import lombok.Setter;

public class Viewers
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int tripid;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private int credits;

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

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	public int getTotals()
	{
		return totals;
	}

	public void setTotals(int totals)
	{
		this.totals = totals;
	}

	@Getter
	@Setter
	private String createdat;

	@Getter
	@Setter
	private int totals;

	public final static String STATUS_VIEWED = "viewed";
	public final static String STATUS_LIKED = "liked";

	public Viewers()
	{

	}

	public Viewers(int tripId, int credits, String status)
	{
		this.tripid = tripId;
		this.credits = credits;
		this.status = status;
	}

}
