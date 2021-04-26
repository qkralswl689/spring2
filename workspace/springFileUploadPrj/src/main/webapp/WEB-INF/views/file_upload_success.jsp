<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Original/Thumbnail Image File Upload</title>
</head>
<body>

    <h3>Spring Original/Thumbnail Image File Upload</h3>
    <p>파일 전송 성공.</p>
    
    <c:forEach items="${files}" var="file" varStatus="st">
        file : ${file}<br>
        <img src="<c:url value='/image/${file}' />" height="300" /><br>
        ${thumbFiles.get(st.index)}
        <img src="<c:url value='/image/thumbnail/${thumbFiles.get(st.index)}' />" />
        <br><br>
    </c:forEach>
    
</body>
</html>