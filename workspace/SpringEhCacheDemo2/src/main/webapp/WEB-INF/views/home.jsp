<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 회원 보기</title>
</head>
<body>

	<c:forEach items="${members}" var="member" varStatus="st">
		${st.count}&nbsp;
		${member.employeeId}&nbsp;
		${member.firstName}&nbsp;
		${member.email}<br>
	</c:forEach>
	 
</body>
</html>