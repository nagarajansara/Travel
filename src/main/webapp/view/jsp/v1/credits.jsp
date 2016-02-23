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
						<h3 class="ctFont_FamilyStyle">Credits Details</h3>
					</div>
					<form action="#" method="post" role="form"
						class="form-horizontal ctVendorListingForm">
						<div class="form-group">
							<c:if test="${not empty model.responseData.credits}">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Current
									credits</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<input type="text" disabled
										value="${model.responseData.credits}" name="credits"
										placeholder="credits" class="bmQuoteCommonFontStyle"
										id="ctVendorCredits" />
								</div>
							</c:if>
						</div>
					</form>
					<div class="row">
						<div class="col-md-8 col-sm-8">
							<div class="kd-section-title">
								<h3 class="ctFont_FamilyStyle">Credits Reduction History</h3>
							</div>
						</div>
						<div class="col-md-12 col-sm-12">
							<c:if test="${not empty model.responseData.history}">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Reason</th>
											<th>Credits Deduction</th>
											<th>Date</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${model.responseData.history}" var="element"
											varStatus="loopElement">
											<tr>
												<td><c:out value="${ loopElement.index + 1}"></c:out></td>
												<td>${element.reason }</td>
												<td>${element.tripcredits }</td>
												<td>${ element.createddate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
							<c:if test="${empty model.responseData.history}">
								<span
									style="font-family: verdana; color: red; font-weight: bold; font-size: 12px;">No
									credits history found</span>
							</c:if>
							<div class="form-group">
								<label class="col-lg-12 col-md-12 col-sm-12 control-label">
									<c:if test="${model.responseData.numEntries gt 5}">
										<a class="frame-btn thbg-color ctCreditsReductionDetailsBtn"
											href="javascript:void(0)">Request email to more details</a>
										</br>&nbsp;
										<img class="ctLoader" style="display: none;"
											src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==">
									</c:if>
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctcredits.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorCreditsMenu').addClass('active');

    ctCreditsOnInit();
</script>