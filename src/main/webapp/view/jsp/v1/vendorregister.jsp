<%@ page language="java" contentType="text/html"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Travel Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/travel/theme/dashboard/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/travel/theme/dashboard/css/font-awesome.css" />
<link rel="stylesheet"
	href="/travel/theme/dashboard/css/icheck/flat/blue.css" />
<link rel="stylesheet" href="/travel/theme/dashboard/css/select2.css" />
<link rel="stylesheet" href="/travel/theme/dashboard/css/unicorn.css" />




<!--[if lt IE 9]>
		<script type="text/javascript" src="js/respond.min.js"></script>
		<![endif]-->

<style>
.ctWrapper {
	background-color: none !important;
}

.ctWrapper {
	/*margin-top: 25px !important;*/
	margin-left: 0px !important;
}

.ctWrapper {
	font-family: verdana;
	font-size: 12px;
}

body {
	background: none !important;
}

.ctRegCitySelect {
	height: 35px;
}

.ctOpenLoginRow {
	margin-top: 16px;
}

.ctHeaderTag {
	background-color: rgb(8, 125, 194) !important;
}
</style>

</head>
<body data-color="white" class="flat">
	<div id="wrapper">
		<div id="header" class="ctHeaderTag">
			<h1>
				<a href="index.html">Unicorn Admin</a>
			</h1>
			<!--<a href="#" id="menu-trigger"><i class="fa fa-bars"></i></a>-->
		</div>
		<div id="content" class="ctWrapper">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="row ctOpenLoginRow">
						<!--<div class="col-xs-12 col-sm-6">
								<a href="">
									<input type="button" value="Login with Facebook" class="btn btn-primary btn-block">
								</a>	
							</div>
							<div class="col-xs-12 col-sm-6">
								<input type="button" value="Login with Twitter" class="btn btn-danger btn-block">
							</div>-->
					</div>
					</br> </br>
					<c:if test="${not empty model.responseStatus}">
						<c:if test="${model.responseStatus ne 200}">
							<c:set var="respomseMsg" value="${(model.responseMsg)}" />
							<div class="row">
								<div class="col-xs-12">
									<c:choose>
										<c:when test="${fn:contains(respomseMsg, 'Duplicate entry')}">
											<div class="alert alert-danger">
												<button data-dismiss="alert" class="close">×</button>
												<strong></strong>User already exits
											</div>
										</c:when>
										<c:otherwise>
											<div class="alert alert-danger">
												<button data-dismiss="alert" class="close">×</button>
												<strong></strong>
												<c:out value="${(model.responseMsg)}" />
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:if>
						<c:if test="${model.responseStatus eq 200}">
							<c:redirect url="/view/jsp/v1/home.jsp" />
						</c:if>
					</c:if>
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="fa fa-align-justify"></i>
							</span>
							<h5>Vendor Registration</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" method="post"
								action="/travel/travelapi/login/vendorregister"
								name="basic_validate" id="basic_validate"
								novalidate="novalidate">
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Name
										of organization</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="nameoforganization" id="nameoforganization">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">First
										Name</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="firstName" id="firstName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Last
										Name</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="lastName" id="lastName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Email</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm" name="email"
											id="email">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Password</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="password" class="form-control input-sm"
											name="password" id="password">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">State</label>
									<div class="col-sm-9 col-md-9 col-lg-9">
										<select name="stateName">
											<option value="Rajasthan">Rajasthan</option>

											<option value="Madhya Pradesh">Madhya Pradesh</option>

											<option value="Maharashtra">Maharashtra</option>

											<option value="Uttar Pradesh">Uttar Pradesh</option>

											<option value="Jammu and Kashmir">Jammu and Kashmir</option>

											<option value="Gujarat">Gujarat</option>

											<option value="Karnataka">Karnataka</option>

											<option value="Andhra Pradesh">Andhra Pradesh</option>

											<option value="Odisha">Odisha</option>

											<option value="Chhattisgarh">Chhattisgarh</option>

											<option value="Tamil Nadu">Tamil Nadu</option>

											<option value="Telangana">Telangana</option>

											<option value="Bihar">Bihar</option>

											<option value="West Bengal">West Bengal</option>

											<option value="Arunachal Pradesh">Arunachal Pradesh</option>

											<option value="Jharkhand">Jharkhand</option>

											<option value="Assam">Assam</option>

											<option value="Himachal Pradesh">Himachal Pradesh</option>

											<option value="Uttarakhand">Uttarakhand</option>

											<option value="Punjab">Punjab</option>

											<option value="Haryana">Haryana</option>

											<option value="Kerala">Kerala</option>

											<option value="Meghalaya">Meghalaya</option>

											<option value="Manipur">Manipur</option>

											<option value="Mizoram">Mizoram</option>

											<option value="Nagaland">Nagaland</option>

											<option value="Tripura">Tripura</option>

											<option value="Sikkim">Sikkim</option>

											<option value="Goa">Goa</option>

											<option value="Delhi">Delhi</option>

											<option value="Puducherry">Puducherry</option>

											<option value="Southern">Southern</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Address</label>
									<div class="col-sm-9 col-md-9 col-lg-6">
										<textarea name="ctAddress" id="ctAddress" class="form-control"
											rows="2"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Mobile</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm" name="mobile"
											id="mobile" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Contact</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="contact" id="contact" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">PAN
										Card</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="pancard" id="pancard" />
									</div>
								</div>
								<div class="form-actions">
									<input type="submit" value="Register" class="btn btn-info">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="/travel/theme/dashboard/js/jquery.min.js"></script>
	<script src="/travel/theme/dashboard/js/jquery-ui.custom.js"></script>
	<script src="/travel/theme/dashboard/js/bootstrap.min.js"></script>
	<script src="/travel/theme/dashboard/js/jquery.icheck.min.js"></script>
	<script src="/travel/theme/dashboard/js/select2.min.js"></script>
	<script src="/travel/theme/dashboard/js/jquery.validate.js"></script>

	<script src="/travel/theme/dashboard/js/jquery.nicescroll.min.js"></script>
	<script src="/travel/theme/dashboard/js/unicorn.js"></script>
	<script src="/travel/theme/dashboard/js/unicorn.form_validation.js"></script>
	<script src="/travel/assest/plugin/numeric/numeric.min.js"></script>
</body>
<script type="text/javascript">
    $('select').select2();
    $('#mobile').numeric();
    $('#contact').numeric();
</script>