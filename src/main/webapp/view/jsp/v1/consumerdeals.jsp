<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/css/ctconsumermytravels.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctConsumerContainer">
			<div class="row">
				<%@ include file="lib/c_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Deals</h3>
					</div>
					<c:if test="${not empty model.responseData.deals}">
						<table class="table table-striped ctEnquiryTableStatus">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip tittle</th>
									<th>Offer</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.deals}" var="element"
									varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td><a target="_balnk"
											href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${element.tripid}"><c:out
													value="${ element.title}"></a> </c:out></td>
										<td><c:out value="${ element.offer_percentage}"></c:out></td>
										<td><c:out value="${ element.price}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.deals}">
						<span style="font-family: verdana; color: red; font-size: 12px;"> No record found</span>
					</c:if>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctconsumermytravels.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerDealsMenu').addClass('active');

    var numEntries = '${model.responseData.numEntries}';
    var MAX_ENTRIES = 5, START_PAGE = 1;

    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/consumer/getAllDealsPagination";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctSetConsumerDealsPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE,
		5, START_PAGE, URL);
    }
    function ctSetConsumerDealsPagination(numEntries, entryPerPage,
	    visiblePageNo, startPage, hrefURL) {
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
</script>
