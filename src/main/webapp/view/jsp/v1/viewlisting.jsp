<%@ page language="java" contentType="text/html"%>
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
										<c:set var="tripimages"
											value="${fn:split(element.tripimagename, ',')}" />
										<c:forEach var="arrayVar" items="${tripimages}">
											<li><img
												src="${hostName}:${port}/${ uploadedImageFolderName }/${arrayVar}"
												width="557px;" height="418px;" /></li>
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
							<div class="kd-pkg-info">
								<ul class="ctKdpkgInfoList">
									<li><i class="fa fa-map-marker"></i> <strong>Duration:</strong>
										${element.dateformat} - ${element.todateformat}</li>
									<li><i class="fa fa-paper-plane"></i> <strong>LOCATION:</strong>
										${element.tocity}</li>
									<li><i class="fa fa-ticket"></i> <strong>Route :</strong>
										${element.route }</li>
									<li><i class="fa fa-tag"></i> <strong>Price:</strong>
										${element.price}</li>
								</ul>
								<div class="row" style="clear: both;">
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
								<a class="kd-booking-btn thbg-color ctBookingNow"
									href="#" data-toggle="modal" data-target="#myModal">enquiry nOW</a>
							</div>
							<div class="kd-pkg-info">
								<div class="row">
									<div class="col-md-6 col-sm-6">
										<form class="form-horizontal">
											<div class="form-group">
												<label class="col-lg-3 col-md-3 col-sm-3 control-label">
													<img class="ctVendorImage" src="${baseURL}/assest/img/vendor_image.png">
												</label>
												<div class="col-lg-4 col-md-4 col-sm-4">
													<div id="ctRatedStar"></div>
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
							<c:forEach items="${model.responseData.tripdetails}"
								var="element" varStatus="loop">
								<c:set var="ratedStart" value="${element.startrating}"></c:set>
								<blockquote>
									<span class="ctDefaultHeaders">${ element.title }</span><br>
									<span
										style="text-transform: uppercase; font-size: 14px !important;">${ element.guidelines }</span>
								</blockquote>
								<div class="kd-rich-editor">
									<div class="kd-imageframe">
										<div class="row"></div>
									</div>
									<c:set var="tripdaysdesc"
										value="${fn:split(element.daysdesc, ',')}" />
									<c:forEach items="${tripdaysdesc}" var="tripdaysdescElement"
										varStatus="loopElement">
										<h1>
											DAY
											<c:out value="${ loopElement.index + 1}"></c:out>
										</h1>
										<p>
											<c:out value="${tripdaysdescElement }"></c:out>
										</p>
									</c:forEach>
								</div>
							</c:forEach>
						</c:if>
						<!--// Comments //-->
						<div id="kdcomments">
							<c:if test="${not empty model.responseData.reviewsdetails}">
								<h2>${model.responseData.reviewsNumEntries}Comments</h2>
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
									<input class="ctViewListingName ctIsChkEmptyVal" type="text"
										placeholder="Name *">
								</p>
								<p class="kd-textarea">
									<textarea class="ctViewListingComment ctIsChkEmptyVal"
										placeholder="add your comment *"></textarea>
								</p>
								<br>
								<p class="ctFontWeight_B">Start Rating</p>
								<div id="star"></div>
								<br>
								<p class="kd-button" style="margin-top: 10px !important;">
									<input type="button" value="Submit comment "
										class="thbg-color ctSubmitCommentBtn"> <span
										class="ctSuccessComment"
										style="display: none; font-family: verdana; color: green">&nbsp;&nbsp;Successfully
										commented</span>
								</p>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bs-example">
			<!-- Button HTML (to Trigger Modal) -->


			<!-- Modal HTML -->
			<div id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Enquire Confirmation</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-8">
									<form class="ctEnquiryNow" action="" method="POST">
										<div class="form-group">
											<div class="col-lg-12 col-md-12 col-sm-12">
												<input type="text"
													class="form-control ctName ctIsChkEmptyEnquiryVal"
													name="name" value="" placeholder="Name *">
											</div>
										</div>
										<div class="form-group">
											<!-- <label class="col-lg-3 col-md-3 col-sm-3 control-label"></label> -->
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
												<!-- <input type="text" class="form-control ctNoCreditsTxt"
													name="name" value="" placeholder="Name *"> -->
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
							<button type="button" class="btn btn-primary ctSUbmitEnquireForm">Save
								changes</button>
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
<script src="${baseURL}/assest/plugin/startrating/jquery.raty.js"></script>
<script src="${baseURL}/assest/js/ctviewlisting.js"></script>
<script type="text/javascript">
    $(document).ready(
	    function() {
		var ctRatedStar = '${ratedStart}';
		$('div#star').raty(
			{
			    "path" : "http://" + location.host + "/"
				    + ctDAO.CONTEXT_NAME
				    + "/assest/plugin/startrating/img/"
			});
		$('#ctRatedStar').raty(
			{
			    start : ctRatedStar,
			    "path" : "http://" + location.host + "/"
				    + ctDAO.CONTEXT_NAME
				    + "/assest/plugin/startrating/img/"
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
