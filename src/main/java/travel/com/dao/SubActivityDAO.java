package travel.com.dao;

import java.util.List;

import travel.com.model.SubActivity;

public interface SubActivityDAO
{
	void addSubActivity(SubActivity subActivity) throws Exception;

	List<SubActivity> getSubActivity(String status) throws Exception;
}
