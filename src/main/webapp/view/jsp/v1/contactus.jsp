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
				<%@ include file="lib/c_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8 ctAddListingDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Contact Us</h3>
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

    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerContactusMenu').addClass('active');
</script>
