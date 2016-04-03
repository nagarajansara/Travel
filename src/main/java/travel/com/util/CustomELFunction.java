package travel.com.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import travel.com.dao.ActivityDAO;
import travel.com.dao.ActivityDAOImpl;
import travel.com.model.Activity;

public class CustomELFunction
{
	public static String getBase64Encode(String paramName)
	{
		byte[] bytesEncoded = Base64.encodeBase64(paramName.getBytes());
		return new String(bytesEncoded);
	}

	public static List<Activity> getActivitys()
	{
		List<Activity> list = new ArrayList<Activity>();
		try
		{
			ActivityDAO activityDAO = new ActivityDAOImpl();
			list = activityDAO.getActivitys(new Activity("active"));
		} catch (Exception ex)
		{
			
		}
		return list;
	}

	// SEO Purpose
	public static String getSlugString(String input)
	{
		final Pattern NONLATIN = Pattern.compile("[^\\w-]");
		final Pattern WHITESPACE = Pattern.compile("[\\s]");

		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}
}
