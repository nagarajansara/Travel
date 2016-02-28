package travel.com.bean.serviceImpl;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import travel.com.bean.ReviewsBean;
import travel.com.bean.service.ReviewsBeanService;

public class ReviewsBeanServiceImpl implements ReviewsBeanService
{
	private RedisTemplate<String, ReviewsBean> redisTemplate;

	private static String REVIEWS_DETAILS_BEAN_KEY = "ReviewsBean";

	public RedisTemplate<String, ReviewsBean> getRedisTemplate()
	{
		return redisTemplate;
	}

	public void setRedisTemplate(
			RedisTemplate<String, ReviewsBean> redisTemplate)
	{
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void save(ReviewsBean person)
	{
		this.redisTemplate.opsForHash().put(REVIEWS_DETAILS_BEAN_KEY,
				person.getId(), person);
	}

	@Override
	public ReviewsBean find(String id)
	{
		return (ReviewsBean) this.redisTemplate.opsForHash().get(
				REVIEWS_DETAILS_BEAN_KEY, id);
	}

	@Override
	public Map<Object, Object> findAll()
	{
		return this.redisTemplate.opsForHash()
				.entries(REVIEWS_DETAILS_BEAN_KEY);
	}

	@Override
	public void delete(String id)
	{
		this.redisTemplate.opsForHash().delete(REVIEWS_DETAILS_BEAN_KEY, id);
	}

}
