<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
<head>
<title>base layout</title>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<style>
.footer_styl
{
	background:gray; 
	height:50px; 
	display:flex;
	align-items:center;
	justify-content:center;
}
</style>
</head>

<body class="wrapper">

	<!-- header -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<tiles:insertAttribute name="header" />
	</nav>

	<!-- body -->
	<div id="content-wrapper">
		
		<!-- body title -->
		<div class="row">
			<div class="col-lg-12">
				<h4 class="page-header">${pageHeader}</h4>
			</div>
		</div>
		
		<!-- body content -->
		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>

	</div>

	<!-- footer -->
	<div class="row footer_styl">
		<tiles:insertAttribute name="footer" />
	</div>
	
</body>
</html>