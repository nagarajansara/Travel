package travel.com.Redis;

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import travel.com.bean.LoginValidatedBean;
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
public interface LoginValidateService
{
	boolean isLoginValidated(LoginValidatedBean loginValidatedBean)
			throws Exception;

	void addLoginValidate(LoginValidatedBean loginValidatedBean)
			throws Exception;

	void addLoginValidate(Login login) throws Exception;

	boolean isLoginValidated(Login login) throws Exception;
}
