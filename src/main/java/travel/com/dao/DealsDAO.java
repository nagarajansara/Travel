package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

public interface DealsDAO
{
	List<Deals> getDeals(Deals deals) throws Exception;

	void updateDeals(int offerPercentage, int id) throws Exception;

	int getDealsEntries(Deals deals) throws Exception;

	void addDeals(Deals deals) throws Exception;

	List<Deals> getAllDeals(int startIndx, int maxIndx) throws Exception;

	int getAllDealsNumEntries() throws Exception;
}
