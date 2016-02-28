package travel.com.bean.service;

import java.util.Map;

import travel.com.bean.ReviewsBean;

public interface ReviewsBeanService
{
	public void save(ReviewsBean reviewsBean);

	public ReviewsBean find(String id);

	public Map<Object, Object> findAll();

	public void delete(String id);
}
