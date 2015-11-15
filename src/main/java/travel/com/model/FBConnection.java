package travel.com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import travel.com.controller.TripController;
import travel.com.service.CityService;
import travel.com.util.*;

@SuppressWarnings("unused")
public class FBConnection
{

	static String accessToken = "";

	public static String FB_APP_ID = "";
	public static String FB_APP_SECRET = "";
	public static String REDIRECT_URI = "";

	private static final Logger logger = Logger.getLogger(FBConnection.class
			.getName());

	/*
	 * static {
	 * 
	 * try { if (Utilities.isServer()) { FB_APP_ID = "379863222204467";
	 * FB_APP_SECRET = "b19fdc076a88ff75ced567f27d34b4a9"; REDIRECT_URI =
	 * "http:///travel/travelapi/login/fbsignupcbk";
	 * 
	 * logger.info("FBConnection : SERVER FB");
	 * 
	 * } else { FB_APP_ID = "379863222204467"; FB_APP_SECRET =
	 * "b19fdc076a88ff75ced567f27d34b4a9"; REDIRECT_URI =
	 * "http://localhost/travel/travelapi/login/fbsignupcbk";
	 * logger.info("FBConnection : Local FB"); } } catch (Exception ex) {
	 * ex.printStackTrace(); }
	 * 
	 * }
	 */

	public String getFBAuthUrl(String FB_APP_ID, String FB_APP_SECRET,
			String REDIRECT_URI)
	{

		this.FB_APP_ID = FB_APP_ID;
		this.FB_APP_SECRET = FB_APP_SECRET;
		this.REDIRECT_URI = REDIRECT_URI;

		setInitializeValues();

		String fbLoginUrl = "";
		try
		{
			fbLoginUrl =
					"http://www.facebook.com/dialog/oauth?" + "client_id="
							+ FB_APP_ID + "&redirect_uri="
							+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
							+ "&scope=email";
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBAuthUrl()
	{

		setInitializeValues();

		String fbLoginUrl = "";
		try
		{

			fbLoginUrl =
					"http://www.facebook.com/dialog/oauth?" + "client_id="
							+ FB_APP_ID + "&redirect_uri="
							+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
							+ "&scope=email";
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code)
	{
		String fbGraphUrl = "";
		try
		{
			fbGraphUrl =
					"https://graph.facebook.com/oauth/access_token?"
							+ "client_id=" + FB_APP_ID + "&redirect_uri="
							+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
							+ "&client_secret=" + FB_APP_SECRET + "&code="
							+ code;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code)
	{
		if ("".equals(accessToken))
		{
			URL fbGraphURL;
			try
			{
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try
			{
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in =
						new BufferedReader(new InputStreamReader(
								fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{"))
			{
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}

	public void setInitializeValues()
	{
		try
		{
			if (Utilities.isServer())
			{

				FB_APP_ID = "379863222204467";
				FB_APP_SECRET = "b19fdc076a88ff75ced567f27d34b4a9";
				REDIRECT_URI =
						"http://saracourierservice.tk"
								+ "/travel/travelapi/login/fbsignupcbk";
			} else
			{
				FB_APP_ID = "379863222204467";
				FB_APP_SECRET = "b19fdc076a88ff75ced567f27d34b4a9";
				REDIRECT_URI =
						"http://localhost/travel/travelapi/login/fbsignupcbk";
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
