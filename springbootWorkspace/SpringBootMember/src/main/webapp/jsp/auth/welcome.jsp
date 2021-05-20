<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>
	<%-- 인증정보 : ${pageContext.request.userPrincipal}<br> --%>
	
	<c:if test="${empty pageContext.request.userPrincipal.name}">
		<script>
			alert("이미 로그아웃되었습니다. 로그인 페이지로 이동합니다.");
			location.href="${pageContext.request.contextPath}/login.do";
		</script>
	</c:if>
	
	<c:if test="${not empty pageContext.request.userPrincipal.name}">
		${pageContext.request.userPrincipal.name} 님 환영합니다.<br>
		
		<input type="button" 
			   value="로그아웃"
			   onclick="location.href='${pageContext.request.contextPath}/logout_proc.do'" />	
			   
		<input type="button" 
			   value="회원정보 보기"
			   onclick="location.href='${pageContext.request.contextPath}/member/member_view.do?memberId=${pageContext.request.userPrincipal.name}'" />			   
			   
		<input type="button" 
			   value="회원정보 수정"
			   onclick="location.href='${pageContext.request.contextPath}/member/member_update.do?memberId=${pageContext.request.userPrincipal.name}'" />
			   
		<input type="button" 
			   value="회원정보 삭제"
			   onclick="location.href='${pageContext.request.contextPath}/member/member_delete.do?memberId=${pageContext.request.userPrincipal.name}'" />
			    	   
	</c:if>
	
	<!-- 추가 -->
	<!-- 관리자일 경우 -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<input type="button" 
			   value="전체 회원정보 조회"
			   onclick="location.href='${pageContext.request.contextPath}/admin/members_all_view.do?page=1'" />
	</sec:authorize>
	
</body>
</html>