package travel.com.dao;

import java.util.List;

import travel.com.model.Reviews;

public interface ReviewsDAO
{
	List<Reviews> getReviewsBasedTripId(Reviews reviews) throws Exception;

	void addComments(Reviews reviews) throws Exception;

	int getNumEntries(Reviews reviews) throws Exception;

	List<Reviews> getCommentsPagno(Reviews reviews) throws Exception;

	List<Reviews> getVendorReviews(Reviews reviews) throws Exception;

	int getVendorReviewsNumEntries(int tripId, int userId) throws Exception;

	List<Reviews> getReviews(int vendorId, int startIndx, int endIndx)
			throws Exception;

	int getReviewsNumEntries(int vendorId) throws Exception;

	List<Reviews> getConsumerReview(int userId, int startIndx, int endIndx)
			throws Exception;

	int getConsumerReviewNumEntries(int userId) throws Exception;
}
