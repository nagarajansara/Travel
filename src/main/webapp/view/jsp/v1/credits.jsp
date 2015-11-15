<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctcredits.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Profile Details</h3>
					</div>
					<form onsubmit="return ctProfileDatas();"
						action="${baseURL}/travelapi/vendor/updateProfile" method="post"
						role="form" class="form-horizontal ctVendorProfileListingForm">
					</form>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctcredits.js"></script>
<script>
	
</script>