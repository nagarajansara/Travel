<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctlistingview.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" />
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<div class="kd-content">
	<section class="kd-pagesection"
		style="padding: 0px 0px 0px 0px; background: #ffffff;">
		<div class="container">
			<c:if test="${not empty model.responseStatus}">
				<c:if test="${model.responseStatus eq 200}">
					<c:if test="${not empty model.responseData.requestedParam}">
						<%-- <c:set var="numEntries"
							value="${model.responseData.requestedParam.numEntries}" /> --%>
						<c:set var="locationId"
							value="${model.responseData.requestedParam.locationId}" />
						<c:set var="locationName"
							value="${model.responseData.requestedParam.locationName}" />
						<c:set var="activityIds"
							value="${model.responseData.requestedParam.activityIds}" />
						<c:set var="fromdate"
							value="${model.responseData.requestedParam.fromdate}" />
						<c:set var="fromPrice"
							value="${model.responseData.requestedParam.fromPrice}" />
						<c:set var="toPrice"
							value="${model.responseData.requestedParam.toPrice}" />
					</c:if>
				</c:if>
			</c:if>
			<c:if test="${empty toPrice}">
				<c:set var="toPrice" value="1000" />
			</c:if>
			<div class="row ctFont_FamilyStyle">
				<div class="col-md-12">
					<div class="col-md-3">
						<div class="bs-example">
							<form class="ctSearchListingForm"
								action="${baseURL}/travelapi/trip/getFilterTripsDetails"
								id="searchFieldsForm" method="POST"
								onsubmit="return ctFilterTrips()">
								<div id="accordion" class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="#collapseOne" data-parent="#accordion"
													data-toggle="" class=""> Location </a>
											</h4>
										</div>
										<div class="panel-collapse collapse in">
											<div class="panel-body">
												<c:choose>
													<c:when
														test="${not empty locationId && locationId ne 'EMPTY'}">
														<input data-option="${locationName}" style="width: 100%;"
															value="${locationId}" type="hidden"
															id="ctSelectListingViewActivityType" name="locationid">
														<input type="hidden" value="${locationName}"
															name="startLocation" class="ctStartLocation">
													</c:when>
													<c:otherwise>
														<input style="width: 100%;" type="hidden"
															name="startpoint" id="ctSelectListingViewActivityType">
														<input type="hidden" value="EMPTY" name="startLocation"
															class="ctStartLocation">
													</c:otherwise>
												</c:choose>

											</div>
											<div class="panel-body">
												<input type="text" name="fromdate" placeholder="From Date"
													class="bmQuoteCommonFontStyle" id="ctListingFromDate" />
											</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="#collapseOne" data-parent="#accordion"
													data-toggle="" class="">Activity Type</a>
											</h4>
										</div>
										<div class="panel-collapse collapse in" id="collapseOne"
											style="height: auto;">
											<div class="panel-body ctListViewActivity">
												<div class="checkbox">
													<c:if test="${not empty model.responseData.activityType}">
														<c:forEach items="${model.responseData.activityType}"
															var="element" varStatus="loop">
															<label> <input type="checkbox"
																name="activitytype" pk_name="${element.name }"
																class="fuelType ctActivityTypeId" value="${element.id}">
																<c:out value="${element.name}">${element.name }</c:out>
															</label>
														</c:forEach>
													</c:if>

												</div>
											</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="#collapseOne" data-parent="#accordion"
													data-toggle="" class="">Price</a>
											</h4>
										</div>
										<div class="panel-collapse collapse in">
											<div class="panel-body">
												<p>
													<input type="text" disabled id="price"
														style="border: 0; width: 100%; color: #000000; font-weight: bold;">
												</p>
												<input type="hidden" value="10" class="ctFromPriceHidden"
													name="fromprice"> <input type="hidden"
													class="ctToPriceHidden" value="${toPrice }" name="toprice">
												<div id="slider-3"></div>
											</div>
										</div>
									</div>
									<div class="panel">
										<div class="">
											<input type="submit" class="custom-btn kd-small ctListView"
												value="Filter">
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-8">
						<div class="kd-section-title">
							<h3>Listing View</h3>
						</div>
						<div class="kd-blog-list kd-blogmedium">
							<c:if test="${not empty model.responseData.tripDetails}">
								<c:set var="numEntries" value="${model.responseData.numEntries}" />
								<div class="row">
									<c:forEach items="${model.responseData.tripDetails}"
										var="element" varStatus="loop">
										<article class="col-md-12">
											<div class="bloginner" style="width: 100% !important">
												<figure>
													<a href="#"><img width="255" height="191"
														src="${hostName}:${port}/travelimages/compress/${(element.tripimagename)}"
														alt=""></a>
													<figcaption>
														<a target="_blank"
															href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${element.id}"
															" class="fa fa-plus-circle"></a>
													</figcaption>
												</figure>
												<section class="kd-bloginfo">
													<h2>
														<a class="ctListingTitle" target="_blank"
															href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${element.id}">${ element.title}
														</a>
													</h2>
													<ul class="kd-postoption">
														<li><a href="#" class="thcolorhover">Start Date </a></li>
														<li><time datetime="">| ${ element.dateformat }</time></li>
													</ul>
													<p>${element.guidelines }</p>
													<div class="kd-usernetwork">
														<ul class="kd-blogcomment">
															<li><a href="#" class="thcolorhover"><i
																	class="fa fa-comments-o"></i> ${element.reviewscount }</a></li>
															<li><a href="#" class="thcolorhover"><i
																	class="fa fa-eye"></i> ${element.viwerscount }</a></li>
														</ul>
														<div class="kd-social-network">
															<ul>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-facebook"></i></a></li>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-twitter"></i></a></li>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-tumblr"></i></a></li>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-google-plus"></i></a></li>
															</ul>
														</div>
													</div>
												</section>
											</div>
										</article>
									</c:forEach>
								</div>
							</c:if>
							<c:if test="${empty model.responseData.tripDetails}">
								<div align="center" class="row ctNoDataFoundRow">
									<span> No listing found</span>
								</div>
							</c:if>
						</div>
						<div class="pagination-wrap ctListingPaginationDiv"
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
<script src="${baseURL}/jquery.js/jquery-ui.js"></script>
<script src="${baseURL}/assest/js/ctlistingview.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/datepicker/tsdatepicker.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    var numEntries = '${numEntries}', locationId = '${locationId}', locationName = '${locationName}', activityIds = '${activityIds}', fromdate = '${fromdate}', fromPrice = '${fromPrice}', toPrice = '${toPrice}', START_PAGE = 1, MAX_ENTRIES = 2, requestedFilterParams = [
	    locationId, locationName, activityIds, fromdate, toPrice ], requestedFilterParamsJSON = {
	"startpoint" : locationId,
	"fromdate" : fromdate,
	"activitytype" : activityIds,
	"toprice" : toPrice,
	"fromprice" : fromPrice,
	"startLocation" : locationName
    }, isTripDetails = '${model.responseData.istripDetails}';
    console.log("fromPrice :" +fromPrice);
    console.log("toPrice :" +toPrice);
    console.log("numEntries :" +numEntries);
    ctInitSetFilterData(locationId, activityIds, fromPrice, toPrice, fromdate);
    if (ctChkRequestedFilterParamsEmpty(requestedFilterParams)
	    || isTripDetails.indexOf('true') >= 0) {

	if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	    var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		    + location.host
		    + "/travel/travelapi/trip/getTripDetailsPageno";
	    lastPart = parseInt(lastPart);
	    if (lastPart && typeof lastPart === 'number') {
		START_PAGE = lastPart;
	    }
	    ctSetListingPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		    START_PAGE, URL);
	}
    } else {
	if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	    var params = JSON.stringify(requestedFilterParamsJSON);
	    var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		    + location.host
		    + "/travel/travelapi/trip/getFilterTripsDetailsPageNo/"
		    + params;
	    lastPart = parseInt(lastPart);
	    if (lastPart && typeof lastPart === 'number') {
		START_PAGE = lastPart;
	    }
	    ctSetListingPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		    START_PAGE, URL);
	}
    }
</script>

