<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="util"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/css/ctconsumerreviews.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/c_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Comments</h3>
					</div>
					<c:if test="${not empty model.responseData.reviewsList}">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Trip Title</th>
									<th>Operation</th>
								</tr>
							<thead>
								<c:forEach items="${model.responseData.reviewsList}"
									var="element" varStatus="loop">
									<tr>
										<td>${(loop.index) + 1}</td>
										<td>${element.title}</td>
										<td class="ctConsumerComment"
											pk_tripid="${util:getBase64Encode(element.tripid) }"><i
											class="fa fa-file-text"></i> Add Comment</td>
									</tr>
								</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.reviewsList}">
						<span class="ctDangerDefaultTxt"> No Comments Available</span>
					</c:if>
					<div class="pagination-wrap ctPaginationDiv" style="display: none;">
						<ul id="pagination-demo" class="pagination-sm pull-right"></ul>
					</div>
					<form class="form-horizontal ctConsumerCommentsFrm">
						<h2 style="font-family: Junction; font-weight: bold;">Comments</h2>
						<div class="form-group">
							<label style="font-family: Junction; font-weight: bold;"
								class="col-lg-3 col-md-3 col-sm-3 control-label">Start
								Ratting </label>
							<div style="font-family: Junction; font-weight: bold;"
								class="col-lg-9 col-md-9 col-sm-9">
								<div id="star"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label"
								style="font-family: Junction; font-weight: bold;">Email <span
								class="ctRequired"> (*)</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<input type="text" class="form-control  ctConsumerEmail"
									name="phoneNo" value="" placeholder="Email *">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label"
								style="font-family: Junction; font-weight: bold;">Username
								<span class="ctRequired"> (*) 
							</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<input type="text" class="form-control  ctConsumerName"
									name="phoneNo" value="" placeholder="Username *">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label"
								style="font-family: Junction; font-weight: bold;">Comment
								<span class="ctRequired"> (*) 
							</label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<textarea class="form-control ctConsumerCommentTxt" rows="5"
									id="comment"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label"
								style="font-family: Junction; font-weight: bold;"></label>
							<div class="col-lg-9 col-md-9 col-sm-9">
								<button type="button"
									class="btn btn-primary ctAddConsumerReviewsBtn">Submit</button>
								<img class="ctConsumerLoading"
									src="${baseURL}/assest/img/loading.gif">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctconsumerreviews.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script src="${baseURL}/assest/plugin/startrating/jquery.raty.js"></script>
<script type="text/javascript">
    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerReviewsMenu').addClass('active');
    $('div#star').raty(
	    {
		"path" : "http://" + location.host + "/" + ctDAO.CONTEXT_NAME
			+ "/assest/plugin/startrating/img/"
	    });
    ctInitSetReviews();
    var numEntries = '${model.responseData.numEntries}', MAX_ENTRIES = 10, START_PAGE = 1;
    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/consumer/getConsumerReviewPagination";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctSetCommentsPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		START_PAGE, URL);

    }
</script>