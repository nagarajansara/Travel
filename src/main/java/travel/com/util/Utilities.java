package travel.com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.validator.routines.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.commons.lang3.RandomStringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import travel.com.JMS.JMSProducer;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Collections;
import java.net.*;

@SuppressWarnings(
{ "unchecked", "unused" })
public class Utilities
{

	private static final Logger logger = Logger.getLogger(Utilities.class
			.getName());

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@Autowired
	@Qualifier("jMSProducer")
	JMSProducer jMSProducer;

	public String getIpAddress(HttpServletRequest request) throws Exception
	{
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null)
		{
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}

	public void setSuccessResponse(Response response)
	{
		setSuccessResponse(response, null);
	}

	public void setSuccessResponse(Response response, Object data)
	{
		response.setResponseStatus(ConstException.ERR_CODE_SUCCESS);
		response.setResponseMsg(ConstException.ERR_MSG_SUCCESS);
		response.setResponseData(data);
	}

	public void setErrResponse(Exception ex, Response response)
	{
		if (ex instanceof java.sql.SQLException
				|| ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException
				|| ex instanceof org.springframework.jdbc.BadSqlGrammarException
				|| ex instanceof org.springframework.dao.InvalidDataAccessApiUsageException
				|| ex instanceof org.springframework.dao.DataAccessException
				|| ex instanceof org.springframework.web.util.NestedServletException
				|| ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
		{
			response.setResponseMsg(ex.getMessage());
			response.setResponseStatus(ConstException.ERR_CODE_DB_ERROR);
		} else if (ex instanceof java.lang.NullPointerException)
		{
			response.setResponseMsg(ex.getMessage());
			response.setResponseStatus(ConstException.ERR_CODE_UNKNOWN);
		} else if (ex instanceof ConstException)
		{
			ConstException constException = (ConstException) ex;
			response.setResponseMsg(constException.getMessage());
			response.setResponseStatus(constException.getCode());
		} else if (ex instanceof java.lang.IllegalThreadStateException)
		{
			response.setResponseMsg(ex.getMessage());
			response.setResponseStatus(ConstException.ERR_CODE_INVALID_THREAD_STOP);
		} else
		{
			setErrResponse(ex, response, ConstException.ERR_CODE_UNKNOWN);
		}

	}

	public void setErrResponse(Exception ex, Response response, int errCode)
	{
		ConstException constException = (ConstException) ex;
		response.setResponseMsg(constException.getMessage());
		response.setResponseStatus(constException.getCode());
	}

	public void setResponseModel(ModelMap model, Response response)
	{
		model.addAttribute("model", response);
	}

	public String getSimpleDateTime() throws Exception
	{
		String simpleDateTime = "";
		SimpleDateFormat dateFormat =
				new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		simpleDateTime = dateFormat.format(date);
		return simpleDateTime;
	}

	public static boolean isServer() throws Exception
	{
		boolean isServer = false;
		InetAddress IP = InetAddress.getLocalHost();
		try
		{
			if (IP.getHostAddress().toString().equals("54.186.249.201")
					|| IP.getHostAddress().toString().equals("172.31.40.246")
					|| IP.getHostAddress().toString().equals("128.199.237.142")
					|| IP.getHostAddress().toString()
							.equals("saracourierservice.tk")
					|| IP.getHostAddress().toString()
							.equals("ecosenseindia.com")
					|| IP.getHostAddress().toString()
							.equals("http://www.ecosenseindia.com"))
			{
				isServer = true;
			}
		} catch (Exception ex)
		{
			throw ex;
		}
		return isServer;

	}

	public boolean isChkRequsetParamsNull(HttpServletRequest req,
			String[] params)
	{
		boolean isNull = false;
		if (params != null)
		{
			for (int i = 0; i < params.length; i++)
			{
				if (req.getParameter(params[i]) == null
						|| req.getParameter(params[i]).equals(""))
				{
					isNull = true;
				}
			}
		}
		return isNull;
	}

	public String getRandomNo()
	{
		Random r = new Random(System.currentTimeMillis());
		int tempRandomVal = 10000000 + r.nextInt(20000000);
		return String.valueOf(tempRandomVal);
	}

	public String convertToCommaDelimited(String[] list)
	{
		StringBuffer ret = new StringBuffer("");
		for (int i = 0; list != null && i < list.length; i++)
		{
			ret.append(list[i]);
			if (i < list.length - 1)
			{
				ret.append(',');
			}
		}
		return ret.toString();
	}

	public String getDefaultWord()
	{
		return "EMPTY";
	}

	public int getDefaultMinIndx(int minIndx)
	{
		return minIndx;
	}

	public int getDefaultMaxIndx(int maxIndx)
	{
		return maxIndx;
	}

	public int getDefaultMinIndx()
	{
		int MIN_INDX = 0;
		return MIN_INDX;
	}

	public int getDefaultMaxIndx()
	{
		int MAX_INDX = 5;
		return MAX_INDX;
	}

	public String getServerName()
	{
		return appProp.getServerDomain();
	}

	public String getFileContent(String filePath) throws Exception
	{
		return getFileContent(new File(filePath));
	}

	public String getFileContent(File file) throws Exception
	{
		BufferedReader br = null;
		String content = "";
		try
		{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null)
			{
				content += sCurrentLine;
			}
		} catch (Exception e)
		{
			throw new Exception(e);
		} finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
			} catch (Exception ex)
			{
			}
		}

		return content;
	}

	public String replaceMacro(String content, HashMap<String, String> map)
			throws Exception
	{
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue() != null ? entry.getValue() : "";
			content = content.replaceAll(key, value);
		}
		return content;
	}

	public String getDateTime()
	{

		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String DateToStr = format.format(curDate);
		return DateToStr;

	}

	public void setAccessCrossDomainResponse(HttpServletResponse res)
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "1728000");
		res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	}

	public String UUID()
	{
		UUID id = UUID.randomUUID();
		return id.toString();
	}

	public void setJMS_Enqueued(String toEmail, String content, String name,
			String subject, String JMSQueue_Type) throws Exception
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("toEmail", toEmail);
		jsonObject.put("content", content);
		jsonObject.put("name", name);
		jsonObject.put("subject", subject);
		jsonObject.put("type", JMSQueue_Type);
		jsonObject.put("msgId", UUID());

		jMSProducer.SendJMS_Message(jsonObject.toString(),
				jMSProducer.EMAIL_QUEUE);
	}

	public String getDecodedString(String paramValue, int deCodedType)
	{
		// 1. base64 decode
		String deCodedValue = "";
		switch (deCodedType)
		{
		case 1:
			byte[] valueDecoded = Base64.decodeBase64(paramValue.getBytes());
			deCodedValue = new String(valueDecoded);
			break;

		default:
			break;
		}
		return deCodedValue;

	}

	public String getEncodedString(String paramValue, int deCodedType)
	{
		// 1. base64 decode
		String enCodedValue = "";
		switch (deCodedType)
		{
		case 1:
			byte[] bytesEncoded = Base64.encodeBase64(paramValue.getBytes());
			enCodedValue = new String(bytesEncoded);
			break;

		default:
			break;
		}
		return enCodedValue;

	}
}
