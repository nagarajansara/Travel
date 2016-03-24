var START_RATING_DEFAULT_VALUE = 0;

function ctInitViewListing() {
    ctSubmitBtn();
    ctSetSavedTrips();

    $("#star > img").click(function() {
	var score = $(this).attr("alt");
	START_RATING_DEFAULT_VALUE = score;
    });

    $('.ctSUbmitEnquireForm')
	    .click(
		    function() {

			var name = $('.ctName').val(), email = $(
				'.ctEnquiryEmail').val(), phoneno = $(
				'.ctPhoneNo').val(), param = {}, tripId = $(
				this).attr('PK_tripid');
			$('.ctEnquiryStatus').hide();
			if (bmpUtil.isTextFieldEmpty('.ctIsChkEmptyEnquiryVal')) {
			    param = {
				"tripId" : tripId,
				"username" : name,
				"phoneno" : phoneno,
				"email" : email
			    };
			    $('.ctAddCommentLoadingImage').show();
			    ctDAO
				    .addEnquiry(
					    param,
					    function(data) {
						if (data
							&& bmpUtil.RESPONSE_STATUS == data.responseStatus) {
						    $(
							    '.ctAddCommentLoadingImage')
							    .hide();
						    $('.ctEnquiryStatus')
							    .show();
						    $('.ctEnquiryStatus')
							    .text(
								    "Vendor call you soon");

						} else {
						    $(
							    '.ctAddCommentLoadingImage')
							    .hide();
						    $('.ctNoCreditsDiv').show();
						    $('.ctNoCreditsTxt').text(
							    data.responseMsg);
						}
					    });
			} else {
			    alert('Please fill all the data');
			}

		    });
    $('.ctEnquiryModalClose, .ctViewListingCloseBtn').click(function() {
	$('.ctEnquiryStatus').hide();
    });

    $('.ctViewLisitingLoadMore')
	    .click(
		    function() {
			var currentPage = parseInt($('#ctViewListingNumEntries')
				.val()), numEntries = parseInt($(
				'#ctCommentsNumEntries').val()), params = {};
			currentPage = currentPage + 1;
			if (currentPage * ctDAO.TOTAL_RECORDS_PER_PAGE <= numEntries) {
			    params["tripId"] = bmpUtil.getLastStartingURL();
			    params["startIndx"] = currentPage;
			    $('.ctLoadingImage').show();
			    ctDAO
				    .getCommentsPagno(
					    params,
					    function(data) {
						if (data
							&& bmpUtil.RESPONSE_STATUS == data.responseStatus) {
						    $('.ctLoadingImage').hide();
						    var responseDatas = data.responseData, html = '', i;
						    $(
							    '#ctViewListingNumEntries')
							    .val(
								    responseDatas.currentPage);
						    if (responseDatas.reviewList.length > 0) {
							for (var i = 0; i < responseDatas.reviewList.length; i++) {
							    html += '<li>'
								    + '<div class="thumblist">'
								    + '<ul>'
								    + '<li>'
								    + '<figure><a href="#"><img alt="" src="http://'
								    + location.host
								    + "/"
								    + ctDAO.CONTEXT_NAME
								    + '/theme/extraimages/comment1.jpg"></a></figure>'
								    + '<div class="text">'
								    + '<a href="#">'
								    + responseDatas.reviewList[i].username
								    + '</a>'
								    + '<time datetime="2008-02-14 20:00"><i class="fa fa-calendar"></i>'
								    + responseDatas.reviewList[i].createdateformat
								    + '</time>'
								    + '<p>'
								    + responseDatas.reviewList[i].comment
								    + '</p>'
								    + '<!--<a class="replay-btn thbg-colorhover" href="#">Reply</a>-->'
								    + '</div>'
								    + '</li>'
								    + '</ul>'
								    + '</div>'
								    + '</li>';

							}
							$('.ctReviewsDetailsUL')
								.append(html);
						    }
						}
					    });

			} else {
			    $('.ctViewLisitingLoadMore').hide();
			}

		    });

};
function ctSubmitBtn() {
    $('.ctSubmitCommentBtn')
	    .click(
		    function() {
			var name = $('.ctViewListingName').val(), comment = $(
				'.ctViewListingComment').val(), email = $(
				'.ctViewListingEmail').val(), params = {};
			if (name && name.length > 0 && comment
				&& comment.length > 0) {
			    params["username"] = name;
			    params["comment"] = comment;
			    params["tripId"] = bmpUtil.getLastStartingURL();
			    params["startrating"] = START_RATING_DEFAULT_VALUE;
			    params["email"] = email;
			    $('.ctSuccessComment').hide();
			    if (bmpUtil.validateEmail(email)) {
				$('.ctTravelLoader').show();
				ctDAO
					.addComments(
						params,
						function(data) {
						    if (bmpUtil.RESPONSE_STATUS == data.responseStatus) {
							$('.ctSuccessComment')
								.show();
							$('.ctSuccessComment')
								.html(
									'Successfully commented');
							$('.ctTravelLoader')
								.hide();
						    } else {
							$('.ctSuccessComment')
								.show();
							$('.ctSuccessComment')
								.html(
									data.responseData);
							$('.ctTravelLoader')
								.hide();
						    }
						});
			    } else {
				alert("Please enter the valid email id");
			    }
			} else {
			    alert("Please fill all the datas");
			}
		    });
};
function ctSetSavedTrips() {
    $('.ctSavedTripList').click(function() {
	// var tripId = bmpUtil.getLastStartingURL(), param = {};
	var tripId = $('.ctSavedTripList').attr('PK_tripid');
	param = {
	    "tripId" : tripId
	};
	$('.ctLoadingSavedTrip').show();
	ctDAO.addSavedTrips(param, function(data) {
	    if (bmpUtil.RESPONSE_STATUS == data.responseStatus) {
		alert("Successfully added");
		$('.ctLoadingSavedTrip').hide();
	    } else {
		alert(data.responseData);
		$('.ctLoadingSavedTrip').hide();
	    }

	});
    });

}