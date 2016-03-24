<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="util"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendorreview.css"
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
						<h3 class="ctFont_FamilyStyle">Review Section</h3>
						&nbsp; <a href="#" data-toggle="tooltip" data-placement="right"
							title=""
							data-original-title="We love feedbacks, so does our community! View/ Report reviews!"
							class="red-tooltip ctVendorReviewIcon"><i
							class="fa fa-info-circle"></i></a>
					</div>
					<c:if test="${not empty model.responseData.reviewList}">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip Title</th>
									<th>Report Email</th>
									<th>Comment</th>
								</tr>
							<thead>
								<c:forEach items="${model.responseData.reviewList}"
									var="element" varStatus="loop">
									<tr>
										<td>${(loop.index) + 1}</td>
										<td><a class="ctListingTitle" target="_blank"
											href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${util:getBase64Encode(element.id) }">
												${element.title} </a></td>
										<td>${element.email}</td>
										<td>${element.comment}</td>
									</tr>
								</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.reviewList}">
						<span class="ctDangerDefaultTxt"> No Comments Available</span>
					</c:if>
					<div class="pagination-wrap ctPaginationDiv" style="display: none;">
						<ul id="pagination-demo" class="pagination-sm pull-right"></ul>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctvendorreview.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorReviewsMenu').addClass('active');

    $(".ctVendorReviewIcon").tooltip();

    var numEntries = '${model.responseData.numEntries}', MAX_ENTRIES = 10, START_PAGE = 1;
    console.log("numEntries :" + numEntries);

    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/vendor/getReviewsPagination";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctSetCommentsPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		START_PAGE, URL);
    }
    ctInitVendorReview();
</script>