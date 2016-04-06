function ctLoginStatus(loginStatus) {
    ctSUbmitGetQuote();
    loginStatus = loginStatus ? parseInt(loginStatus) : 0;
    if (loginStatus && loginStatus == -1) {

	$('.ctErrorSpan').show();
	$('.ctOpenModal').trigger('click');
    }
};
function ctSUbmitGetQuote() {
    $('.ctSUbmitGetQuote')
	    .click(
		    function() {
			var name = $(".ctGetQuoteName").val(), email = $(
				".ctGetQuoteEmail").val(), phoneno = $(
				".ctGetQuotePhoneNo").val(), requirement = $(
				".ctQuoteRequirement").val(), param = {};
			if (name && name.length > 0 && email
				&& email.length > 0 && phoneno
				&& phoneno.length > 0 && requirement
				&& requirement.length > 0) {
			    param = {
				"name" : name,
				"email" : email,
				"phoneno" : phoneno,
				"requirement" : requirement
			    };
			    $(".ctGetQuoteLoadingImage").show();
			    ctDAO
				    .addGetQuoteDetails(
					    param,
					    function(data) {
						if (data) {
						    $(".ctGetQuoteLoadingImage")
							    .hide();
						    if (bmpUtil.RESPONSE_STATUS == data.responseStatus) {
							$('.ctCloseGetQuote')
								.trigger(
									'click');
						    }
						} else {
						    $(".ctGetQuoteLoadingImage")
							    .hide();
						}
					    });
			} else {
			    alert("Please enter all the details");
			}

		    });
}
