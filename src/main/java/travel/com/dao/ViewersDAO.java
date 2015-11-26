package travel.com.dao;

import travel.com.model.Viewers;

public interface ViewersDAO
{
	void updateViewersCount(Viewers viewers) throws Exception;
	void insertViewers(Viewers viewers) throws Exception;
	int getNumEntries(Viewers viewers) throws Exception;
}
