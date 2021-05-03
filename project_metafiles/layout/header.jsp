<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>tiles layout header</title>
</head>
<body>

	<ul class="navbar-nav">
		<li class="nav-item active">
			<a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="link1.do">Link</a></li>
		<li class="nav-item"><a class="nav-link" href="anotherLayout.do">another layout</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
		</li>
	</ul>

</body>
</html>