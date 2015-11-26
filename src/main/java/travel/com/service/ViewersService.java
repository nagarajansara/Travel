package travel.com.service;

import travel.com.model.Viewers;

public interface ViewersService
{
	void updateViewersCount(Viewers viewers) throws Exception;
	void insertViewers(Viewers viewers) throws Exception;
	int getNumEntries(Viewers viewers) throws Exception;
}
