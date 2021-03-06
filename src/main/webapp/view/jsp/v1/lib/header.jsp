<%@page import="travel.com.model.FBConnection"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	FBConnection fbConnection = new FBConnection();
	String domainName = request.getServerName();
%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">

<!-- Mirrored from kodeforest.com/html/travel/index.html by HTTrack Website Copier/3.x [XR&CO'2010], Sun, 24 May 2015 04:03:14 GMT -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta property="og:locale" content="en_US" />
<meta property="og:type" content="website" />
<meta property="og:url"
	content="http://www.ecosenseindia.com/travel/travelapi/trip/getTripDetails" />

<title>Travel</title>

<c:set var="baseURL" value="${pageContext.request.contextPath}" />
<c:set var="req" value="${pageContext.request}" />
<c:set var="hostName" value="http://${pageContext.request.serverName}" />
<c:set var="port" value="${pageContext.request.serverPort}" />
<c:set var="uploadedImageFolderName" value="travelimages/compress" />

<!-- Css Folder -->
<link href="${baseURL}/theme/css/bootstrap.css" rel="stylesheet">
<link href="${baseURL}/theme/css/bootstrap-theme.css" rel="stylesheet">
<link href="${baseURL}/theme/css/font-awesome.css" rel="stylesheet">
<link href="${baseURL}/theme/css/color.css" rel="stylesheet">
<link href="${baseURL}/theme/style.css" rel="stylesheet">
<link href="${baseURL}/theme/css/responsive.css" rel="stylesheet">
<link href="${baseURL}/theme/css/themetypo.css" rel="stylesheet">
<link href="${baseURL}/theme/plugin/datepicker/tsdatepicker.css"
	rel="stylesheet">
<link href="${baseURL}/assest/css/travels.css" rel="stylesheet">
<link href="${baseURL}/assest/css/ctconsumer.css" rel="stylesheet">
<link href="${baseURL}/assest/plugin/alert/sweetalert.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<link href="${baseURL}/assest/img/favicon-16x16.png" rel="shortcut icon"
	type="image/png" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
.ctLoginForm p span {
	color: red;
}
</style>

</head>
<body>
	<header id="mainheader">
		<!--// Top Baar //-->
		<div class="kd-topbar ct_MainHeaderFontStyle">
			<div class="container">
				<div class="row">
					<div class="col-md-5">
						<ul class="kd-topinfo ctHeaderInfo">
							<li>
								<div id="lang_sel">
									<ul>
										<li><i class="fa fa-phone"></i> Phone: 91-9962230256</li>
									</ul>
								</div>
							</li>
							<li><i class="fa fa-envelope-o"></i> dude@horruit.com</li>

						</ul>
					</div>
					<div class="col-md-7">
						<ul class="kd-userinfo">
							<li>
								<div class="kd-social-network">
									<ul>
										<li><a href="#"><i class="fa fa-facebook"></i></a></li>
										<li><a href="#"><i class="fa fa-twitter"></i></a></li>
									</ul>
								</div>
							</li>
							<c:if test="${sessionScope.fName == null}">
								<li><a href="#" data-toggle="modal"
									data-target="#registerModalbox"> Register </a>
									<div class="modal fade kd-loginbox" id="registerModalbox"
										tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-body">
													<a href="#" class="close" data-dismiss="modal"
														aria-label="Close"><span aria-hidden="true">&times;</span></a>
													<div class="kd-login-title">
														<h2>Register TO</h2>
														<span>Your Account</span>
														<div class="kd-login-network">
															<ul>
																<!--<li><a href="#" data-original-title="Facebook"><i class="fa fa-facebook"></i> Login with Facebook</a></li>
                                <li><a href="#" data-original-title="Twitter"><i class="fa fa-twitter"></i> Login with Twitter</a></li>-->
																<li><a class="dropdown-toggle"
																	href="${baseURL}/view/jsp/v1/customerregister.jsp"><i
																		class="fa fa-user icon-white"></i> Customer </a></li>
																<%-- <li><a class="btn btn-primary dropdown-toggle"
																	href="${baseURL}/view/jsp/v1/vendorregister.jsp"><i
																		class="fa fa-user icon-white"></i> Vendor </a></li> --%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div></li>
							</c:if>
							<!-- Modal -->
							<li><c:choose>
									<c:when test="${sessionScope.fName != null}">
										<a href="${baseURL}/travelapi/login/logout" data-toggle=""
											data-target="#">Logout</a>
									</c:when>
									<c:otherwise>
										<a href="#" class="ctOpenModal" data-toggle="modal"
											data-target="#Modalbox">Login</a>
									</c:otherwise>
								</c:choose> <!-- Modal -->
								<div class="modal fade kd-loginbox" id="Modalbox" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-body">
												<a href="#" class="close" data-dismiss="modal"
													aria-label="Close"><span aria-hidden="true">&times;</span></a>
												<div class="kd-login-title">
													<h2 style="font-size: 12px !important;">LOGIN TO</h2>
													<span>Your Account</span>
													<div class="kd-login-network">
														<ul>
															<%-- <li><a
																href="<%=fbConnection.getFBAuthUrl(domainName)%>"
																data-original-title="Facebook"
																style="font-size: 12px !important;"><i
																	class="fa fa-facebook"></i> Login with Facebook</a></li> --%>
															<!--<li><a href="#" data-original-title="Twitter"><i class="fa fa-twitter"></i> Login with Twitter</a></li>-->
														</ul>
													</div>
												</div>
												<div class="kd-login-sepratore">
													<span>OR</span>
												</div>

												<form class="form-horizontal ctLoginForm" method="post"
													action="${baseURL}/travelapi/login/validate"
													name="basic_validate" id="basic_validate"
													novalidate="novalidate">
													<span class="ctErrorSpan"
														style="display: none; color: red; font-family: Junction;">Invalid
														login</span>
													<p>
														<i class="fa fa-envelope-o"></i> <input type="text"
															name="email" id="email" placeholder="Email Address">
													</p>
													<p>
														<i class="fa fa-lock"></i> <input type="password"
															name="password" id="password" placeholder="Your Password">
													</p>
													<p>
														<input type="submit" value="Login now" class="thbg-color"
															style="font-size: 12px !important;"> <a href="#">Forget
															Password?</a>
													</p>
												</form>

											</div>
										</div>
									</div>
								</div></li>
							<c:choose>
								<c:when test="${sessionScope.fName != null}">
									<li><i class="fa fa-user"></i> <a href="#"><c:out
												value='${sessionScope["fName"]}' /></a></li>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${sessionScope.fName != null}">
									<li><i class=""></i> <a
										href="${baseURL}/travelapi/consumer/getSavedTrips"><c:out
												value='Saved Trips' /> </a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${baseURL}/view/jsp/v1/vendorregister.jsp"
										data-toggle="" data-target="#" target="_blank">Are you
											trip provider</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when
									test="${(sessionScope.role != null) && (sessionScope.role eq 'ROLE_VENDOR')}">
									<li><i class=""></i> <a
										href="${baseURL}/travelapi/vendor/listing"> <c:out
												value='Dashboard' /></a></li>
								</c:when>
								<c:otherwise>
									<c:if
										test="${(sessionScope.role != null) && (sessionScope.role eq 'ROLE_CUSTOMER')}">
										<li><a href="${baseURL}/travelapi/consumer/getprofile">Dashboard</a></li>
									</c:if>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--// Header Baar //-->
		<div
			class="kd-headbar ct_MainHeaderFontStyle ct_SubBasckGroundColor ctHeaderCommon">
			<div class="container">
				<div class="row">
					<div class="col-md-3 col-xs-6">
						<a href="${baseURL}/travelapi/trip/getTopActivitys"
							class="logo ctTravelLogo_Padding"><img
							src="${baseURL}/theme/images/logo.png" alt="hourrit"></a>
					</div>
					<div class="col-md-9 col-xs-6">
						<div class="kd-rightside " style="float: none;">
							<nav class="navbar navbar-default navigation">
								<div class="navbar-header">
									<button type="button" class="navbar-toggle collapsed"
										data-toggle="collapse" data-target="#navbar-collapse-1">
										<span class="icon-bar"></span> <span class="icon-bar"></span>
										<span class="icon-bar"></span>
									</button>
								</div>

								<div class="collapse navbar-collapse" id="navbar-collapse-1">
									<ul class="nav navbar-nav ctTravelNavBar-Nav">
										<li>
											<div style="max-width: 200px; min-width: 200px;">
												<input style="width: 100%;" name="startpoint" type="hidden"
													id="ctSelectHeaderCity">
											</div>
										</li>
										<li class="ctHeaderActivity"></li>
										<li><i class="fa fa-search ctListSearchBtn"
											style="font-weight: bold; cursor: pointer; color: #666666;"></i></li>
										<li><a style="line-height: 30px;"
											href="${baseURL}/travelapi/trip/getTopActivitys">Home</a></li>

										<!-- <ul class="sub-dropdown">
												<li><a href="index-map.html">Map HomePage</a></li>
											</ul> -->
										<!-- </li> -->
										<%-- <li><a href="${baseURL}/travelapi/trip/getTripDetails">Listing
												Page</a></li> --%>
										<!--<li><a href="about-us.html">About Us</a></li>
                      <li><a href="#">Blog</a>
                        <ul class="sub-dropdown">
                          <li><a href="blog-grid.html">Blog Grid</a></li>
                          <li><a href="blog-large.html">Blog Large</a></li>
                          <li><a href="blog-medium.html">Blog Medium</a></li>
                          <li><a href="blog-detail.html">Single Post Layout</a>
                            <ul class="sub-dropdown">
                              <li><a href="blog-detail-video.html">Video & Soundcloud</a></li>
                              <li><a href="blog-detail.html">Single Image Layout</a></li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                      <li><a href="package-list.html">Tour Packages</a>
                        <ul class="sub-dropdown">
                          <li><a href="package-list.html">Packages List</a></li>
                          <li><a href="package-detail.html">Package Single Layout</a>
                            <ul class="sub-dropdown">
                              <li><a href="package-video.html">Video & Soundcloud</a></li>
                              <li><a href="package-detail.html">Package Single Image</a></li>
                            </ul>
                          </li>
                        </ul>
                      </li>-->
										<%-- <c:if
											test="${(sessionScope.role != null) && (sessionScope.role eq 'ROLE_VENDOR')}">
											<li><a href="${baseURL}/travelapi/vendor/listing">Vendor</a>
											</li>
										</c:if> --%>
										<%-- <c:if
											test="${(sessionScope.role != null) && (sessionScope.role eq 'ROLE_CUSTOMER')}">
											<li><a href="${baseURL}/travelapi/consumer/getprofile">Customer</a></li>
										</c:if> --%>
										<!--<li><a href="contact-us.html">contact us</a></li>-->
									</ul>
								</div>
								<!-- /.navbar-collapse -->
							</nav>
							<div class="kd-search">
								<!-- <a href="#" class="kd-searchbtn" data-toggle="modal"
									data-target="#searchmodalbox"><i class="fa fa-search"></i></a> -->
								<!-- Modal -->
								<div class="modal fade kd-loginbox" id="searchmodalbox"
									tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-body">
												<a href="#" class="close" data-dismiss="modal"
													aria-label="Close"><span aria-hidden="true">&times;</span></a>
												<div class="kd-login-title">
													<h2>Search Your KeyWord</h2>
												</div>
												<form>
													<p>
														<i class="fa fa-search"></i> <input type="text"
															placeholder="Enter Your Keyword">
													</p>
													<p>
														<input type="submit" value="Search" class="thbg-color">
													</p>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--// Header Baar //-->
	</header>