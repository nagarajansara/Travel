package travel.com.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import travel.com.dao.ReviewsDAO;
import travel.com.dao.TripDAO;
import travel.com.model.Reviews;
import travel.com.service.ReviewsService;

@SuppressWarnings("unused")
public class ReviewsServiceBO implements ReviewsService
{

	@Autowired
	@Qualifier("reviewsDAO")
	ReviewsDAO reviewsDAO;

	public List<Reviews> getReviewsBasedTripId(Reviews reviews)
			throws Exception
	{
		return reviewsDAO.getReviewsBasedTripId(reviews);
	}

	public void addComments(Reviews reviews) throws Exception
	{
		reviewsDAO.addComments(reviews);
	}

	public int getNumEntries(Reviews reviews) throws Exception
	{
		return reviewsDAO.getNumEntries(reviews);
	}

	public List<Reviews> getCommentsPagno(Reviews reviews) throws Exception
	{
		return reviewsDAO.getCommentsPagno(reviews);
	}
}
