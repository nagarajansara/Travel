<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="util"%>
<%@ include file="lib/header.jsp"%>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
<link rel="stylesheet" href="${baseURL}/assest/css/ctsavedtrips.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row">
				<div class="col-md-9 col-sm-9">
					<div class="kd-section-title">
						<h3>Saved Trips</h3>
					</div>
					<div class="kd-package-list">
						<c:if test="${not empty model.responseData.savedTrips}">
							<c:set var="numEntries" value="${model.responseData.numEntries}" />
							<div class="row">
								<c:forEach items="${model.responseData.savedTrips}"
									var="element" varStatus="loop">
									<article class="col-md-12 ctBlogborderstyle">
										<article class="col-md-5">
											<figure>
												<a href="#"><img width="315" height="235"
													alt="${ element.title}" title="${ element.title}"
													src="${hostName}:${port}/${uploadedImageFolderName}/${element.tripimagename}"></a>
												<figcaption>
													<span class="package-price thbg-color">RS ${ element.price}</span>
													<div class="kd-bottomelement">
														<h5>
															<a href="#">${ element.title}</a>
														</h5>
														<div class="days-counter ctDaysCounter">
															<span>${ element.duration}</span> <br> days
														</div>
													</div>
												</figcaption>
											</figure>
										</article>
										<section class="kd-bloginfo col-md-7">
											<h2>
												<a class="ctListingTitle" target="_blank"
													href="${baseURL}/travelapi/trip/getTripDetailsBasedId/${util:getBase64Encode(element.tripid) }">${ element.title}
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
															class="fa fa-eye"></i> ${element.viewrscount }</a></li>
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
									</article>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${empty model.responseData.savedTrips}">
							<div align="center" class="row ctNoDataFoundRow">
								<span> No saved listing found</span>
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
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/jquery.js/jquery-ui.js"></script>
<script src="${baseURL}/assest/js/ctsavedtrips.js"></script>
<script src="${baseURL}/assest/plugin/datepicker/tsdatepicker.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    var numEntries = '${numEntries}', START_PAGE = 1, MAX_ENTRIES = 2;
    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var params = JSON.stringify(requestedFilterParamsJSON);
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/consumer/getSavedTripsPagination/"
		+ params;
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctSetSavedListingPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE,
		5, START_PAGE, URL);
    }
</script>


