<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>javascript resource path sample</title>

<!-- contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 

<!-- jQuery -->
<script src="<c:url value="/webjars/jquery/3.6.0/dist/jquery.min.js" />"></script>

<!-- bootstrap css -->
<link rel="stylesheet" href="${contextPath}/webjars/bootstrap/4.6.0/css/bootstrap.min.css" />

<!-- bootstrap js -->
<script src="${contextPath}/webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>

<!-- common.css -->
<link rel="stylesheet" href="<c:url value='/css/common.css' />">

<!-- common.js -->
<script src="<c:url value='/js/common.js' />" charset="utf-8"></script>

<script>
$(function() {
	
	$("#btn").click(function(e){
		
		alert("target id : "+e.target.id);
	});
});
</script>
</head>
<body>

	<button type="button" id="btn" class="btn btn-primary mt-2 ml-2">버튼</button>
	
	<br><br>
	
	<a id="btn_styl" href="javascript:demo()">외장 javascript 테스트</a>
	
</body>
</html>