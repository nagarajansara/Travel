<%@ page language="java" contentType="text/html"%>
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
					</div>
					<div class="kd-section-title ctUserCreditsValue"></div>
					<div class="ctLoadingImg" style="display: none;">
						<img src="${baseURL}/assest/img/loading.gif">
					</div>
					<c:if test="${not empty model.responseData}">
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
								<c:forEach items="${model.responseData}" var="element"
									varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td style="text-transform: uppercase;"><a
											href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${element.tripid}"
											target="_blank">${element.title }</a></td>
										<td style="text-transform: uppercase;">${element.status }</td>
										<td class="ctEnquiryStatus" style="text-transform: uppercase;">
											<div class="checkbox">
												<label> <input data-toggle="toggle"
													class="ctToggleBtn" pk_email="${ element.email }"
													pk_ID="${element.id}" pk_tripId="${element.tripid }"
													data-size="mini" type="checkbox">
												</label>
											</div>
										</td>
										<c:set var="USER_CREDITS" value="${ element.credits }"></c:set>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData}">
						<span class="ctDangerDefaultTxt">No data found</span>
					</c:if>
				</div>
				<div class="row"></div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctenquirystatus.js"></script>
<script src="${baseURL}/assest/plugin/toggle/cttoggle.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorPendingStatus').addClass('active');
    var USER_CREDITS = '${USER_CREDITS}';
    if (USER_CREDITS == ctDAO.INITIAL_USER_CREDITS) {
	$('.ctUserCreditsValue')
		.append(
			'<a class="custom-btn frame-btn thbg-color" href="#" style="font-size: 12px;">Buy Credits</a>');
    }

    $(".ctToggleBtn").change(
	    function(event) {
		var enquiryId = $(this).attr('pk_ID'), email = $(this).attr(
			'pk_email'), tObj = this, currentValue = $(this).prop(
			'checked'), tripId = $(this).attr('pk_tripId');
		console.log("currentValue :" + currentValue);
		if (currentValue) {
		    $('.ctLoadingImg').show();
		    ctActivateEnquiryData(enquiryId, email, tripId, function() {
			$('.ctLoadingImg').hide();
		    });
		} else {
		    alert("Once you click you can't do it again");
		}

	    });
</script>