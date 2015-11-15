$(function() {
	$('.ctProfileEditBtn').click(function() {
		console.log("ctProfileEditBtn");
		$('.ctProfileEditBtnParent').hide();
		$('.ctProfileUpdateBtnParent').show();
		$(".ctVendorProfileListingForm input").prop('disabled', false);
		$(".ctVendorProfileListingForm textarea").prop('disabled', false);
	});

});
function ctProfileDatas() {

	var isValid = true;
	if (!bmpUtil.isTextFieldEmpty('.ctIsChkEmptyVal'))
	{
		isValid = false;
		alert('Please fill all the details');
	}
	return isValid;
};