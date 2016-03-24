<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsdatepicker.css"
	media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctprofile.css"
	media="screen" />

<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctConsumerContainer">
			<div class="row">
				<c:choose>
					<c:when
						test="${(sessionScope.role != null) && (sessionScope.role eq 'ROLE_VENDOR')}">
						<%@ include file="lib/v_leftsidebar.jsp"%>
					</c:when>
					<c:otherwise>
						<%@ include file="lib/c_leftsidebar.jsp"%>
					</c:otherwise>
				</c:choose>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Contact Us</h3>
						&nbsp; <a href="#" data-toggle="tooltip" data-placement="right"
							title=""
							data-original-title="We are always available to help! You can reach us out at"
							class="red-tooltip ctContactUsIcon"><i
							class="fa fa-info-circle"></i></a>
					</div>
					<div class="widget kd-userinfo-widget">
						<ul>
							<li><i class="fa fa-map-marker"></i> Gandhipuram, Coimbatore
								Tamilnadu India</li>
							<li><i class="fa fa-at"></i> Horruit.com</li>
							<li><i class="fa fa-envelope"></i> dude@horruit.com</li>
							<li><i class="fa fa-phone-square"></i>91-9962230256</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorContactMenu').addClass('active');

    $(".ctContactUsIcon").tooltip();

    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerContactusMenu').addClass('active');
</script>
