package travel.com.dao;

import java.util.List;

import travel.com.model.Reviews;

public interface ReviewsDAO
{
	List<Reviews> getReviewsBasedTripId(Reviews reviews) throws Exception;

	void addComments(Reviews reviews) throws Exception;

	int getNumEntries(Reviews reviews) throws Exception;

	List<Reviews> getCommentsPagno(Reviews reviews)  throws Exception;
}
