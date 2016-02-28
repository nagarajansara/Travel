package travel.com.dao;

import java.io.*;
import java.util.*;

import travel.com.model.*;

@SuppressWarnings(
{ "unused", "unchecked" })
public interface ConsumerDAO
{
	List<Login> getProfile(int userId) throws Exception;

	void updateConsumerProfile(Login login) throws Exception;

	int getConsumerTripDetailsNumEntries(Booking booking) throws Exception;

	List<Trip> getConsumerTripDetails(Booking booking) throws Exception;
}
