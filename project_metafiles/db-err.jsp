<%@ page isErrorPage="true" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>DB error patch</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script>
$(function() {
	
	$("#err_dialog").dialog({
		
	      resizable: false,
	      height: "auto",
	      width: 400,
	      modal: true,
	      open: function (event, ui) { 
	    	  // 기존 창닫기 버튼 은닉
              $(this).parent().children().children(".ui-dialog-titlebar-close").hide();
          },
	      buttons: {
	        "창닫기": function() {
	        	
	          $( this ).dialog( "close" );
	          
	          setTimeout(function() {
		  			location.href= "${pageContext.request.contextPath}"; // 페이지 이동
		  	  }, 100); 
	        }
	      } //
	   });
	});
</script>
</head>
<body>

	<div id="err_dialog" title="에러 메시지">
		<p>${db_error}<br><br>
	              홈페이지로 이동하겠습니다.</p>
	</div>  
	
</body>
</html>