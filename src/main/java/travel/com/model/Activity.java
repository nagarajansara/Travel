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

public class Activity
{
	@Getter @Setter
    private int id;

	@Getter @Setter
    private String name;

	@Getter @Setter
    private String status;

	@Getter @Setter
    private String createdat;

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
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
	public String getCreatedat()
	{
		return createdat;
	}
	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}
	public Activity()
	{

	}
	public Activity(String name, String status)
	{
		this.name = name;
		this.status = status;
	}
	public Activity(String status)
	{
		this.status = status;
	}
	
	
}
