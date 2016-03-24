<%@ page language="java" contentType="text/html"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<style type="text/css">
.ctActivationSuccess {
	font-family: Junction;
	font-size: 12px;
	color: green;
}

.ctActivationError {
	font-family: Junction;
	font-size: 12px;
	color: red;
}
</style>
</head>
<body>
	<span> <c:if test="${not empty model.responseStatus}">
			<c:if test="${model.responseData eq 1}">
				<span class="ctActivationSuccess">User activated successfully</span>
			</c:if>
			<c:if test="${model.responseData ne 1}">
				<span class="ctActivationError">User activation failed</span>
			</c:if>
		</c:if>
	</span>
</body>
</html>