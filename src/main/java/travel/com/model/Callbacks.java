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

public class Callbacks
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
	private int tripid;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private String createdat;

	@Getter
	@Setter
	private String email;

	public Callbacks()
	{

	}

	public Callbacks(int consumerid)
	{
		this.consumerid = consumerid;
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

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}
