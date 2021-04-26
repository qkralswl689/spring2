<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="UTF-8">
<title>Original/Thumbnail Image File Upload Demo</title>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
$(document).ready(function() {

	$('#addFile').click(function() {
        
		var fileIndex = $('#fileTable tr').children().length;
		
        $('#fileTable').append(
                '<tr><td>'+
                '   <input type="file" name="files['+ fileIndex +']" />'+
                '</td></tr>');
    });
     
});
</script>
</head>
<body>

	<h3>Spring Original/Thumbnail Image File Upload</h3>
 
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
	        <tr>
	            <td><input name="files[1]" type="file" /></td>
	        </tr>
	    </table>
	    
	    <br>
	    <input type="submit" value="전송" />
	
	</form:form>

</body>
</html>