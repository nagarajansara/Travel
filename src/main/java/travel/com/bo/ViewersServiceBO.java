package travel.com.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import travel.com.dao.BaseDAO;
import travel.com.dao.ViewersDAO;
import travel.com.model.Activity;
import travel.com.model.Viewers;
import travel.com.service.ViewersService;

@SuppressWarnings("unused")
public class ViewersServiceBO implements ViewersService
{

	@Autowired
	@Qualifier("viewersDAO")
	ViewersDAO viewersDAO;

	public void updateViewersCount(Viewers viewers) throws Exception
	{
		viewersDAO.updateViewersCount(viewers);
	}

	public void insertViewers(Viewers viewers) throws Exception
	{
		viewersDAO.insertViewers(viewers);
	}

	public int getNumEntries(Viewers viewers) throws Exception
	{
		return viewersDAO.getNumEntries(viewers);
	}
}
