<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session List status</title>
</head>
<body>
@SessionAttributes Session (all) : ${sessionScope.list}<br>
@SessionAttributes Session (index=0) : ${sessionScope.list.get(0)}<br>
</body>
</html>