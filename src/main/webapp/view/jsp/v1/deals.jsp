<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctdeals.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Add a Deal</h3>
						&nbsp;<a href="#" data-toggle="tooltip" data-placement="right"
							title=""
							data-original-title="Add deals to improve your prospects"
							class="red-tooltip ctVendorDealsIcon"><i
							class="fa fa-info-circle"></i></a>
					</div>
					<form onsubmit="return ctAddDealsFrmSubmit();"
						action="${baseURL}/travelapi/vendor/addDeals" method="post"
						role="form" class="form-horizontal">
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
								Enter Trip Name </label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input style="width: 100%;" type="hidden" name="city"
									id="ctSelectedTripId">
							</div>
						</div>
						<div class="form-group"></div>
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
								Offer Description </label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<textarea name="offerdesc" placeholder="Description" rows="3"
									class="ctIsChkEmptyVal form-control ctDealsOfferDescription"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
								Offer Percentage </label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input name="offerPercentage" type="text"
									class="form-control ctOfferPercentage ctIsChkEmptyVal">
							</div>
						</div>
						<div class="form-group">
							<input type="hidden" class="ctDealsTripID" name="tripID">
						</div>
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B"></label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<button class="btn"
									style="background-color: #99cd4e; color: #ffffff;"
									type="submit">ADD</button>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="kd-section-title">
							<h3 class="ctFont_FamilyStyle">Deal History</h3>
						</div>
						<c:if test="${not empty model.responseData.dealsList}">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Trip Title</th>
										<th>Offer Percentage</th>
										<th>Grand Total</th>
										<th>Manage</th>
									</tr>
								</thead>
								<c:forEach items="${model.responseData.dealsList}" var="element"
									varStatus="loop">
									<tr>
										<td>${(loop.index) + 1}</td>
										<td>${element.title}</td>
										<td><input type="text"
											class="form-control ctDealsPercentage" disabled
											value="${element.offer_percentage}"></td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3"
												value="${element.price - (element.price * (element.offer_percentage / 100))}" />
										</td>
										<td><a class="ctManageDealsAnchor"
											href="javascript:void(0)"><i
												class="fa fa-edit ctManageDealsEditIcon"
												pk_dealsId="${element.id}"></a></i> &nbsp;<a
											href="javascript:void(0)" class="ctManageUpdateDealsAnchor"
											style="display: none"><i
												class="fa fa-refresh ctManageDealsUpdateIcon"
												pk_dealsId="${element.id}"></i> </a></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty model.responseData.dealsList}">
							<span class="ctDangerDefaultTxt"> No offers</span>
						</c:if>
						<div class="pagination-wrap ctPaginationDiv"
							style="display: none;">
							<ul id="pagination-demo" class="pagination-sm pull-right"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctdeals.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    $(".ctVendorDealsIcon").tooltip();
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorDealsMenu').addClass('active');
    $('.ctOfferPercentage').numeric();
    $('.ctDealsPercentage').numeric();
    var numEntries = '${model.responseData.numEntries}', MAX_ENTRIES = 10, START_PAGE = 1;
    var remoteDataConfig = {
	dropdownCssClass : 'bmSelect2Class',
	cache : "true",
	placeholder : "Enter Trip Name",
	minimumInputLength : 2,
	ajax : {
	    url : '${baseURL}/travelapi/vendor/getDeals.json',
	    dataType : 'json',
	    data : function(term, page) {
		searchTerm = term.toUpperCase();
		return {
		    startTitle : term,
		    page_limit : 10
		};
	    },
	    results : function(data, page) {
		return {
		    results : getMockData(data.model)
		};
	    }
	},
	formatResult : function(option) {
	    return "<div>" + option.text + "</div>";
	},
	formatSelection : function(option) {
	    return option.text;
	}
    };
    function getMockData(mockData) {
	mockData = JSON.parse(mockData);
	var foundOptions = [];
	for ( var key in mockData) {
	    if (mockData[key].text.toUpperCase().indexOf(searchTerm) >= 0) {
		foundOptions.push(mockData[key]);
	    }
	}

	return foundOptions;
    };

    $("#ctSelectedTripId").select2(remoteDataConfig);

    ctDealsInit();

    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host + "/travel/travelapi/vendor/getDealsPageNo";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}

	ctSetDealsPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		START_PAGE, URL);
    }
</script>