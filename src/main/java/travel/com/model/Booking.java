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

	@Getter
	@Setter
	private String createdat;

	public Booking()
	{

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
