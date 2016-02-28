package travel.com.bean.service;

import java.util.Map;

import travel.com.bean.TripDetailsBean;

public interface TripDetailsBeanService
{
	public void save(TripDetailsBean tripDetailsBean);

	public TripDetailsBean find(String id);

	public Map<Object, Object> findAll();

	public void delete(String id);
}