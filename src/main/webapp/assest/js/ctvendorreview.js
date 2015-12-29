function ctInitVendorReview() {
    $('.ctGetReviewDetailsSubmitBtn').click(function() {
	$('.ctViewVendorReview').html('');
	ctRenderReview();
    });
    $('.ctVendorReviewsPageNo')
	    .click(
		    function() {
			var currPageNo = parseInt($(this).attr('pk_No'));
			var selectObj = $('#ctSelectedTripId').select2('data'), tripId, param = {};
			if (selectObj) {
			    $('.ctReviewLoadMoreImg').show();
			    tripId = selectObj.id;
			    param["tripId"] = tripId;
			    param["startIndx"] = currPageNo;
			    $(this).attr('pk_No', currPageNo + 1);
			    ctDAO.getVendorReviewsPageNo(param, function(data) {
				ctCreateReviewHTML(data, currPageNo);
			    });
			} else {
			    alert('Please choose the tripdetails');
			}
		    });
};
function ctRenderReview() {
    var selectObj = $('#ctSelectedTripId').select2('data'), tripId, param = {};
    $('.ctKd_SectionDangerTxt').hide();
    if (selectObj) {
	$('.ctReviewLoadMoreImg').show();
	tripId = selectObj.id;
	param["tripId"] = tripId;
	ctDAO.getVendorReviews(param, function(data) {
	    ctCreateReviewHTML(data);
	});
    } else {
	alert('Please choose the trip title');
    }
};
function ctCreateReviewHTML(data, currentPage) {
    if (data.responseStatus == bmpUtil.RESPONSE_STATUS) {
	$('.ctReviewLoadMoreImg').hide();
	var customerReiews = (data.responseData).reviews, numEntries = data.responseData.numEntries, html = '', i;
	currentPage = currentPage ? currentPage : 0;
	currentPage = currentPage + 1;
	if (numEntries > 0) {
	    for (i = 0; i < customerReiews.length; i++) {
		html += '<li>'
			+ '<div class="thumblist">'
			+ '<ul>'
			+ '<li>'
			+ '<figure>'
			+ '<a href="#"><img src="/travel/theme/extraimages/comment1.jpg" alt=""></a>'
			+ '</figure>'
			+ '<div class="text">'
			+ '<a href="javascript:void(0)" class="ctReviewTitleTxt">'
			+ customerReiews[i].title + '</a></br>'
			+ '<!--<time datetime="">'
			+ '<i class="fa fa-calendar"></i>' + '27-11-15'
			+ '</time>-->' + '<p> ' + customerReiews[i].comment
			+ '</p>' + '</div>' + '</li>' + '</ul>' + '</div>'
			+ '</li>';
	    }
	    $('.ctViewVendorReview').append(html);
	    if (numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE
		    && currentPage * ctDAO.TOTAL_RECORDS_PER_PAGE < numEntries) {
		$('.ctReviewLoadMore').show();
	    } else {
		$('.ctReviewLoadMore').hide();
		$('.ctVendorReviewsPageNo').attr('pk_No', '1');
	    }
	} else {
	    $('.ctKd_SectionDangerTxt').show();
	    $('.ctReviewLoadMoreImg').hide();
	}
    } else {
	alert('Please enter the correct details');
    }
};
