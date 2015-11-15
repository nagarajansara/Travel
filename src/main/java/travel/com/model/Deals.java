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

public class Deals
{

	@Getter @Setter
    private int id;

	@Getter @Setter
    private int vendorid;

	@Getter @Setter
    private String offer;

	@Getter @Setter
    private String offerdesc;

	@Getter @Setter
    private int tripid;

	@Getter @Setter
    private String createdat;

	public Deals()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getVendorid()
	{
		return vendorid;
	}

	public void setVendorid(int vendorid)
	{
		this.vendorid = vendorid;
	}

	public String getOffer()
	{
		return offer;
	}

	public void setOffer(String offer)
	{
		this.offer = offer;
	}

	public String getOfferdesc()
	{
		return offerdesc;
	}

	public void setOfferdesc(String offerdesc)
	{
		this.offerdesc = offerdesc;
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
	
}

