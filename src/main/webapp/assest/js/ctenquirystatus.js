function ctActivateEnquiryData(enquiryId, email, tripId, mobileNo, cbk) {
    var param = {
	"enquiryid" : enquiryId,
	"tripId" : tripId,
	"mobileNo" : mobileNo
    };
    ctDAO.activateEnquiry(param, function(data) {
	$('.ctNoCreditsWarning').hide();
	if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
	    cbk();
	} else {
	    $('.ctLoadingImg').hide();
	    $('.ctNoCreditsWarning').show();
	}

    });
};
function ctGetMoreSentEnquireDetails() {
    $('.ctMoreSentEnquiryDetailsBtn').click(function() {
	var param = {};
	$('.ctLoader').show();
	ctDAO.getMoreSentEnquireDetails(param, function(data) {
	    if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
		$('.ctLoader').hide();
		alert("Please wait untill admin send full records");
	    } else {
		$('.ctLoader').hide();
	    }

	});

    });
};
function ctSetListingPagination(numEntries, entryPerPage, visiblePageNo,
	startPage, hrefURL) {
    $(".ctListingPaginationDiv").css({
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
