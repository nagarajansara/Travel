<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
<link rel="stylesheet" href="${baseURL}/assest/css/ctviewlisting.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row">
				<div class="">
					<div class="kd-package-detail">
						<div id="jssor_1" class="detail-thumb"
							style="position: relative; /* margin: 0 auto; */ top: 0px; left: 10px; width: 600px; height: 300px; overflow: hidden; visibility: hidden;">
							<!-- Loading Screen -->
							<div data-u="loading"
								style="position: absolute; top: 0px; left: 0px;">
								<div
									style="filter: alpha(opacity = 70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
								<div
									style="position: absolute; display: block; background: url('${baseURL}/assest/plugin/slider/img/loading.gif') no-repeat center center; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
							</div>
							<div data-u="slides"
								style="cursor: default; position: relative; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden;">
								<c:if test="${not empty model.responseData.tripdetails}">
									<c:forEach items="${model.responseData.tripdetails}"
										var="element" varStatus="loop">
										<c:set var="tripimages"
											value="${fn:split(element.tripimagename, ',')}" />
										<c:forEach var="arrayVar" items="${tripimages}">
											<div data-p="112.50" style="display: none;">
												<img data-u="image"
													src="${hostName}:${port}/travelimages/${arrayVar}" />
											</div>
										</c:forEach>
									</c:forEach>
								</c:if>
							</div>
							<!-- Bullet Navigator -->
							<div data-u="navigator" class="jssorb01"
								style="bottom: 16px; right: 16px;">
								<div data-u="prototype" style="width: 12px; height: 12px;"></div>
							</div>
							<!-- Arrow Navigator -->
							<span data-u="arrowleft" class="jssora05l"
								style="top: 123px; left: 8px; width: 40px; height: 40px;"
								data-autocenter="2"></span> <span data-u="arrowright"
								class="jssora05r"
								style="top: 123px; right: 8px; width: 40px; height: 40px;"
								data-autocenter="2"></span> <a href="http://www.jssor.com"
								style="display: none">Jssor Slider</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="kd-package-detail">
						<c:if test="${not empty model.responseData.tripdetails}">
							<c:forEach items="${model.responseData.tripdetails}"
								var="element" varStatus="loop">
								<div class="kd-pkg-info">
									<ul>
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
										<div class="col-md-3">
											<ul>
												<li><i class="fa fa-eye"></i> <strong>Views:</strong>
													${ element.views}</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul>
												<li><i class="fa fa-heart"></i> <strong>Favourite:</strong>
													${ element.favourites}</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul>
												<li><i class="fa fa-comment"></i> <strong>Reviews:</strong>
													${ element.reviews}</li>
											</ul>
										</div>
									</div>
									<a class="kd-booking-btn thbg-color ctBookingNow"
										href="javascript:void(0)">enquiry nOW</a>
								</div>
								<blockquote>${ element.guidelines }</blockquote>
								<div class="kd-rich-editor">
									<div class="kd-imageframe">
										<div class="row">
											<div class="col-md-7">
												<h2>Description</h2>
												<p>${ element.description}</p>
											</div>
										</div>
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
								<p class="kd-button">
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
<script src="${baseURL}/assest/plugin/slider/jssor.slider.mini.js"></script>
<script src="${baseURL}/assest/js/ctviewlisting.js"></script>
<script>
    $(document).ready(function() {
	var jssor_1_SlideshowTransitions = [ {
	    $Duration : 1200,
	    x : 0.2,
	    y : -0.1,
	    $Delay : 20,
	    $Cols : 8,
	    $Rows : 4,
	    $Clip : 15,
	    $During : {
		$Left : [ 0.3, 0.7 ],
		$Top : [ 0.3, 0.7 ]
	    },
	    $Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
	    $Assembly : 260,
	    $Easing : {
		$Left : $Jease$.$InWave,
		$Top : $Jease$.$InWave,
		$Clip : $Jease$.$OutQuad
	    },
	    $Outside : true,
	    $Round : {
		$Left : 1.3,
		$Top : 2.5
	    }
	}, {
	    $Duration : 1500,
	    x : 0.3,
	    y : -0.3,
	    $Delay : 20,
	    $Cols : 8,
	    $Rows : 4,
	    $Clip : 15,
	    $During : {
		$Left : [ 0.1, 0.9 ],
		$Top : [ 0.1, 0.9 ]
	    },
	    $SlideOut : true,
	    $Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
	    $Assembly : 260,
	    $Easing : {
		$Left : $Jease$.$InJump,
		$Top : $Jease$.$InJump,
		$Clip : $Jease$.$OutQuad
	    },
	    $Outside : true,
	    $Round : {
		$Left : 0.8,
		$Top : 2.5
	    }
	}, {
	    $Duration : 1500,
	    x : 0.2,
	    y : -0.1,
	    $Delay : 20,
	    $Cols : 8,
	    $Rows : 4,
	    $Clip : 15,
	    $During : {
		$Left : [ 0.3, 0.7 ],
		$Top : [ 0.3, 0.7 ]
	    },
	    $Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
	    $Assembly : 260,
	    $Easing : {
		$Left : $Jease$.$InWave,
		$Top : $Jease$.$InWave,
		$Clip : $Jease$.$OutQuad
	    },
	    $Outside : true,
	    $Round : {
		$Left : 0.8,
		$Top : 2.5
	    }
	}, {
	    $Duration : 1500,
	    x : 0.3,
	    y : -0.3,
	    $Delay : 80,
	    $Cols : 8,
	    $Rows : 4,
	    $Clip : 15,
	    $During : {
		$Left : [ 0.3, 0.7 ],
		$Top : [ 0.3, 0.7 ]
	    },
	    $Easing : {
		$Left : $Jease$.$InJump,
		$Top : $Jease$.$InJump,
		$Clip : $Jease$.$OutQuad
	    },
	    $Outside : true,
	    $Round : {
		$Left : 0.8,
		$Top : 2.5
	    }
	}, {
	    $Duration : 1800,
	    x : 1,
	    y : 0.2,
	    $Delay : 30,
	    $Cols : 10,
	    $Rows : 5,
	    $Clip : 15,
	    $During : {
		$Left : [ 0.3, 0.7 ],
		$Top : [ 0.3, 0.7 ]
	    },
	    $SlideOut : true,
	    $Reverse : true,
	    $Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
	    $Assembly : 2050,
	    $Easing : {
		$Left : $Jease$.$InOutSine,
		$Top : $Jease$.$OutWave,
		$Clip : $Jease$.$InOutQuad
	    },
	    $Outside : true,
	    $Round : {
		$Top : 1.3
	    }
	}, {
	    $Duration : 1000,
	    $Delay : 30,
	    $Cols : 8,
	    $Rows : 4,
	    $Clip : 15,
	    $SlideOut : true,
	    $Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
	    $Assembly : 2049,
	    $Easing : $Jease$.$OutQuad
	}, {
	    $Duration : 1000,
	    $Delay : 80,
	    $Cols : 8,
	    $Rows : 4,
	    $Clip : 15,
	    $SlideOut : true,
	    $Easing : $Jease$.$OutQuad
	}, {
	    $Duration : 1000,
	    y : -1,
	    $Cols : 12,
	    $Formation : $JssorSlideshowFormations$.$FormationStraight,
	    $ChessMode : {
		$Column : 12
	    }
	}, {
	    $Duration : 1000,
	    x : -0.2,
	    $Delay : 40,
	    $Cols : 12,
	    $SlideOut : true,
	    $Formation : $JssorSlideshowFormations$.$FormationStraight,
	    $Assembly : 260,
	    $Easing : {
		$Left : $Jease$.$InOutExpo,
		$Opacity : $Jease$.$InOutQuad
	    },
	    $Opacity : 2,
	    $Outside : true,
	    $Round : {
		$Top : 0.5
	    }
	}, {
	    $Duration : 2000,
	    y : -1,
	    $Delay : 60,
	    $Cols : 15,
	    $SlideOut : true,
	    $Formation : $JssorSlideshowFormations$.$FormationStraight,
	    $Easing : $Jease$.$OutJump,
	    $Round : {
		$Top : 1.5
	    }
	} ];

	var jssor_1_options = {
	    $AutoPlay : true,
	    $SlideshowOptions : {
		$Class : $JssorSlideshowRunner$,
		$Transitions : jssor_1_SlideshowTransitions,
		$TransitionsOrder : 1
	    },
	    $ArrowNavigatorOptions : {
		$Class : $JssorArrowNavigator$
	    },
	    $BulletNavigatorOptions : {
		$Class : $JssorBulletNavigator$
	    }
	};

	var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

	//responsive code begin
	//you can remove responsive code if you don't want the slider scales while window resizes
	function ScaleSlider() {
	    var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
	    if (refSize) {
		refSize = Math.min(refSize, 600);
		jssor_1_slider.$ScaleWidth(refSize);
	    } else {
		window.setTimeout(ScaleSlider, 30);
	    }
	}
	ScaleSlider();
	$(window).bind("load", ScaleSlider);
	$(window).bind("resize", ScaleSlider);
	$(window).bind("orientationchange", ScaleSlider);

	//responsive code end
	$('.ctPhoneNo').numeric();
	$(".ctBookingNow").click(function() {
	    $("#myModal").modal('show');
	});

	ctInitViewListing();

    });
</script>
