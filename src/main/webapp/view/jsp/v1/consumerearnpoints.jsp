<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<%-- <link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" /> --%>
<link rel="stylesheet"
	href="${baseURL}/assest/css/ctconsumermytravels.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctConsumerContainer">
			<div class="row">
				<%@ include file="lib/c_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">My points</h3>
					</div>
					<c:if test="${not empty model.responseData.points}">
						<table class="table table-striped ctEnquiryTableStatus">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Points</th>
									<th>Type</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.points}" var="element"
									varStatus="loopElement">
									<tr>
										<td><c:out value="${ loopElement.index + 1}"></c:out></td>
										<td><c:out value="${ element.points}"></c:out></td>
										<td><c:out value="${ element.type}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.points}">
						<span class="ctDangerDefaultTxt">No data found</span>
					</c:if>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script type="text/javascript">
    $('.ctCustomerListMenu li').removeClass('active');
    $('.ctCustomerListMenu .ctCustomerPointsMenu').addClass('active');
</script>
