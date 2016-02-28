<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendorcontact.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Contact US</h3>
					</div>
					<form action="#" method="post" class="form-horizontal">
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
							</label>
							<div class="col-lg-4 col-md-4 col-sm-4"></div>
						</div>
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
							</label>
							<div class="col-lg-4 col-md-4 col-sm-4"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctvendorcontact.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorContactMenu').addClass('active');
</script>