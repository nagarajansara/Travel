package travel.com.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener
{

	public void sessionDestroyed(HttpSessionEvent event)
	{
		// Do here the job.
		//System.out.println("Seesion Destroy");
	}

	public void sessionCreated(HttpSessionEvent arg0)
	{
		// TODO Auto-generated method stub
		/* System.out.println("Seesion Created"); */
	}

}