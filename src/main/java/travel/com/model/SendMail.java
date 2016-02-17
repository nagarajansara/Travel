package travel.com.model;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import travel.com.util.AppProp;
import travel.com.util.Utilities;

@SuppressWarnings(
{ "unchecked", "unused" })
public class SendMail
{

	private static final Logger logger = Logger.getLogger(SendMail.class
			.getName());

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	public String sendMail(String[] toAddr, String subject, String content,
			String fromEmail, String fromName) throws Exception
	{
		String smtpHost = appProp.getSmtpHost();
		String smtpPort = appProp.getSmtpPort();
		final String username = appProp.getAuthEmail();
		final String password = appProp.getAuthPass();

		String messageID = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);

		String dsn = "SUCCESS,FAILURE,DELAY ORCPT=rfc822;" + username;
		props.put("mail.smtp.dsn.notify", dsn);

		Session session =
				Session.getInstance(props, new javax.mail.Authenticator()
				{
					protected
							PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username, password);
					}
				});
		try
		{
			messageID = getUUID();
			CustomMimeMessage message =
					new CustomMimeMessage(session, messageID);
			message.setReplyTo(new javax.mail.Address[]
			{ new javax.mail.internet.InternetAddress(fromEmail) });
			message.setFrom(new InternetAddress(fromEmail, fromName));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toAddr[0]));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);

			messageID = message.getMessageID();
		} catch (Exception e)
		{
			logger.error("sendMail :" + e.getMessage());
			throw e;
		} finally
		{
			props.clear();
		}
		return messageID;
	}

	public String sendMail(String[] toAddr, String subject, String content)
			throws Exception
	{
		String smtpHost = appProp.getSmtpHost();
		String smtpPort = appProp.getSmtpPort();
		final String username = appProp.getAuthEmail();
		final String password = appProp.getAuthPass();

		String messageID = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);

		String fromEmail = appProp.getAdminMailId();
		String fromName = appProp.getAdminName();

		String dsn = "SUCCESS,FAILURE,DELAY ORCPT=rfc822;" + username;
		props.put("mail.smtp.dsn.notify", dsn);

		Session session =
				Session.getInstance(props, new javax.mail.Authenticator()
				{
					protected
							PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username, password);
					}
				});
		try
		{
			messageID = getUUID();
			CustomMimeMessage message =
					new CustomMimeMessage(session, messageID);
			message.setReplyTo(new javax.mail.Address[]
			{ new javax.mail.internet.InternetAddress(appProp.getAdminMailId()) });
			message.setFrom(new InternetAddress(fromEmail, fromName));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toAddr[0]));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);

			messageID = message.getMessageID();
		} catch (Exception e)
		{
			logger.error("sendMail :" + e.getMessage());
			throw e;
		} finally
		{
			props.clear();
		}
		return messageID;
	}

	public String getUUID() throws Exception
	{
		String uuid = null;
		try
		{
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
		} catch (Exception e)
		{
			logger.error("getUUID :" + e.getMessage());
		}
		return uuid;
	}

	class CustomMimeMessage extends MimeMessage
	{
		private String messageID;

		public CustomMimeMessage(Session session, String messageID)
		{
			super(session);
			this.messageID = messageID;
		}

		@Override
		protected void updateMessageID() throws MessagingException
		{
			setHeader("Message-ID", messageID);
		}
	}

}
