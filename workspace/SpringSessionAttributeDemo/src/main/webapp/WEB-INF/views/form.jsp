<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>form</title>
</head>
<body>

	<form:form modelAttribute="demoVO"
			   action="action.do"
			   method="post">
			   
		   <label for="id">id : </labeL>
		   <form:input path="id" /><br>
		   
		   <label for="name">name : </label>
		   <input type="text" name="name" /><br>
		   
		   <input type="submit" value="전송" />
			   
	</form:form>		   	

</body>
</html>