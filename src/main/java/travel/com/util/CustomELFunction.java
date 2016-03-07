package travel.com.util;

import org.apache.commons.codec.binary.Base64;

public class CustomELFunction
{
	public static String getBase64Encode(String paramName)
	{
		byte[] bytesEncoded = Base64.encodeBase64(paramName.getBytes());
		return new String(bytesEncoded);
	}
}
