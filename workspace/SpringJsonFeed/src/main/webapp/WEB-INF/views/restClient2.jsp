<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>rest client</title>
<script src="<c:url value="/jQuery/jquery-3.4.1.min.js" />"></script>
<script>
$(function() {
    
    /* $.ajax 옵션들 */
    /* data : JSON.stringify(data), */
    /* timeout : 100000, */
    /* dataType : 'json',*/
    /* type : "POST",    */
    
    $("input#btn").click(function(e) {
    	
    	alert("요청");
            
          $.ajax({
                     // url : "${pageContext.request.contextPath}/jsonFeed2",
                     url : "${pageContext.request.contextPath}/jsonFeed",
                     contentType : "application/json",
                     data : {
                    	 id : $("#id").val()
                     },
                     success : function (json) {
                    	 
                    	  alert("성공");
                    	  
                    	  // Spring :: @ResponseBody JSON Feed용 
                          // 주의사항 ! JSON.stringify() 사용 !
                          // var jsonObj = JSON.parse(JSON.stringify(json));
                          var jsonObj = JSON.parse(json);
                                
                          alert("JSON name : " +jsonObj.name);
                          $("textarea#result").val("JSON name : " +jsonObj.name);
                     }, 
                     
                     error : function(xhr, status) {
                         alert(xhr+" : "+status);
                    } 
         }); // $.ajax
         
    });  //
    
    /* id 필드 초기 CSS */
    $("#id").css({"border":"0px"});
    $("#id").attr("readonly","true");
    $("#id").val("(초기)편집 불가 상태");
    
    
    // id 필드를 클릭하였을 때 text 필드 활성화(입력 불능 => 입력 가능 상태 변환)
    /*
    $("#id").on("click", function(e) {
    	
    	console.log("아이디 클릭");
    	
    	$("#id").css({"border":"solid 1px"});
    	$("#id").removeAttr("readonly");
        $("#id").val("편집 가능 상태");
    	
    });
    */
    // 편집 가능 상태 조절 버튼
    $("button#status_btn").click(function() {
    	
    	$(this).toggleClass(function(){
    		
    		// 편집 불가 -> 편집 가능
    		if ($(this).text() == '편집 가능') { 
    			
	    		$("#id").css({"border":"solid 1px"});
	        	$("#id").removeAttr("readonly");
	            $("#id").val("편집 가능 상태");
	            $(this).text("편집 불가");
	            
    		} else { // 편집 가능 -> 편집 불가
    			
    			$("#id").css("border", "0px");
	        	$("#id").attr("readonly", "readonly");
	            $("#id").val("편집 불가 상태");
	            $(this).text("편집 가능");
    		} //
    	});
    });
    
});     
</script>
</head>
<body>
 
         아이디 : <input type="text" id="id" readonly>&nbsp;
    <button id="status_btn">편집 가능</button><br>
    <input type="button" id="btn" value="JSON 요청"><br>
         전송 결과 :<br>
   	<textarea id="result" readonly></textarea>
    
 
</body>
</html>