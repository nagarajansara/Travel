function ctActivateEnquiryData(enquiryId, email, tripId, cbk) {
    var param = {
	"enquiryid" : enquiryId,
	"email" : email,
	"tripId" : tripId
    };
    ctDAO.activateEnquiry(param, function(data) {
	if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
	    cbk();
	} else {
	    $('.ctLoadingImg').hide();
	    alert(data.responseData);
	}

    });
}
