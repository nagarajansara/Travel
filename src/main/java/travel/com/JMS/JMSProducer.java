package travel.com.JMS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;

import travel.com.controller.TripController;
import travel.com.dao.BaseDAO;
import travel.com.model.Enquiry;
import travel.com.model.Login;
import travel.com.model.SendMail;
import travel.com.model.Trip;
import travel.com.service.EnquiryService;
import travel.com.service.LoginService;
import travel.com.service.TripService;
import travel.com.util.AppProp;
import travel.com.util.Utilities;

@SuppressWarnings("unused")
public class JMSProducer
{

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("enquiryService")
	EnquiryService enquiryService;

	@Autowired
	@Qualifier("tripService")
	TripService tripService;

	@Autowired
	@Qualifier("loginService")
	LoginService loginService;

	@Autowired
	@Qualifier("sendMail")
	SendMail sendMail;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	public final static String EMAIL_QUEUE = "emailQueue";

	private static final Logger logger = Logger.getLogger(JMSProducer.class
			.getName());

	public
			void
			SendJMS_Message(final String jsonObject, final String queueName)
					throws Exception
	{
		try
		{
			if (jmsTemplate != null)
			{

				jmsTemplate.send(queueName, new MessageCreator()
				{

					@Override
					public Message createMessage(Session session)
							throws JMSException
					{
						TextMessage textMessage = session.createTextMessage();
						textMessage.setText(jsonObject);
						return textMessage;
					}
				});

			} else
			{
				throw new Exception();
			}
		} catch (Exception e)
		{
			throw e;
		}
	}

	public void receiveJMS_Message() throws Exception
	{
		Message receivedMessage = jmsTemplate.receive(EMAIL_QUEUE);
		String text = "";
		try
		{
			if (receivedMessage instanceof TextMessage)
			{
				TextMessage textMessage = (TextMessage) receivedMessage;
				text = textMessage.getText();
				JSONObject jsonObject = new JSONObject(text);
				String content = jsonObject.getString("content");
				String subject = jsonObject.getString("subject");
				String toEmail = jsonObject.getString("toEmail");
				String name = jsonObject.getString("name");
				String[] toAddr =
				{ toEmail };
				sendMail.sendMail(toAddr, subject, content);
			}
		} catch (Exception ex)
		{
			
			SendJMS_Message(text, EMAIL_QUEUE); // Resend email
			logger.error("receiveJMS_Message :" + ex.getMessage());
		}
	}

	public void startJMSThread()
	{
		Start_JMS_THREAD start_JMS_THREAD = new Start_JMS_THREAD("MAIL_THREAD");
		start_JMS_THREAD.start();
	}

	class Start_JMS_THREAD extends Thread
	{
		public final int STATUS_START = 0;
		public final int STATUS_STOP = 1;
		public final int TIME_INTERVAL = 1000; // 1 mins

		private int status = STATUS_START;

		public Start_JMS_THREAD(String str)
		{
			super(str);
		}

		public void run()
		{
			while (status == STATUS_START)
			{
				try
				{
					receiveJMS_Message();
					TimeUnit.MINUTES.sleep(1);
				} catch (Exception e)
				{
					logger.error("Start_JMS_THREAD :" + e.getMessage());
				}
			}
		}

		public void startChecking()
		{
			status = STATUS_START;
			this.start();
		}

		public void stopChecking()
		{
			status = STATUS_STOP;
			this.stop();
		}

	}

}
