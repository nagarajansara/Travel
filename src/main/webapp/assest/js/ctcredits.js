function ctCreditsOnInit() {
    $('.ctCreditsReductionDetailsBtn').click(function() {
	var param = {};
	$('.ctLoader').show();
	ctDAO.getCreditsReductionMoreDetails(param, function(data) {

	    if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
		$('.ctLoader').hide();
		alert("Please wait couple of hours admin send you email");
	    } else {
		$('.ctLoader').hide();
	    }
	});
    });

};