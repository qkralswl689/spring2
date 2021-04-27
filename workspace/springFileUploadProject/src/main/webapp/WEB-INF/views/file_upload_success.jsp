<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC Multiple File Upload</title>
</head>
<body>
업로드된 파일들 : ${files}<br>

    <h1>Spring File Upload Demo(절대 경로)</h1>
    <p>파일 전송 성공</p>
    <ol>
        <c:forEach items="${files}" var="file" varStatus="st">
            <li>
            	${fn:replace(filePathList.get(st.index), "//","\\")} : 
            	<%-- <a href='${fn:replace(filePathList.get(st.index), "//","\\")}'>${file}</a> --%>
            	<%-- <a href="<c:url value='/upload/${file}' />">${file}</a> --%>
            	<a href="<c:url value='/upload/${file}' />">${deFiles.get(st.index)}</a>
           	</li>
        </c:forEach>
    </ol>
</body>
</html>