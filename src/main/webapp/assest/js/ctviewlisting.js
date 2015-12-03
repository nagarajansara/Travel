function ctInitViewListing() {
    ctSubmitBtn();
    $('.ctSUbmitEnquireForm')
	    .click(
		    function() {

			var name = $('.ctName').val(), email = $(
				'.ctEnquiryEmail').val(), phoneno = $(
				'.ctPhoneNo').val(), param = {};
			if (bmpUtil.isTextFieldEmpty('.ctIsChkEmptyEnquiryVal')) {
			    param = {
				"tripId" : bmpUtil.getLastStartingURL(),
				"username" : name,
				"phoneno" : phoneno,
				"email" : email
			    }
			    ctDAO
				    .addEnquiry(
					    param,
					    function(data) {
						if (data
							&& bmpUtil.RESPONSE_STATUS == data.responseStatus) {
						    $('.ctEnquiryModalClose')
							    .trigger('click');
						} else {
						    $('.ctNoCreditsDiv').show();
						    $('.ctNoCreditsTxt').text(
							    data.responseMsg);
						}
					    });
			} else {
			    alert('Please fill all the data');
			}

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
    $('.ctSubmitCommentBtn').click(
	    function() {
		var name = $('.ctViewListingName').val(), comment = $(
			'.ctViewListingComment').val(), params = {};
		console.log("name : " + name);
		console.log("comment :" + comment);
		console.log("name : " + name.length);
		console.log("comment :" + comment.length);

		if (name && name.length > 0 && comment && comment.length > 0) {
		    params["username"] = name;
		    params["comment"] = comment;
		    params["tripId"] = bmpUtil.getLastStartingURL();
		    $('.ctSuccessComment').hide();
		    ctDAO.addComments(params, function(data) {
			if (bmpUtil.RESPONSE_STATUS == data.responseStatus) {
			    $('.ctSuccessComment').show();
			    $('.ctSuccessComment').html(
				    'Successfully commented');
			} else {
			    $('.ctSuccessComment').show();
			    $('.ctSuccessComment').html(data.responseData);

			}
		    });
		} else {
		    alert("Please fill all the datas");
		}

	    });
}