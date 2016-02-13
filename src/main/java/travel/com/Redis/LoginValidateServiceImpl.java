package travel.com.Redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import travel.com.bean.LoginValidatedBean;
import travel.com.model.Login;

@SuppressWarnings(
{ "unused", "unchecked" })
public class LoginValidateServiceImpl implements LoginValidateService
{

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	public boolean isLoginValidated(LoginValidatedBean loginValidatedBean)
			throws Exception
	{
		boolean isLoginValidated = false;
		System.out.println("PASSWORD :"
				+ redisTemplate.opsForValue()
						.get(loginValidatedBean.getEmail()));
		return isLoginValidated;
	}

	@Override
	public void addLoginValidate(LoginValidatedBean loginValidatedBean)
			throws Exception
	{

		if (loginValidatedBean != null)
		{
			redisTemplate.opsForValue().set(loginValidatedBean.getEmail(),
					loginValidatedBean.getPassword());
		} else
		{
			throw new Exception("Bean class not found");
		}

	}

	@Override
	public void addLoginValidate(Login login) throws Exception
	{
		if (login != null)
		{
			redisTemplate.opsForHash().put("users", login.hashCode(), login);
		} else
		{
			throw new Exception("Bean class not found");
		}
	}

	public boolean isLoginValidated(Login login) throws Exception
	{
		boolean isLoginValidated = false;
		Login loginData =
				(Login) redisTemplate.opsForHash().get("users",
						login.hashCode());
		System.out.println("EMAIL :" + loginData.getEmail());
		if (loginData != null)
		{
			isLoginValidated = true;
		}
		return isLoginValidated;
	}
}
