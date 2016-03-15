var START_RATING_DEFAULT_VALUE = 0, TRIP_ID = 0;
function ctSetCommentsPagination(numEntries, entryPerPage, visiblePageNo,
	startPage, hrefURL) {
    $(".ctPaginationDiv").css({
	display : "block"
    });
    numEntries = numEntries ? numEntries : 50;
    entryPerPage = entryPerPage ? entryPerPage : 10;
    visiblePageNo = visiblePageNo ? visiblePageNo : 5;
    startPage = startPage ? startPage : 1;
    if (numEntries && entryPerPage) {
	var config = {
	    "parentSelector" : "#pagination-demo",
	    "totalPages" : numEntries,
	    "visiblePages" : visiblePageNo,
	    "entryPerPage" : entryPerPage,
	    "startPage" : startPage,
	    "cbk" : function(event, pageNo) {

	    },
	    "HREF_URL" : hrefURL
	}, renderPagination = new RenderPagination(config);
	renderPagination.render();
    }
};
function ctInitSetReviews() {
    $('.ctConsumerComment').click(function() {
	$('.ctConsumerCommentsFrm').show();
	TRIP_ID = $(this).attr('pk_tripid');
    });
    $('.ctAddConsumerReviewsBtn')
	    .click(
		    function() {
			var param, comment = $('.ctConsumerCommentTxt').val(), name = $(
				'.ctConsumerName').val(), email = $(
				'.ctConsumerEmail').val(), param = {
			    "tripId" : TRIP_ID,
			    "comment" : comment,
			    "username" : name,
			    "email" : email,
			    "startrating" : START_RATING_DEFAULT_VALUE
			};
			if (name && comment && email && name.length > 0
				&& comment.length && email.length > 0) {
			    if (bmpUtil.validateEmail(email)) {

				$('.ctConsumerLoading').show();
				ctDAO
					.addComments(
						param,
						function(data) {
						    if (data
							    && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
							$('.ctConsumerLoading')
								.hide();
							$(
								'.ctConsumerCommentTxt')
								.val('');
							$('.ctConsumerName')
								.val('');
							$('.ctConsumerEmail')
								.val('');
							alert("Successfully added");
						    } else {
							$('.ctConsumerLoading')
								.hide();
						    }
						});
			    } else {
				alert("Please enter proper email id");
			    }
			} else {
			    alert("Please enter all the details");
			}
		    });

    $("#star > img").click(function() {
	var score = $(this).attr("alt");
	START_RATING_DEFAULT_VALUE = score;
    });
};