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

	@Getter
	@Setter
	private String createdat;

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
