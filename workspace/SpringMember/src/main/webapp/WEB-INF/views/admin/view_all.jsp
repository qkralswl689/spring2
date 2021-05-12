<%@page import="com.javateam.SpringMember.domain.MemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>전체 회원 정보 조회</title>

<!-- bootstrap CSS : 4.6.0 -->
<link rel="stylesheet" href="${rootPath}/webjars/bootstrap/4.6.0/css/bootstrap.min.css">

<!-- jquery : 3.6.0 -->
<script src="${rootPath}/webjars/jquery/3.6.0/jquery.min.js"></script>

<!-- bootstrap JS : 4.6.0 -->
<script src="${rootPath}/webjars/popper.js/1.16.0/umd/popper.min.js"></script>

<script src="${rootPath}/webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>

<style>
/* 화면 크기 오버할 경우 가로 스크롤바 기능 추가 */
div#view_all_wrap {
	overflow-x:auto;
}

table#view_all_table {
	width:1400px;
	text-align:center;
	margin:auto;
	background:#94D8F6;
	font-size:9pt;
}	

table#view_all_table td, table#view_all_table th {
	padding:5px;
	margin:1px;	
}

table#view_all_table td {
	background:#fff;
}

table#view_all_table thead {
	background:#007AAE;
	color:#fff;
}

table#paging_tbl {
	margin:auto;
	text-align:center;
	height:150px;
}
</style>

<script>
window.onload = function() {
	
	view_all_move_btn.onclick = function() {
		
		location.href='${rootPath}/member/viewAll.do';
	}
	
	// 회원 수정 버튼 클릭시
	var update_btns = document.querySelectorAll("[id^=member_update_btn]");
	
	for (const update_btn of update_btns) {
		
		update_btn.onclick = function(e) {
			
			alert("수정");
			
			// 아이디 파악
			var memberId = e.currentTarget.id.substring("member_update_btn_".length,update_btn.length);
			console.log("memberId : "+memberId);
			
			location.href = "${rootPath}/member/member_update.do?memberId="+memberId;
			
		} //
		
	} //for
	
	
	// 회원 삭제 버튼 클릭시
	var delete_btns = document.querySelectorAll("[id^=member_delete_btn]");
	
	for (var delete_btn of delete_btns) {
		
		delete_btn.onclick = function(e) {
			
			// 정말 삭제할 지 다시 한번 점검
			if (confirm("정말 삭제하시겠습니까?")) {
				
				// 아이디 확보
				var memberId = e.currentTarget.id.substring("member_delete_btn_".length,delete_btn.length);
				console.log("memberId : "+memberId);
				
				location.href = "${rootPath}/member/deleteProc.do?memberId="+memberId;
			} //
			
		} //
		
	} //for	
	
} //	
</script>
</head>
<body>

	<!-- 전체 레이아웃 -->
	<div id="view_all_wrap">
		<br>
		<!-- 회원 정보 테이블(grid) -->
		<table id="view_all_table">
			<thead>
				<tr>
					<th>번호</th>
					<th>회원아이디</th>
					<th>패쓰워드</th>
					<th>별명</th>
					<th>이름</th>
					<th>성별</th>
					<th>이메일</th>
					<th>연락처</th>
					<th>생일</th>
					<th>우편번호</th>
					<th>기본주소</th>
					<th>상세주소</th>
					<th>가입일</th>
					<th>메뉴</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:set var="page" value="${empty page ? 1 : page}" />
				<c:set var="limit" value="${empty limit ? 1 : limit}" /> 
				
				<c:forEach items="${members}" var="member" varStatus="st">
				<tr>
					<td>${st.count+((pageVO.page-1)*limit)}</td>
					<td>${member.memberId}</td>
					<td>********</td>
					<td>${member.memberNickname}</td>
					<td>${member.memberName}</td>
					<td>${member.memberGender=='m' ? '남' : '여'}</td>
					<td>${member.memberEmail}</td>
					<td>${member.memberPhone}</td>					
					<td>${member.memberBirth}</td>
					<td>${member.memberZip}</td>
					<td>${member.memberAddressBasic}</td>
					<td>${member.memberAddressDetail}</td>
					<td><fmt:formatDate value="${member.memberJoinDate}" pattern="yyyy년 M월 d일" /></td>
					<td>
						<!-- <button id="member_update_btn" class="btn btn-sm btn-primary">수정</button>
						<button id="member_delete_btn" class="btn btn-sm btn-primary">삭제</button> -->
						
						<!-- 버튼의 고유성(아이디)과 인자(memberId) 전달을 위해 버튼의 id를 변경함 -->
						<button id="member_update_btn_${member.memberId}" class="btn btn-sm btn-primary">수정</button>
						<button id="member_delete_btn_${member.memberId}" class="btn btn-sm btn-primary">삭제</button>
					</td>
				</tr>
				</c:forEach>				
			</tbody>
			
		</table>
		
		<!-- 페이징 -->
		<table id="paging_tbl">
			<tr>
				<td height="80" valign="bottom">
					<%@ include file="paging.jspf" %>
				</td>
			</tr>
		</table>
		
	</div>
	<!--// 전체 레이아웃 -->
</body>
</html>