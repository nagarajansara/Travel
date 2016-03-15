<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
<link rel="stylesheet" href="${baseURL}/assest/css/ctviewvendor.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row">
				<div class="col-md-12">
					<div class="kd-blog-detail">
						<div class="kd-admin thememargin ctVendorProfile">
							<c:if test="${not empty model.responseData}">
								<c:forEach items="${model.responseData}" var="element"
									varStatus="loop">
									<c:set value="${element.totaltrip}" var="totalTrips"></c:set>
									<c:set value="${element.starrating}" var="startRatting"></c:set>
									<article class="col-md-6">
										<figure>
											<a href="#"> <img src="${baseURL}/assest/img/default.jpg">
											</a>
										</figure>
										<div class="admin-info ctVendorAdminInfo">
											<h2>${element.firstname}&nbsp;${element.lastname}</h2>
											<span class="ctTotalTrips"> Total Trips:
												${element.totaltrip } &nbsp; &nbsp; ${element.totalreviews }
												&nbsp;customer reviews </span> </br> </br>
											<div id="ctRatedStar"></div>
										</div>
									</article>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctviewvendor.js"></script>
<script src="${baseURL}/assest/plugin/startrating/jquery.raty-fa.js"></script>
<script type="text/javascript">
    var totalTrips = '${totalTrips}';
    var startRatting = '${startRatting}';
    $('#ctRatedStar').raty({
	score : startRatting,
	readOnly : true,
	halfShow : true
    });
</script>