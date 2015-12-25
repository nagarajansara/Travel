package travel;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;

import com.mysql.jdbc.Field;

public class SampleThread // extends Exce
{

	public SampleThread()
	{

	}

	public static void main(String args[]) throws Throwable
	{
		String URL = "http://localhost/api/login/test.html";
		String LASTURL = URL.substring(URL.lastIndexOf("/") + 2, URL.length());
		System.out.println("LASTURL :" + LASTURL);
	}
}

class A
{
	public String name;
	public String city;
	public String state;

	public A(String name, String city, String state)
	{
		this.name = name;
		this.city = city;
		this.state = state;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

}
