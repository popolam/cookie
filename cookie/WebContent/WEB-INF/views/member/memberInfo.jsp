<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Information</title>
<link rel="stylesheet" type="text/css" href="/cookie/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cookie/css/kingdom.css">
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cookie/js/info.js"></script>
</head>
<body>
	<div class="w3-content w3-center mw700">
		<h1 class="w3-teal w3-card-4">[ ${DATA.name} ] 님 회원 정보</h1>
		<div class="w3-col w3-border-bottom pdb10">
			<span class="w3-cell m2 w3-button w3-small w3-green w3-hover-lime w3-left mt0 btnBox" id="hbtn">Home</span>
	<c:if test="${not empty SID and (DATA.id eq SID)}">
			<span class="w3-cell m2 w3-button w3-small w3-orange w3-hover-deep-orange w3-right mt0 btnBox" id="iebtn">정보수정</span>
			<span class="w3-cell m2 w3-button w3-small w3-red w3-hover-pale-red w3-right mt0 btnBox" id="dbtn">탈 퇴</span>
	</c:if>
		</div>
		
		<div class="w3-col w3-card-4 w3-margin-top w3-padding">
			<div class="w3-col">
			<div class="w3-col w250 pd10">
				<div class="w3-border infoAvtBox">
					<img src="/cookie/img/avatar/${DATA.avatar}" class="infoAvtBox" id="${DATA.ano}">
				</div>
			</div>
				<div class="w3-rest pdr10">
					<div class="w3-col w3-display-container contBox">
						<div class="w3-col w3-display-middle">
							<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원번호 : </span><span class="w3-twothird w3-center" id="no">${DATA.mno}</span></div>
							<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원이름 : </span><span class="w3-twothird w3-center">${DATA.name}</span></div>
							<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">아 이 디 : </span><span class="w3-twothird w3-center">${DATA.id}</span></div>
							<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원메일 : </span><span class="w3-twothird w3-center" id="cMail">${DATA.mail}</span></div>
							<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">회원성별 : </span>
								<span class="w3-twothird w3-center">
									<c:if test="${DATA.gen == 'M'}">남 자</c:if>
									<c:if test="${DATA.gen eq 'F'}">여 자</c:if>
								</span>
							</div>
							<div class="w3-col w3-text-grey ft18px mh3"><span class="w3-third w3-right-align">가 입 일 : </span><span class="w3-twothird w3-center">${DATA.sdate}</span></div>
						</div>
					</div>
				</div>
			</div>
		</div>

<c:if test="${SID eq DATA.id }">
	<!-- 회원탈퇴 모달 -->
	<div id="leave" class="w3-modal" style="display: none;">
			<div class="w3-modal-content">
		    <header class="w3-container w3-red">
		      <span onclick="document.getElementById('leave').style.display='none'" 
		      class="w3-button w3-display-topright">&times;</span>
		      <h2>회원탈퇴</h2>
		    </header>
		    <div class="w3-container w3-margin-top w3-margin-bottom">
				<form method="POST" action="/cookie/member/signout.cnu" 
						id="dfrm" name="dfrm"
						class="w3-col w3-padding w3-margin-bottom">
					<input type="hidden" name="id" value="${DATA.id}">
					<input type="hidden" name="mno" value="${DATA.mno}">
					<h2 class="w3-container w3-center w3-padding">탈퇴하시겠습니까?</h2>
					<label for="pw" class="w3-col m3 w3-text-grey ft18px">비밀번호 : </label>
					<input type="password" id="pw" name="pw" 
							class="w3-col m7 w3-input w3-border">
					<div class="w3-col m2 pdh10">
						<div class="w3-col w3-button w3-medium w3-red w3-hover-orange w3-left mt0" id="del">탈퇴처리</div>
					</div>
				</form>
		    </div>
		  </div>
	</div>
	
	
			<!-- 메세지 확인 모달 -->
	<c:if test="${not empty MSG}">
			<div id="id01" class="w3-modal" style="display: block;">
			  <div class="w3-modal-content">
			<c:if test="${MSG eq '회원 정보가 수정되었습니다.'}">
			    <header class="w3-container w3-teal">
			      <span onclick="document.getElementById('id01').style.display='none'" 
			      class="w3-button w3-display-topright">&times;</span>
			      <h2>회원 정보 수정 성공</h2>
			    </header>
			</c:if>
			<c:if test="${MSG eq '회원 정보 수정에 실패했습니다.'}">
			    <header class="w3-container w3-red">
			      <span onclick="document.getElementById('id01').style.display='none'" 
			      class="w3-button w3-display-topright">&times;</span>
			      <h2>회원 정보 수정 실패</h2>
			    </header>
			</c:if>
			<c:if test="${MSG eq '탈퇴처리에 실패했습니다.'}">
			    <header class="w3-container w3-red">
			      <span onclick="document.getElementById('id01').style.display='none'" 
			      class="w3-button w3-display-topright">&times;</span>
			      <h2>회원 탈퇴 처리 실패</h2>
			    </header>
			</c:if>
			    <div class="w3-container w3-margin-top w3-margin-bottom">
			      <h4 class="w3-center w3-text-grey w3-margin-top w3-margin-bottom">${MSG}</h4>
			    </div>
			  </div>
			</div>
	</c:if>
</c:if>
	</div>
</body>
</html>