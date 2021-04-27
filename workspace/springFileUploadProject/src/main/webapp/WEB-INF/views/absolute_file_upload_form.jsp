<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Spring File upload Form Demo</title>

<!-- jQuery -->
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script src="<c:url value='/jquery/jquery-3.5.1.min.js' />"></script>

<script>
$(document).ready(function() {

	$('#addFile').click(function() {
        
		var fileIndex = $('#fileTable tr').children().length;
		
		console.log("fileIndex : "+fileIndex);
		
        $('#fileTable').append(
                '<tr><td>'+
                '   <input type="file" name="files['+ fileIndex +']" />'+
                '</td></tr>');
    });
     
});
</script>
</head>
<body>
<h2>Spring MVC File Upload(파일 업로드:절대 경로) Demo</h2>

	<!-- 절대 경로(k:/upload)에 업로드(저장) --> 
	
	<form:form  method="post" 
				action="save"
  				modelAttribute="uploadForm" 
				enctype="multipart/form-data"> 
				
	    <p>전송할 파일(들)을 선택합니다. </p>
	 
	    <input id="addFile" type="button" value="전송 파일 추가" />
	    
	    <table id="fileTable">
	        <tr>
	            <td><input name="files[0]" type="file" /></td>
	        </tr>
	    </table>
	    
	    <br>
	    <input type="submit" value="전송" />
	
	</form:form>

</body>
</html>