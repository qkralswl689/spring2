<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session status</title>
</head>
<body>
@SessionAttributes Session : ${sessionScope.demoVO}<br>
demoVO : ${demoVO}<br>
</body>
</html>