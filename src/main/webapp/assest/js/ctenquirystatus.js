function ctActivateEnquiryData(enquiryId, email, tripId, mobileNo, cbk) {
    var param = {
	"enquiryid": enquiryId,
	//"email": email,
	"tripId": tripId,
	"mobileNo": mobileNo
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
}
