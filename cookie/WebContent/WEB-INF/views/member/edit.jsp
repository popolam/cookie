<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cookie/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cookie/css/user.css">
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cookie/js/w3color.js"></script>
<script type="text/javascript" src="/cookie/js/join.js"></script>
<style type="text/css">
	.avtimg {
		width: 100px;
		height: 100px;
	}
	.avtbox {
		display: inline-block;
		width: 102px;
		height: 117px;
	}
	#pwmsg, #repwmsg, #idmsg {
		display: none;
	}
	
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="w3-content w3-margin-top mxw700">
		<!-- 타이틀 -->
		<h1 class="w3-pink w3-center w3-padding w3-card-4">${DATA.name } 회원 정보 수정</h1>
		<form method="POST" action="/cookie/member/editProc.cnu" name="frm" id="frm"
			class="w3-col w3-margin-top w3-margin-bottom w3-padding w3-card-4">
			
			<input type="hidden" name="mno" id="mno" value="${DATA.mno}">
			
			<div class="w3-hide" id="cmail">${DATA.mail}</div>
			<div class="w3-hide" id="ctel">${DATA.tel}</div>
			<div class="w3-hide" id="cano">${DATA.ano}</div>
			
			<div>
				<label for="name" class="w3-col s3 w3-right-align w3-margin-top clrgrey ft14 mgb10">회원이름 : </label>
				<input type="text" name="name" id="name" class="w3-col s8 w3-margin-top mgl10 w3-input mgb10 w3-white" value="${DATA.name}" disabled>
			</div>
			<div>
				<label for="id" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">아 이 디 : </label>
				<input type="text" name="id" id="id" class="w3-col s8 mgl10 w3-input mgb10 w3-white" value="${DATA.id}" disabled>
			</div>
			<div>
				<label for="pw" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">비밀번호 : </label>
				<div class="w3-col s8 mgl10 mgb10">
					<input type="password" name="pw" id="pw" class="w3-col w3-input w3-border">
				</div>
			</div>
			<div>
				<label for="repw" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">pw check : </label>
				<div class="w3-col s8 mgl10 mgb10">
					<input type="password" name="repw" id="repw" class="w3-col w3-input w3-border">
					<span class="w3-col w3-text-red" id="repwmsg"># 비밀번호가 일치하지 않습니다.</span>
				</div>
			</div>
			<div>
				<label for="mail" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">회원메일 : </label>
				<input type="text" name="mail" id="mail" class="w3-col s8 mgl10 w3-input w3-border mgb10" value="${DATA.mail }">
			</div>
			<div>
				<label for="tel" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">전화번호 : </label>
				<input type="text" name="tel" id="tel" class="w3-col s8 mgl10 w3-input w3-border mgb10" value="${DATA.tel }">
			</div>
			<div>
				<label class="w3-col s3 w3-right-align clrgrey ft14 mgb10">회원성별 : </label>
				<div class="w3-col s8 mgl10 mgb10 w3-center">
					${DATA.gen == 'M' ? '남 자' : '여 자'}
				</div>
			</div>
			<div class="w3-col" id="avtfr">
				<label class="w3-col s3 w3-right-align clrgrey ft14 mgb10">아 바 타 : </label>
				<div class="w3-col s8 mgl10 mgb10 w3-center">
			<c:if test="${DATA.gen eq 'M' }">
						<div class="avtboxfr w3-center w3-margin-top" id="mavt">
					<c:forEach var="data" items="${LIST}">
						<c:if test="${data.gen == 'M' }">
						 	<div class="avtbox">
						 		<label for="mavt${idx}">
						 			<img src="/cookie/img/avatar/${data.avatar}" class="w3-col avtimg">
						 		</label>
						 	<c:if test="${DATA.ano eq data.ano}">
						 		<input type="radio" name="ano" id="mavt${data.ano}" value="${data.ano}" checked>
						 	</c:if>
						 	<c:if test="${DATA.ano ne data.ano}">
						 		<input type="radio" name="ano" id="mavt${data.ano}" value="${data.ano}">
						 	</c:if>
						 	</div>
						 </c:if>
					</c:forEach>
						</div>
			</c:if>
			<c:if test="${DATA.gen ne 'M' }">
						 <div class="avtboxfr w3-center w3-margin-top" id="favt">
					<c:forEach var="data" items="${LIST}">
						 <c:if test="${data.gen == 'F' }">
						 	<div class="avtbox">
						 		<label for="favt${data.ano}">
						 			<img src="/cookie/img/avatar/${data.avatar}" class="w3-col avtimg">
						 		</label>
						 	<c:if test="${DATA.ano eq data.ano}">
						 		<input type="radio" name="ano" id="favt${data.ano}" value="${data.ano}" checked>
						 	</c:if>
						 	<c:if test="${DATA.ano ne data.ano}">
						 		<input type="radio" name="ano" id="favt${data.ano}" value="${data.ano}">
						 	</c:if>
						 	</div>
						 </c:if>
				 	</c:forEach>
				 		</div>
				</c:if>
				</div>
			</div>
		</form>
		
		<!-- 버튼 태그 -->
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-deep-orange w3-hover-orange w3-button" id="rbtn">reset</div> 
			<div class="w3-third w3-green w3-hover-lime w3-button" id="hbtn">home</div> 
			<div class="w3-third w3-blue w3-hover-aqua w3-button" id="ebtn">edit</div> 
		</div>
	</div>
</body>
</html>
