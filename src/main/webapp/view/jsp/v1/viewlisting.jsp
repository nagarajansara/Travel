<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="/WEB-INF/tld/customTagLibrary" prefix="util"%>
<%@ include file="lib/header.jsp"%>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/slider/flexslider.css" media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctviewlisting.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/startrating/raty.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row">
				<div class="col-md-6 col-sm-6 ctViewListingKD-package-detail">
					<div class="kd-package-detail">
						<!-- Place somewhere in the <body> of your page -->
						<c:if test="${not empty model.responseData.tripdetails}">
							<div id="slider"
								class="flexslider ctflexslider ctOriginalImgFlex">
								<ul class="slides">
									<c:forEach items="${model.responseData.tripdetails}"
										var="element" varStatus="loop">
										<c:set var="TRIPID" value="${element.tripid }"></c:set>
										<c:set var="ratedStart" value="${element.startrating }"></c:set>
										<c:set var="tripimages"
											value="${fn:split(element.tripimagename, ',')}" />
										<c:forEach var="arrayVar" items="${tripimages}">
											<li><img
												src="${hostName}:${port}/${ uploadedImageFolderName }/${arrayVar}"
												width="100px" height="100px;" /></li>
										</c:forEach>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<c:if test="${not empty model.responseData.tripdetails}">
							<div id="carousel" class="flexslider ctflexslider ctThumbImgFlex">
								<ul class="slides">
									<c:forEach items="${model.responseData.tripdetails}"
										var="element" varStatus="loop">
										<c:set var="tripimages"
											value="${fn:split(element.tripimagename, ',')}" />
										<c:forEach var="arrayVar" items="${tripimages}">
											<li><img
												src="${hostName}:${port}/${ uploadedImageFolderName }/${arrayVar}"
												height="80px !important;" width="120px;" /></li>
										</c:forEach>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<c:if test="${not empty model.responseData.tripdetails}">
						<c:forEach items="${model.responseData.tripdetails}" var="element"
							varStatus="loop">
							<c:set var="tourProvidedEmail" value="${element.email }"
								scope="request" />
							<c:set var="tourProvidedName" value="${element.firstname }"
								scope="request" />
							<c:set var="tourProvidedUserId" value="${element.userid }"
								scope="request" />
							<div class="kd-pkg-info ctNoborder">
								<ul class="ctKdpkgInfoList ctKdpkgInfoListIconColor">
									<li><i class="fa fa-map-marker"></i> <strong>Duration:</strong>
										${element.dateformat} - ${element.todateformat}</li>
									<li><i class="fa fa-paper-plane"></i> <strong>LOCATION:</strong>
										${element.tocity}</li>
									<li><i class="fa fa-ticket"></i> <strong>Route :</strong>
										${element.route }</li>
									<li><c:choose>
											<c:when test="${element.offer_percentage eq element.price}">
												<i class="fa fa-tag"></i>
												<strong>Price:</strong>${element.offer_percentage}
									</c:when>
											<c:otherwise>
												<i class="fa fa-tag"></i>
												<strong>Price:</strong>
												<span style="text-decoration: line-through;">${element.price}</span>
												${element.offer_percentage}
											</c:otherwise>
										</c:choose></li>
								</ul>
								<div class="row ctKdpkgInfoListIconColor" style="clear: both;">
									<div class="col-md-4 col-sm-4">
										<ul>
											<li><i class="fa fa-eye"></i> <strong>Views:</strong> ${ element.views}</li>
										</ul>
									</div>
									<div class="col-md-4 col-sm-4">
										<ul>
											<li><i class="fa fa-heart"></i> <strong>Favourite:</strong>
												${ element.favourites}</li>
										</ul>
									</div>
									<div class="col-md-4 col-sm-4">
										<ul>
											<li><i class="fa fa-comment"></i> <strong>Reviews:</strong>
												${ element.reviews}</li>
										</ul>
									</div>
								</div>
								<!-- <a class="kd-booking-btn thbg-color ctBookingNow" href="#"
									data-toggle="modal" data-target="#myModal">enquiry nOW</a> -->
							</div>
							<div class="kd-pkg-info ctNoborder">
								<div class="row">
									<div class="col-md-12 col-sm-12">
										<form class="form-horizontal">
											<div class="form-group">
												<label
													class="col-lg-4 col-md-4 col-sm-4 control-label ctViewListingFont_B">
													TOUR PROVIDED BY </label>
											</div>
											<div class="form-group">
												<label class="col-lg-2 col-md-2 col-sm-2 control-label">
													<img class="ctVendorImage"
													src="${baseURL}/assest/img/vendor_image.png">
												</label>
												<div class="col-lg-6 col-md-6 col-sm-6">
													<div id="ctRatedStar"></div>
												</div>
											</div>
											<div class="form-group">
												<label
													class="col-lg-2 col-md-2 col-sm-2 control-label ctViewListingFont_B">
													Name :</label>
												<div class="col-lg-6 col-md-6 col-sm-6">
													${tourProvidedName}</div>
											</div>
											<div class="form-group">
												<label
													class="col-lg-2 col-md-2 col-sm-2 control-label ctViewListingFont_B">
													Email :</label>
												<div class="col-lg-6 col-md-6 col-sm-6 ctTourVendorEmail">
													<a
														href="${baseURL}/travelapi/vendor/getVendorDetailsBasedId/${tourProvidedUserId}"
														target="_balnk">${tourProvidedEmail}</a>
												</div>
											</div>
											<div class="form-group">
												<!-- <label class="col-lg-4 col-md-4 col-sm-4 control-label">
												</label> -->
												<div
													class="col-lg-12 col-md-12 col-sm-12 ctViewListingFont_B">
													<a class="kd-booking-btn thbg-color ctBookingNow" href="#"
														data-toggle="modal" data-target="#myModal">enquiry nOW</a>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col-md-8">
					<div class="kd-package-detail">
						<c:if test="${not empty model.responseData.tripdetails}">
							<div class="kd-tab kd-horizontal-tab ctViewListingMenu">
								<!-- Nav tabs -->
								<ul class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#home"
										aria-controls="home" role="tab" data-toggle="tab">Title</a></li>
									<li role="presentation"><a href="#profile"
										aria-controls="profile" role="tab" data-toggle="tab">Guidelines</a></li>
									<li role="presentation"><a href="#messages"
										aria-controls="messages" role="tab" data-toggle="tab">Itenary</a></li>
									<li role="presentation"><a href="#dates"
										aria-controls=dates role="tab" data-toggle="tab">Dates</a></li>
									<li class="pull-right ctSavedListTrip"><c:choose>
											<c:when test="${(sessionScope.role != null)}">
												<a class="ctSavedTripList"
													PK_tripid=${util:getBase64Encode(TRIPID) }>Saved Trip
													List</a>
												<img style="display: none;" class="ctLoadingSavedTrip"
													src="${baseURL}/assest/img/loading.gif">
											</c:when>
											<c:otherwise>
												<a class="ctSavedTripList" style="cursor: progress"
													title="Please login and continue">Saved Trip List</a>
											</c:otherwise>
										</c:choose>
								</ul>
								<c:remove var="TRIPID" />
								<c:forEach items="${model.responseData.tripdetails}"
									var="element" varStatus="loop">
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="home">
											<div class="kd-dropcap">
												<p class="ctDefaultHeaders">${ element.title }</p>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane" id="profile">
											<div class="kd-dropcap">
												<p class="ctDefaultHeaders">${ element.guidelines }</p>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane" id="dates">
											<div class="">
												<p class="ctDefaultHeaders">${element.dateformat}-
													${element.todateformat}</p>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane" id="messages">
											<div class="">
												<div class="kd-rich-editor">
													<c:set var="tripdaysdesc"
														value="${fn:split(element.daysdesc, ',')}" />
													<c:forEach items="${tripdaysdesc}"
														var="tripdaysdescElement" varStatus="loopElement">
														<h1>
															DAY
															<c:out value="${ loopElement.index + 1}"></c:out>
														</h1>
														<p>
															<c:out value="${tripdaysdescElement }"></c:out>
														</p>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:if>
						<!--// Comments //-->
						<div id="kdcomments">
							<c:if test="${not empty model.responseData.reviewsdetails}">
								<h2>Comments (${model.responseData.reviewsNumEntries})</h2>
								<ul class="ctReviewsDetailsUL">
									<c:forEach items="${model.responseData.reviewsdetails}"
										var="element" varStatus="loop">
										<li>
											<div class="thumblist">
												<ul>
													<li>
														<figure>
															<a href="#"><img alt=""
																src="${baseURL}/theme/extraimages/comment1.jpg"></a>
														</figure>
														<div class="text">
															<a href="#">${element.username}</a>
															<time datetime="2008-02-14 20:00">
																<i class="fa fa-calendar"></i>
																${element.createdateformat}
															</time>
															<p>${element.comment}</p>
															<!-- <a class="replay-btn thbg-colorhover" href="#">Reply</a> -->
														</div>
													</li>
												</ul>
											</div>
										</li>
									</c:forEach>
								</ul>
							</c:if>
							<div class="row">
								<div class="col-md-12">
									<div align="center">
										<c:if test="${not empty model.responseData.reviewsNumEntries}">
											<c:if test="${model.responseData.reviewsNumEntries gt 10}">
												<div id="load-more" class="ctViewLisitingLoadMore">Load
													More</div>
												<div class="ctLoadingImage ctDefaultDisplayNone">
													<img src="${baseURL}/assest/img/loading.gif">
												</div>
												<input id="ctViewListingNumEntries" type="hidden"
													value="${model.responseData.reviewsCurrentPage }">
												<input id="ctCommentsNumEntries" type="hidden"
													value="${model.responseData.reviewsNumEntries }">
											</c:if>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<!--// Comments //-->
						<div id="respond">
							<br>
							<h2>Comments</h2>
							<form>
								<p>
									<input class="ctViewListingEmail ctIsChkEmptyVal" type="text"
										placeholder="Email *">
								</p>
								<p>
									<input class="ctViewListingName ctIsChkEmptyVal" type="text"
										placeholder="Name *">
								</p>
								<p class="kd-textarea">
									<textarea class="ctViewListingComment ctIsChkEmptyVal"
										placeholder="add your comment *"></textarea>
								</p>
								<br>
								<!-- <p class="ctFontWeight_B">Start Rating</p>
								<div id="star"></div> -->
								<br>
								<p class="kd-button" style="margin-top: 10px !important;">
									<img class="ctTravelLoader"
										src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
									<input type="button" value="Submit comment "
										class="thbg-color ctSubmitCommentBtn"> <span
										class="ctSuccessComment"
										style="display: none; font-family: Junction; color: green">&nbsp;&nbsp;Successfully
										commented</span>
								</p>
							</form>
						</div>
					</div>
				</div>
				<aside class="col-md-4">
					<div class="widget widget-blogpost ctViewListingWigetBlogPost">
						<div class="kd-widget-title">
							<h2>Related Trips</h2>
						</div>
						<ul>
							<li>
								<figure>
									<a href="#"><img
										src="http://localhost:8082/travelimages/compress/23038picture0006.jpg"
										alt=""></a>
								</figure>
								<div class="kd-post-info ">
									<h6>
										<a href="#">Watch this Chinese airport dummy...</a>
									</h6>
									<time datetime="2008-02-14 20:00">January 15, 2015</time>
								</div>
							</li>
							<li>
								<figure>
									<a href="#"><img
										src="http://localhost:8082/travelimages/compress/23038picture0006.jpg"
										alt=""></a>
								</figure>
								<div class="kd-post-info">
									<h6>
										<a href="#">Watch this Chinese airport dummy...</a>
									</h6>
									<time datetime="2008-02-14 20:00">January 15, 2015</time>
								</div>
							</li>
							<li>
								<figure>
									<a href="#"><img
										src="http://localhost:8082/travelimages/compress/2469611855641_455679057938263_6088867645054996865_n.jpg"
										alt=""></a>
								</figure>
								<div class="kd-post-info">
									<h6>
										<a href="#">Watch this Chinese airport dummy...</a>
									</h6>
									<time datetime="2008-02-14 20:00">January 15, 2015</time>
								</div>
							</li>
						</ul>
					</div>
				</aside>
			</div>
		</div>
		<div class="bs-example">
			<!-- Button HTML (to Trigger Modal) -->
			<!-- Modal HTML -->
			<div id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close ctViewListingCloseBtn"
								data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Enquire Confirmation</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-8">
									<form class="ctEnquiryNow" action="" method="POST">
										<div class="form-group">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<label style="color: red; display: none"
													class="col-lg-12 col-md-12 col-sm-12 control-label ctEnquiryStatus"></label>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<input type="text"
													class="form-control ctName ctIsChkEmptyEnquiryVal"
													name="name" value="" placeholder="Name *">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<input type="text"
													class="form-control ctEnquiryEmail ctIsChkEmptyEnquiryVal"
													name="emailId" value="" placeholder="Email Id *">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<input type="text"
													class="form-control ctPhoneNo ctIsChkEmptyEnquiryVal"
													name="phoneNo" value="" placeholder="Phone No *">
											</div>
										</div>
										<div class="form-group ctNoCreditsDiv ctDefaultDisplayNone">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<span class="ctNoCreditsTxt ctDangerDefaultTxt"></span>
											</div>
										</div>
										<div class="form-group">
											<div class="ctAddCommentLoadingImage ctDefaultDisplayNone">
												<img src="${baseURL}/assest/img/loading.gif">
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default ctEnquiryModalClose"
								data-dismiss="modal">Close</button>
							<button type="button" PK_tripid=${util:getBase64Encode(TRIPID) }
								class="btn btn-primary ctSUbmitEnquireForm">Send</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/plugin/slider/jquery.flexslider.js"></script>
<script src="${baseURL}/assest/plugin/startrating/jquery.raty-fa.js"></script>
<script src="${baseURL}/assest/js/ctviewlisting.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
	var ctRatedStar = '${ratedStart}';
	console.log("ctRatedStar :" + ctRatedStar);
	$('.ctPhoneNo').numeric();
	$('#ctRatedStar').raty({
	    score : ctRatedStar,
	    readOnly : true,
	    halfShow : true

	});
	// The slider being synced must be initialized first
	$('#carousel').flexslider({
	    animation : "slide",
	    controlNav : false,
	    animationLoop : false,
	    slideshow : false,
	    itemWidth : 110,
	    itemMargin : 5,
	    asNavFor : '#slider'
	});

	$('#slider').flexslider({
	    animation : "slide",
	    controlNav : false,
	    animationLoop : false,
	    slideshow : false,
	    sync : "#carousel"
	});

	ctInitViewListing();
    });
</script>
