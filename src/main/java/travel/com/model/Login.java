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

public class Login
{

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String firstname;

	@Getter
	@Setter
	private String lastname;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String phoneno;

	@Getter
	@Setter
	private String address;

	@Getter
	@Setter
	private String city;

	@Getter
	@Setter
	private String isapproved;

	@Getter
	@Setter
	private String role;

	@Getter
	@Setter
	private String openid;

	@Getter
	@Setter
	private String openiddata;

	@Getter
	@Setter
	private String signuptype;

	@Getter
	@Setter
	private String ip;

	@Getter
	@Setter
	private String state;

	@Getter
	@Setter
	private String organizationname;

	@Getter
	@Setter
	private String pancardno;

	public String getPancardno()
	{
		return pancardno;
	}

	public void setPancardno(String pancardno)
	{
		this.pancardno = pancardno;
	}

	public String getPincode()
	{
		return pincode;
	}

	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	@Getter
	@Setter
	private String mobile;

	@Getter
	@Setter
	private String pincode;

	public Login()
	{

	}

	public Login(String email, String firstName, String lastName,
		String password, String city, String role, String ip)
	{
		this.email = email;
		this.firstname = firstName;
		this.lastname = lastName;
		this.password = password;
		this.city = city;
		this.role = role;
		this.ip = ip;
	}

	public Login(String email, String firstName, String lastName, String city,
		String role, String ip, String openId, String signupType,
		String openIdData)
	{
		this.email = email;
		this.firstname = firstName;
		this.lastname = lastName;
		this.city = city;
		this.role = role;
		this.ip = ip;
		this.openid = openId;
		this.signuptype = signupType;
		this.openiddata = openIdData;
	}

	public Login(String nameOrganization, String email, String firstName,
		String lastName, String password, String stateName, String address,
		String mobile, String phoneno, String role, String ip)
	{
		this.organizationname = organizationname;
		this.email = email;
		this.password = password;
		this.firstname = firstName;
		this.lastname = lastName;
		this.state = stateName;
		this.role = role;
		this.ip = ip;
		this.address = address;
		this.mobile = mobile;
		this.phoneno = phoneno;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPhoneno()
	{
		return phoneno;
	}

	public void setPhoneno(String phoneno)
	{
		this.phoneno = phoneno;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getIsapproved()
	{
		return isapproved;
	}

	public void setIsapproved(String isapproved)
	{
		this.isapproved = isapproved;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}

	public String getOpeniddata()
	{
		return openiddata;
	}

	public void setOpeniddata(String openiddata)
	{
		this.openiddata = openiddata;
	}

	public String getSignuptype()
	{
		return signuptype;
	}

	public void setSignuptype(String signuptype)
	{
		this.signuptype = signuptype;
	}

	public Login(String firstname, String lastname, String address,
		String mobile, String pincode, String state, String organizationname,
		String city, String pancardno, int id)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.mobile = mobile;
		this.pincode = pincode;
		this.state = state;
		this.organizationname = organizationname;
		this.city = city;
		this.pancardno = pancardno;
		this.id = id;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getOrganizationname()
	{
		return organizationname;
	}

	public void setOrganizationname(String organizationname)
	{
		this.organizationname = organizationname;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Login(int id, String role)
	{
		this.id = id;
		this.role = role;
	}
}
