<%@ page language="java" contentType="text/html" %>
<%@ include file="lib/header.jsp" %>
<link rel="stylesheet" href="${baseURL}/assest/plugin/datepicker/tsdatepicker.css" media="screen" />
<%-- <link rel="stylesheet" href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" /> --%>
<link rel="stylesheet" href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendor.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;" class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row"> 
				<div class="col-md-8 ctExistingTripDetails">
	                <div class="kd-section-title">
	                	<div class="alert alert-success alert-dismissable">
	                        <i class="fa fa-check"></i>
	                        <button aria-hidden="true" data-dismiss="alert" class="close" type="button">�</button>
	                        <b>Alert!</b> Successfully updated. Please wait untill admin approve your updates
	                    </div>
	                </div>
	             </div>   
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp" %>