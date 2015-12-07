package travel.com.JMS;

import java.util.List;
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
import travel.com.model.Trip;
import travel.com.service.EnquiryService;
import travel.com.service.LoginService;
import travel.com.service.TripService;

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

	final String ENQUIRY_MESSAGE_QUEQUE = "enquiryMsg";
	public final String ACTIVATE_ENQUIRY_MESSAGE_QUEQUE = "activate_enquiryMsg";

	private static final Logger logger = Logger.getLogger(JMSProducer.class
			.getName());

	public void SendJMS_Message(final String jsonObject) throws Exception
	{
		try
		{
			if (jmsTemplate != null)
			{

				jmsTemplate.send(ENQUIRY_MESSAGE_QUEQUE, new MessageCreator()
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
		Message receivedMessage = jmsTemplate.receive(ENQUIRY_MESSAGE_QUEQUE);

		if (receivedMessage instanceof TextMessage)
		{
			TextMessage textMessage = (TextMessage) receivedMessage;
			String text = textMessage.getText();
			JSONObject jsonObject2 = new JSONObject(text);
			String username = (String) jsonObject2.get("name");
			String subject = (String) jsonObject2.get("subject");
			String content = (String) jsonObject2.get("content");
			String email = (String) jsonObject2.get("toEmail");
			String phoneno = (String) jsonObject2.get("phoneno");
			int tripId = (Integer) jsonObject2.get("tripId");
			List<Trip> list = tripService.getCredits_AND_Email(tripId);
			String enquiryStatus = "";
			int enquiryDeduction = 0;
			if (list != null && list.size() > 0)
			{
				Trip trip = (Trip) list.get(0);
				int totalCredist = trip.getCredits();
				if (totalCredist >= Enquiry.DEFAULT_ENQUIRY_DEDUCTION)
				{

					// Send email
					enquiryStatus = Enquiry.STATUS_SENT;
					enquiryDeduction = Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
					totalCredist =
							totalCredist - Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
					loginService.updateCredits(email);

				} else
				{
					enquiryStatus = Enquiry.STATUS_PENDING;
					enquiryDeduction = Enquiry.DEFAULT_ENQUIRY_NON_DEDUCTION;
				}
				Enquiry enquiry =
						new Enquiry(tripId, username, enquiryStatus, phoneno,
								enquiryDeduction, email);
				enquiryService.addEnquiry(enquiry);
			}

		} else
		{
			logger.info("receiveJMS_Message TextMessage :" + receivedMessage);
		}
	}

	public void receiveJMS_Message(final String queueName) throws Exception
	{
		Message receivedMessage = jmsTemplate.receive(queueName);

		if (receivedMessage instanceof TextMessage)
		{
			TextMessage textMessage = (TextMessage) receivedMessage;
			String text = textMessage.getText();
			JSONObject jsonObject2 = new JSONObject(text);
			int enquiryId = (Integer) jsonObject2.get("enquiryid");
			String email = (String) jsonObject2.get("email");
			int tripId = (Integer) jsonObject2.get("tripId");
			List<Trip> list = tripService.getCredits_AND_Email(tripId);
			String enquiryStatus = "";
			int enquiryDeduction = 0;
			if (list != null && list.size() > 0)
			{

				Trip trip = (Trip) list.get(0);
				int totalCredist = trip.getCredits();
				if (totalCredist >= Enquiry.DEFAULT_ENQUIRY_DEDUCTION)
				{

					// Send email
					Enquiry enquiry =
							new Enquiry(Enquiry.DEFAULT_ENQUIRY_DEDUCTION,
									enquiryId, Enquiry.STATUS_SENT);
					enquiryService.updateEnquiryStatus(enquiry);

					enquiryStatus = Enquiry.STATUS_SENT;
					enquiryDeduction = Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
					totalCredist =
							totalCredist - Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
					loginService.updateCredits(email);

				}
			}

		} else
		{
			logger.info("receiveJMS_Message TextMessage:" + receivedMessage);
		}
	}

	public void startJMSThread()
	{
		Start_JMS_THREAD start_JMS_THREAD = new Start_JMS_THREAD("JMS THREAD");
		Start_JMS_ACTIVATION_THREAD start_JMS_ACTIVATION_THREAD =
				new Start_JMS_ACTIVATION_THREAD("Activation thread");
		start_JMS_THREAD.start();
		start_JMS_ACTIVATION_THREAD.start();
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
					TimeUnit.MILLISECONDS.sleep(60);
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

	class Start_JMS_ACTIVATION_THREAD extends Thread
	{
		public final int STATUS_START = 0;
		public final int STATUS_STOP = 1;
		public final int TIME_INTERVAL = 1000; // 1 mins

		private int status = STATUS_START;

		public Start_JMS_ACTIVATION_THREAD(String str)
		{
			super(str);
		}

		public void run()
		{
			while (status == STATUS_START)
			{
				try
				{
					receiveJMS_Message(ACTIVATE_ENQUIRY_MESSAGE_QUEQUE);
					TimeUnit.MILLISECONDS.sleep(60);
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
