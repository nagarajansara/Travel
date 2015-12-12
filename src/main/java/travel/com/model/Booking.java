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
public class Booking
{
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private int vendorid;

	@Getter
	@Setter
	private int consumerid;

	@Getter
	@Setter
	private String status;

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
	private int startIndx;

	@Getter
	@Setter
	private int endIndx;

	@Getter
	@Setter
	private int totals;

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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Getter
	@Setter
	private String title;

	@Getter
	@Setter
	private String email;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public static String getBookingStatusBooked()
	{
		return BOOKING_STATUS_BOOKED;
	}

	@Getter
	@Setter
	private int credits;

	public final static String BOOKING_STATUS_BOOKED = "booked";

	public Booking()
	{

	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public Booking(int consumerid)
	{
		this.consumerid = consumerid;
	}

	public Booking(int vendorid, String status)
	{
		this.vendorid = vendorid;
		this.status = status;
	}

	public Booking(int vendorid, String status, int startIndx, int endIndx)
	{
		this.vendorid = vendorid;
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

	public int getConsumerid()
	{
		return consumerid;
	}

	public void setConsumerid(int consumerid)
	{
		this.consumerid = consumerid;
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
}
