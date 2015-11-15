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

public class City implements Serializable
{
	@Getter @Setter
    private int id;

	@Getter @Setter
	private String city;

	@Getter @Setter
	private String state;

	@Getter @Setter
	private String createdat;

	public City()
	{

	}
	public City(String city, String state)
	{
		this.city = city;
		this.state = state;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
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

