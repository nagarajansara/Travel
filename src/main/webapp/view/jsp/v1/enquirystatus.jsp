<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="util"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctenquirystatus.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/toggle/cttoggle.css" media="screen" />

<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Enquire Status</h3>
						&nbsp; <a href="#" data-toggle="tooltip" data-placement="right"
							title=""
							data-original-title="View and action on the leads and booking that you have received"
							class="red-tooltip ctVendorEnquireStatusIcon"><i
							class="fa fa-info-circle"></i></a>
					</div>
					<!-- <div class="kd-section-title ctUserCreditsValue"></div> -->
					<div class="ctLoadingImg" style="display: none;">
						<img src="${baseURL}/assest/img/loading.gif">
					</div>
					<h1>Pending Enquiry</h1>
					<div class="ctNoCreditsWarning" style="display: none;">
						<span style="font-family: Junction; color: red; font-size: 12px;">
							Credits limit not eligible </span>
					</div>
					<c:if test="${not empty model.responseData.enquiry}">
						<table class="table table-striped ctEnquiryTableStatus">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip Name</th>
									<th>Status</th>
									<th>Activate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.enquiry}" var="element"
									varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td style="text-transform: uppercase;"><a
											href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${util:getBase64Encode(element.id) }"
											target="_blank">${element.title }</a></td>
										<td style="text-transform: uppercase;">${element.status }</td>
										<td class="ctEnquiryStatus" style="text-transform: uppercase;">
											<div class="checkbox">
												<label> <input data-toggle="toggle"
													class="ctToggleBtn" pk_email="${ element.email }"
													pk_ID="${element.id}" pk_tripId="${element.tripid }"
													pk_MobileNO="${element.phoneno }" data-size="mini"
													type="checkbox">
												</label>
											</div>
										</td>
										<c:set var="USER_CREDITS" value="${ element.credits }"></c:set>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.enquiry}">
						<span class="ctDangerDefaultTxt">No data found</span>
					</c:if>
					<div class="pagination-wrap ctListingPaginationDiv"
						style="display: none;">
						<ul id="pagination-demo" class="pagination-sm pull-right"></ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-md-3 col-lg-3 col-md-3 col-sm-3"></div>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<h1>Processing Enquiry</h1>
					<c:if
						test="${not empty model.responseData.processingEnquiryDetails}">
						<table class="table table-striped ctSentEnquiryStatus">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip Name</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach
									items="${model.responseData.processingEnquiryDetails}"
									var="element" varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td style="text-transform: uppercase;"><a
											href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${element.tripid}"
											target="_blank">${element.title }</a></td>
										<td style="text-transform: uppercase;">${element.status }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.processingEnquiryDetails}">
						<span class="ctDangerDefaultTxt">No data found</span>
					</c:if>
					<div class="form-group">
						<label class="col-lg-12 col-md-12 col-sm-12 control-label">
							<c:if
								test="${model.responseData.processingEnquiryDetailsNumEntries gt 5}">
								<a class="frame-btn thbg-color ctMoreSentEnquiryDetailsBtn"
									href="javascript:void(0)">Request email to more details</a>
								</br>&nbsp;
										<img class="ctLoader" style="display: none;"
									src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==">
							</c:if>
						</label>
					</div>
				</div>
				<div class="col-md-3 col-md-3 col-lg-3 col-md-3 col-sm-3"></div>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<h1>Expired Enquiry</h1>
					<c:if test="${not empty model.responseData.expiredEnquirys}">
						<table class="table table-striped ctSentEnquiryStatus">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip Name</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.expiredEnquirys}"
									var="element" varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td style="text-transform: uppercase;"><a
											href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${element.tripid}"
											target="_blank">${element.title }</a></td>
										<td style="text-transform: uppercase;">${element.status }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.expiredEnquirys}">
						<span class="ctDangerDefaultTxt">No data found</span>
					</c:if>
					<div class="form-group">
						<label class="col-lg-12 col-md-12 col-sm-12 control-label">
							<c:if test="${model.responseData.expiredEnquirysNumEntries gt 5}">
								<a class="frame-btn thbg-color ctMoreSentEnquiryDetailsBtn"
									href="javascript:void(0)">Request email to more details</a>
								</br>&nbsp;
										<img class="ctLoader" style="display: none;"
									src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==">
							</c:if>
						</label>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctenquirystatus.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script src="${baseURL}/assest/plugin/toggle/cttoggle.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorPendingStatus').addClass('active');
    var USER_CREDITS = '${USER_CREDITS}';
    var numEntries = '${model.responseData.numEntries}', START_PAGE = 1;

    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/login/getpendingenquiryPagination";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctSetListingPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		START_PAGE, URL);
    }

    if (USER_CREDITS == ctDAO.INITIAL_USER_CREDITS) {
	$('.ctUserCreditsValue')
		.append(
			'<a class="custom-btn frame-btn thbg-color" href="#" style="font-size: 12px;">Buy Credits</a>');
    }

    $(".ctToggleBtn")
	    .change(
		    function(event) {
			var enquiryId = $(this).attr('pk_ID'), email = $(this)
				.attr('pk_email'), tObj = this, currentValue = $(
				this).prop('checked'), tripId = $(this).attr(
				'pk_tripId'), mobileNo = $(this).attr(
				'pk_MobileNO');
			if (currentValue) {
			    $('.ctLoadingImg').show();
			    ctActivateEnquiryData(enquiryId, email, tripId,
				    mobileNo, function() {
					$('.ctLoadingImg').hide();
				    });
			} else {
			    alert("Once you click you can't do it again");
			}

		    });

    $(".ctVendorEnquireStatusIcon").tooltip();

    ctGetMoreSentEnquireDetails();
</script>