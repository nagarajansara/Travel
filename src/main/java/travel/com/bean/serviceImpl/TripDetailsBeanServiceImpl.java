package travel.com.bean.serviceImpl;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import travel.com.bean.TripDetailsBean;
import travel.com.bean.service.TripDetailsBeanService;

public class TripDetailsBeanServiceImpl implements TripDetailsBeanService
{

	private RedisTemplate<String, TripDetailsBean> redisTemplate;

	private static String TRIP_DETAILS_BEAN_KEY = "TripDetailsBean";

	public RedisTemplate<String, TripDetailsBean> getRedisTemplate()
	{
		return redisTemplate;
	}

	public void setRedisTemplate(
			RedisTemplate<String, TripDetailsBean> redisTemplate)
	{
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void save(TripDetailsBean person)
	{
		this.redisTemplate.opsForHash().put(TRIP_DETAILS_BEAN_KEY,
				person.getId(), person);
	}

	@Override
	public TripDetailsBean find(String id)
	{
		return (TripDetailsBean) this.redisTemplate.opsForHash().get(
				TRIP_DETAILS_BEAN_KEY, id);
	}

	@Override
	public Map<Object, Object> findAll()
	{
		return this.redisTemplate.opsForHash().entries(TRIP_DETAILS_BEAN_KEY);
	}

	@Override
	public void delete(String id)
	{
		this.redisTemplate.opsForHash().delete(TRIP_DETAILS_BEAN_KEY, id);
	}

}