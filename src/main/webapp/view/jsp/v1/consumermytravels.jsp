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
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">My Travels</h3>
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
													src="${hostName}:${port}/travelimages/compress/${(element.imageurls)}"
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
<script src="${baseURL}/jquery.js/jquery-ui.js"></script>
<script src="${baseURL}/assest/js/ctconsumermytravels.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerTravelsMenu').addClass('active');

    var numEntries = '${model.responseData.numEntries}';
    var MAX_ENTRIES = 5, START_PAGE = 1;

    if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE) {
	var lastPart = bmpUtil.getLastStartingURL(), URL = "http://"
		+ location.host
		+ "/travel/travelapi/consumer/getConsumerTravelsPagination";
	lastPart = parseInt(lastPart);
	if (lastPart && typeof lastPart === 'number') {
	    START_PAGE = lastPart;
	}
	ctSetConsumerPagination(numEntries, ctDAO.TOTAL_RECORDS_PER_PAGE, 5,
		START_PAGE, URL);
    }
</script>