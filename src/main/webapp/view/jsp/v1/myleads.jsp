<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsdatepicker.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctmyleads.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-9 col-sm-9 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">My Leads</h3>
					</div>
					<c:if test="${not empty model.responseData.leads}">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip Title</th>
									<th>Customer Email</th>
									<th>Booked Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.leads}" var="element"
									varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td style="text-transform: uppercase;"><a
											href="javascript:void(0)" target="_blank">${element.title }</a></td>
										<td style="text-transform: uppercase;"><a
											href="javascript:void(0)" target="_blank">${element.email }</a></td>
										<td style="text-transform: uppercase;"><a
											href="javascript:void(0)" target="_blank">${element.createdat }</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.leads}">
						<span class="ctDangerDefaultTxt">No data found</span>
					</c:if>
					<div class="col-md-12 col-sm-12">
						<div class="pagination-wrap ctLeadsPaginationDiv"
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
<script src="${baseURL}/assest/js/ctmyleads.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorLeadsMenu').addClass('active');

    var searchTerm = null, numEntries = '${model.responseData.numEntries}', MAX_ENTRIES = 10, START_PAGE = 1;

    console.log("numEntries :" + numEntries);

    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/booking/getLeadsPagination";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctMyleadsPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		START_PAGE, URL);
    }
</script>