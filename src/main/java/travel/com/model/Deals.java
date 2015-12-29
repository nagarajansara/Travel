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

@SuppressWarnings(
{ "unchecked", "unused" })
public class Deals
{

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int price;

	@Getter
	@Setter
	private int vendorid;

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	@Getter
	@Setter
	private String title;

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
	private int offer_percentage;

	public void setOffer(int offer_percentage)
	{
		this.offer_percentage = offer_percentage;
	}

	@Getter
	@Setter
	private String offerdesc;

	@Getter
	@Setter
	private int tripid;

	@Getter
	@Setter
	private int totals;

	public int getTotals()
	{
		return totals;
	}

	public void setTotals(int totals)
	{
		this.totals = totals;
	}

	public int getOffer_percentage()
	{
		return offer_percentage;
	}

	public void setOffer_percentage(int offer_percentage)
	{
		this.offer_percentage = offer_percentage;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Getter
	@Setter
	private String createdat;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private int startIndx;

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
	private int endIndx;

	public Deals()
	{

	}

	public Deals(int vendorId, int offerPercentage, String offerDesc, int tripId)
	{
		this.vendorid = vendorId;
		this.offer_percentage = offerPercentage;
		this.offerdesc = offerDesc;
		this.tripid = tripId;
	}

	public Deals(int vendorId, String status, int startIndx, int endIndx)
	{
		this.vendorid = vendorId;
		this.status = status;
		this.startIndx = startIndx;
		this.endIndx = endIndx;
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
