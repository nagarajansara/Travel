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

	final String ENQUIRY_MESSAGE_QUEQUE = "enquiryMsg";
	public final String ACTIVATE_ENQUIRY_MESSAGE_QUEQUE = "activate_enquiryMsg";
	public final String REGISTERATION_QUEUE = "login_registration";

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

		try
		{
			if (receivedMessage instanceof TextMessage)
			{
				TextMessage textMessage = (TextMessage) receivedMessage;
				String text = textMessage.getText();
				JSONObject jsonObject2 = new JSONObject(text);
				String username = (String) jsonObject2.get("name");
				String subject = (String) jsonObject2.get("subject");
				String content = (String) jsonObject2.get("content");
				String toEmail = (String) jsonObject2.get("toEmail");
				String cutomerEmail = (String) jsonObject2.get("customerEmail");
				String phoneno = (String) jsonObject2.get("phoneno");
				int tripId = (Integer) jsonObject2.get("tripId");

				List<Trip> list = tripService.getCredits_AND_Email(tripId); // TODO

				String enquiryStatus = "";
				int enquiryDeduction = 0;
				if (list != null && list.size() > 0)
				{
					Trip trip = (Trip) list.get(0);
					int totalCredist = trip.getCredits();
					String tripOwnerEmail = trip.getEmail();
					if (totalCredist >= Enquiry.DEFAULT_ENQUIRY_DEDUCTION)
					{
						try
						{

							HashMap<String, String> map =
									new HashMap<String, String>();
							String tripURL =
									(utilities.isServer()) ? ("<a href=\"http://www.saratravel.tk/travel/travelapi/trip/getTripDetailsBasedId/"
											+ tripId + "\">Trip Details</a>")
											: "<a href=\"http://localhost:8082/travel/travelapi/trip/getTripDetailsBasedId/"
													+ tripId
													+ "\">Trip Details</a>";

							map.put("CT_TRIP_URL_MACRO", tripURL);
							map.put("CT_CUST_EMAIL_MACRO", cutomerEmail);
							map.put("CT_MOBILE_MACRO", phoneno);

							String filePath =
									appProp.getHTMLDir() + "enquiry.html";
							content = utilities.getFileContent(filePath);
							content = utilities.replaceMacro(content, map);

							// SEND EMAIL
							String[] toAddr =
							{ toEmail };
							sendMail.sendMail(toAddr, subject, content,
									appProp.getAdminMailId(),
									appProp.getAdminName());

							enquiryStatus = Enquiry.STATUS_SENT;
							enquiryDeduction =
									Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
							totalCredist =
									totalCredist
											- Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
							loginService.updateCredits(tripOwnerEmail,
									totalCredist);
						} catch (Exception ex)
						{
							throw ex;
						}

					} else
					{

						HashMap<String, String> map =
								new HashMap<String, String>();

						String filePath =
								appProp.getHTMLDir() + "pendingenquiry.html";
						content = utilities.getFileContent(filePath);
						content = utilities.replaceMacro(content, map);

						// SEND EMAIL
						String[] toAddr =
						{ toEmail };
						sendMail.sendMail(toAddr, "Enquiry pending", content,
								appProp.getAdminMailId(),
								appProp.getAdminName());

						enquiryStatus = Enquiry.STATUS_PENDING;
						enquiryDeduction =
								Enquiry.DEFAULT_ENQUIRY_NON_DEDUCTION;
					}

					Enquiry enquiry =
							new Enquiry(tripId, username, enquiryStatus,
									phoneno, enquiryDeduction, cutomerEmail);
					enquiryService.addEnquiry(enquiry);
				}

			} else
			{
				logger.info("receiveJMS_Message TextMessage :"
						+ receivedMessage);
			}
		} catch (Exception ex)
		{

			logger.error("receiveJMS_Message :" + ex.getMessage());
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
			int tripId = (Integer) jsonObject2.get("tripId");
			String tripOwnerEmail = (String) jsonObject2.get("tripowneremail");
			String enquiredEmail = (String) jsonObject2.get("enquiredemail");
			String mobileNo = (String) jsonObject2.get("mobileNo");

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

					HashMap<String, String> map = new HashMap<String, String>();
					String tripURL =
							(utilities.isServer()) ? ("<a href=\"http://www.saratravel.tk/travel/travelapi/trip/getTripDetailsBasedId/"
									+ tripId + "\">Trip Details</a>")
									: "<a href=\"http://localhost:8082/travel/travelapi/trip/getTripDetailsBasedId/"
											+ tripId + "\">Trip Details</a>";

					map.put("CT_TRIP_URL_MACRO", tripURL);
					map.put("CT_CUST_EMAIL_MACRO", enquiredEmail);
					map.put("CT_MOBILE_MACRO", mobileNo);

					String filePath = appProp.getHTMLDir() + "enquiry.html";
					String content = utilities.getFileContent(filePath);
					content = utilities.replaceMacro(content, map);

					// SEND EMAIL
					String[] toAddr =
					{ tripOwnerEmail };
					sendMail.sendMail(toAddr, appProp.getEnqurirySubject(),
							content, appProp.getAdminMailId(),
							appProp.getAdminName());

					Enquiry enquiry =
							new Enquiry(Enquiry.DEFAULT_ENQUIRY_DEDUCTION,
									enquiryId, Enquiry.STATUS_SENT);
					enquiryService.updateEnquiryStatus(enquiry);

					enquiryStatus = Enquiry.STATUS_SENT;
					enquiryDeduction = Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
					totalCredist =
							totalCredist - Enquiry.DEFAULT_ENQUIRY_DEDUCTION;
					loginService.updateCredits(tripOwnerEmail, totalCredist);

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
		Start_JMS_REGISTRATION_THREAD start_JMS_REGISTRATION_THREAD =
				new Start_JMS_REGISTRATION_THREAD("Registration");
		start_JMS_THREAD.start();
		start_JMS_ACTIVATION_THREAD.start();
		start_JMS_REGISTRATION_THREAD.start();
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

	class Start_JMS_REGISTRATION_THREAD extends Thread
	{
		public final int STATUS_START = 0;
		public final int STATUS_STOP = 1;
		public final int TIME_INTERVAL = 1000; // 1 mins

		private int status = STATUS_START;

		public Start_JMS_REGISTRATION_THREAD(String str)
		{
			super(str);
		}

		public void run()
		{
			while (status == STATUS_START)
			{
				try
				{
					receiveRegistration_JMS_Message(REGISTERATION_QUEUE);
					TimeUnit.MILLISECONDS.sleep(60);
				} catch (Exception e)
				{
					logger.error("Start_JMS_REGISTRATION_THREAD :"
							+ e.getMessage());
				}
			}
		}

		public void receiveRegistration_JMS_Message(String quequeName)
		{
			Message receivedMessage = jmsTemplate.receive(quequeName);
			String CUSTOMER_ROLE = "ROLE_CUSTOMER";
			try
			{
				if (receivedMessage instanceof TextMessage)
				{
					TextMessage textMessage = (TextMessage) receivedMessage;
					String text = textMessage.getText();
					JSONObject jsonObject = new JSONObject(text);
					String[] toEmail =
					{ (String) jsonObject.get("email") };
					String[] adminEmail =
					{ appProp.getAdminMailId() };

					String role = (String) jsonObject.get("role");
					String name = (String) jsonObject.get("name");
					String stateName = (String) jsonObject.get("stateName");

					// CLIENT NOTIFICATION
					sendMail.sendMail(toEmail, appProp.getMailSubject(),
							"Wellcome", appProp.getAdminMailId(),
							appProp.getAdminName());

					HashMap<String, String> map = new HashMap<String, String>();
					map.put("CT_EMAIL_MACRO", toEmail[0]);
					map.put("CT_NAME_MACRO", name);
					map.put("CT_ROLE_MACRO", role);
					map.put("CT_CITY_MACRO", stateName);

					String filePath =
							appProp.getHTMLDir() + "userregister.html";
					String content = utilities.getFileContent(filePath);
					content = utilities.replaceMacro(content, map);

					// ADMIN NOTIFICATION
					sendMail.sendMail(adminEmail, "NEW USER REGISTER "
							+ utilities.getDateTime(), content,
							appProp.getAdminMailId(), appProp.getAdminName());

				}
			} catch (Exception ex)
			{

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
