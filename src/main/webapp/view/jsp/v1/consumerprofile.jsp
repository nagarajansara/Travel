<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsdatepicker.css"
	media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctprofile.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendor.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctConsumerContainer">
			<div class="row">
				<%@ include file="lib/c_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Profile Details</h3>
					</div>
					<c:if test="${not empty model.responseData.userDetails}">
						<c:forEach items="${model.responseData.userDetails}" var="element"
							varStatus="loop">
							<c:set var="fname" value="${element.firstname}" />
							<c:set var="lname" value="${element.lastname}" />
							<c:set var="address" value="${element.address}" />
							<c:set var="mobile" value="${element.mobile}" />
							<c:set var="pincode" value="${element.pincode}" />
							<c:set var="state" value="${element.state}" />
							<c:set var="organizationname" value="${element.organizationname}" />
							<c:set var="city" value="${element.city}" />
							<c:set var="pancardno" value="${element.pancardno}" />
						</c:forEach>
						<form onsubmit="return ctConsumerProfileDatas();"
							action="${baseURL}/travelapi/consumer/updateConsumerProfile"
							method="post" role="form"
							class="form-horizontal ctVendorProfileListingForm">
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">First
									Name </label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<input disabled type="text"
										class="ctFirstNameTxt  form-control ctIsChkEmptyVal"
										name="fname" value="${fname}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Last
									Name</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<input disabled type="text"
										class="ctLastNameTxt  form-control ctIsChkEmptyVal"
										name="lname" value="${lname}">
								</div>
							</div>
							<%-- <div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Address
								</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<textarea disabled
										class="ctIsChkEmptyVal form-control ctAddressArea" rows="3"
										placeholder="" name="addressTxt">${address}</textarea>
								</div>
							</div> --%>
							<%-- <div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Phone
									No </label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<input name="phoneTxt" disabled type="text"
										class="ctPhoneNoTxt form-control ctIsChkEmptyVal"
										value="${mobile}">
								</div>
							</div> --%>
							<%-- <div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Pin
									Code </label>
								<div class="col-lg-4 col-md-4 col-sm-4 ctRouteDiv">
									<input name="pincode" disabled type="text"
										class="ctPinCodeTxt form-control ctIsChkEmptyVal" name=""
										value="${pincode}">
								</div>
							</div> --%>
							<%-- <div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">State
								</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<input name="state" disabled type="text"
										class="ctStateTxt form-control ctIsChkEmptyVal" name=""
										value="${state}">
								</div>
							</div> --%>
							<%-- <div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Organization
									Name </label>
								<div class="col-lg-4 col-md-4 col-sm-4 ctRouteDiv">
									<input name="organization" disabled type="text"
										class="ctOrganizationTxt form-control ctIsChkEmptyVal" name=""
										value="${organizationname}">
								</div>
							</div> --%>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">City
								</label>
								<div class="col-lg-4 col-md-4 col-sm-4 ctRouteDiv">
									<input name="city" disabled type="text"
										class="form-control ctIsChkEmptyVal" name="" value="${city}">
								</div>
							</div>
							<%-- <div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">panCard
									No </label>
								<div class="col-lg-4 col-md-4 col-sm-4 ctRouteDiv">
									<input name="pancardno" disabled type="text"
										class="form-control ctIsChkEmptyVal" name=""
										value="${pancardno}">
								</div>
							</div> --%>
							<div class="form-group ctProfileEditBtnParent">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label"></label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<button type="button" class="btn btn-primary ctProfileEditBtn">Edit</button>
								</div>
							</div>
							<div class="form-group ctProfileUpdateBtnParent"
								style="display: none;">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label"></label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<button type="submit"
										class="btn btn-primary ctProfileUpdateBtn">Update</button>
								</div>
							</div>
						</form>
					</c:if>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctconsumerprofile.js"></script>
<script type="text/javascript">
    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerProfileMenu').addClass('active');

    $('.ctPhoneNoTxt').numeric();
    $('.ctPinCodeTxt').numeric();
</script>
