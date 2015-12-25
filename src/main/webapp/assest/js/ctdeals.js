var MAX_OFFER_PERCENTAGE = 100;

function ctDealsInit() {

    $('.ctManageDealsEditIcon')
	    .click(
		    function() {
			var parent_tObj = $(this).parent().parent().parent(), tables = parent_tObj
				.find('td').eq(2);
			$('input', tables).prop('disabled', false);
			$('.ctManageUpdateDealsAnchor', parent_tObj).show();
			$('.ctManageDealsEditIcon', parent_tObj).hide();
		    });
    $('.ctManageDealsUpdateIcon')
	    .click(
		    function() {
			var parent_tObj = $(this).parent().parent().parent(), tables = parent_tObj
				.find('td').eq(2), dealsID = $(this).attr(
				'pk_dealsId'), offerPercentage = parseInt($(
				'input', tables).val()), param = {};
			if (MAX_OFFER_PERCENTAGE > offerPercentage) {
			    param["offer_percentage"] = offerPercentage;
			    param["dealId"] = dealsID;
			    ctDAO
				    .updateDeals(
					    param,
					    function(data) {
						if (bmpUtil.RESPONSE_STATUS == data.responseStatus) {
						    $(
							    '.ctManageUpdateDealsAnchor',
							    parent_tObj).hide();
						    $('.ctManageDealsEditIcon',
							    parent_tObj).show();

						} else {
						    $('.ctManageDealsEditIcon',
							    parent_tObj).show();

						}
					    });
			} else {
			    alert('Please bellow the value 100');
			    $('.ctManageDealsEditIcon', parent_tObj).show();

			}
			$('input', tables).prop('disabled', true);

		    });
};
function ctSetDealsPagination(numEntries, entryPerPage, visiblePageNo,
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
function ctAddDealsFrmSubmit() {
    var selectObj = $('#ctSelectedTripId').select2('data'), tripId, isSelected = true;
    if (selectObj && bmpUtil.isTextFieldEmpty('.ctIsChkEmptyVal')) {
	tripId = selectObj.id;
	$('.ctDealsTripID').val(tripId);
    } else {
	isSelected = false;
    }
    return isSelected;
}
