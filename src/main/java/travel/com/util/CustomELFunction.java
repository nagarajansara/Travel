package travel.com.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

public class CustomELFunction
{
	public static String getBase64Encode(String paramName)
	{
		byte[] bytesEncoded = Base64.encodeBase64(paramName.getBytes());
		return new String(bytesEncoded);
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
