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

public class Itenary
{

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private Long tripid;

	@Getter
	@Setter
	private int day;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Long getTripid()
	{
		return tripid;
	}

	public void setTripid(Long tripid)
	{
		this.tripid = tripid;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public String getDaywisedescription()
	{
		return daywisedescription;
	}

	public void setDaywisedescription(String daywisedescription)
	{
		this.daywisedescription = daywisedescription;
	}

	@Getter
	@Setter
	private String daywisedescription;

	public Itenary()
	{

	}

	public Itenary(Long tripid, int day, String daywisedescription)
	{
		this.tripid = tripid;
		this.day = day;
		this.daywisedescription = daywisedescription;
	}

}
