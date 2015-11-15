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

public class TripImage
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int tripid;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private String imagetype;

	@Getter
	@Setter
	private String created_at;

	public TripImage()
	{

	}

	public TripImage(int tripId, String imageName)
	{
		this.tripid = tripId;
		this.name = imageName;
	}

	public TripImage(int tripId, String imageName, String status)
	{
		this.tripid = tripId;
		this.status = status;
		this.name = imageName;
	}

	public TripImage(int tripId, int id, String status)
	{
		this.tripid = tripId;
		this.id = id;
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

	public int getTripid()
	{
		return tripid;
	}

	public void setTripid(int tripid)
	{
		this.tripid = tripid;
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

	public String getImagetype()
	{
		return imagetype;
	}

	public void setImagetype(String imagetype)
	{
		this.imagetype = imagetype;
	}

	public String getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}
}
