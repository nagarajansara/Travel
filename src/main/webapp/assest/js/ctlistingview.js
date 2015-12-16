var MAX_RANGE_VALUES = 20000, MIN_RANGE_VALUES = 100, DEFDAULE_EMPTY = "EMPTY";
$(function() {
	$('#ctListingFromDate').Zebra_DatePicker();
});
function ctFilterTrips() {
	var selectObj = $('#ctSelectListingViewActivityType').select2('data'), selectLocationId;
	if (selectObj)
	{
		selectLocationId = selectObj.id;
		$('.ctStartLocation').val(selectObj.text);
		$('.ctLisitingViewActivityId').val(selectLocationId);
	}
	return true;
};
function ctInitSetFilterData(locationId, activityIds, fromPrice, toPrice,
		fromDate) {
	$('#ctListingFromDate').val(fromDate);
	ctSetFilterDatas(locationId, activityIds, fromPrice, toPrice);
};
function ctSetFilterDatas(locationId, activityIds, fromPrice, toPrice) {
	fromPrice =
			(fromPrice && fromPrice.length > 0) ? fromPrice : 100;
	toPrice = (toPrice && toPrice.length > 0) ? toPrice : 300;
	if (activityIds && activityIds != DEFDAULE_EMPTY)
	{
		activityIds = activityIds.split(",");
		for (var i = 0; i < activityIds.length; i++)
		{
			$('.ctActivityTypeId[value = ' + activityIds[i] + ']').prop(
					'checked', true);
		}
	}
	
	$("#slider-3")
			.slider(
					{
						range : true,
						min : 0,
						max : MAX_RANGE_VALUES,
						values :
						[
								fromPrice, toPrice
						],
						slide : function(event, ui) {
							$("#price").val(
									"PRICE-INR " + ui.values[0] + " - "
											+ ui.values[1]);
							$('.ctFromPriceHidden').val(ui.values[0]);
							$('.ctToPriceHidden').val(ui.values[1]);
						}
					});
	$("#price").val(
			"PRICE-INR " + $("#slider-3").slider("values", 0) + " "
					+ $("#slider-3").slider("values", 1));

	var remoteDataConfig =
			{
				dropdownCssClass : 'bmSelect2Class',
				cache : "true",
				placeholder : "Enter your city",
				minimumInputLength : 2,
				ajax :
				{
					url : 'http://' + location.host + '/' + ctDAO.CONTEXT_NAME
							+ '/travelapi/city/getCityApi.json',
					dataType : 'json',
					data : function(term, page) {
						searchTerm = term.toUpperCase();
						return{
							locationname : term,
							page_limit : 10
						};
					},
					results : function(data, page) {
						return{
							results : getMockData(data.model)
						};
					}
				},
				formatResult : function(option) {
					return "<div>" + option.text + "</div>";
				},
				formatSelection : function(option) {
					return option.text;
				},
				initSelection : function(element, callback) {
					var id = element.val();
					var text = element.data('option');
					var data =
					{
						id : id,
						text : text
					};
					callback(data);
				},
				escapeMarkup : function(m) {
					return m;
				}
			};
	function getMockData(mockData) {
		mockData = JSON.parse(mockData);
		var foundOptions = [];
		for ( var key in mockData)
		{
			if (mockData[key].text.toUpperCase().indexOf(searchTerm) >= 0)
			{
				foundOptions.push(mockData[key]);
			}
		}

		return foundOptions;
	}
	;

	$("#ctSelectListingViewActivityType").select2(remoteDataConfig);

	if (locationId && locationId.length > 0 && DEFDAULE_EMPTY != locationId)
	{
		$("#ctSelectListingViewActivityType").select2('val', locationId);
	}

};
function ctSetListingPagination(numEntries, entryPerPage, visiblePageNo,
		startPage, hrefURL) {
	$(".ctListingPaginationDiv").css(
	{
		display : "block"
	});
	numEntries = numEntries ? numEntries : 50;
	entryPerPage = entryPerPage ? entryPerPage : 10;
	visiblePageNo = visiblePageNo ? visiblePageNo : 5;
	startPage = startPage ? startPage : 1;
	if (numEntries && entryPerPage)
	{
		var config =
		{
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
function ctChkRequestedFilterParamsEmpty(requestedFilterParam) {
	var isEmpty = true;
	if(requestedFilterParam)
	{
		for(var i = 0; i < requestedFilterParam.length; i++)
		{
			console.log("requestedFilterParam : " +requestedFilterParam);
			if(requestedFilterParam[i] && requestedFilterParam[i].length > 0
					&& requestedFilterParam[i] != DEFDAULE_EMPTY)
			{
				return false;
				return;
			}
			
		}
	}
	else
	{
		return true;
		return;
	}
	return isEmpty;
};
