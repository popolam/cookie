<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판상세보기</title>
<link rel="stylesheet" href="/cls/css/w3.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<style>
    
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#home').click(function(){
			location.href = '/cls/main.cls';
		});
		$('#list').click(function(){
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form method="post" action="/cls/board/board.cls" id="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
		<input type="hidden" name="bno" value="${DATA.bno}">
		<input type="hidden" name="title" value="${DATA.title}">
		<input type="hidden" name="body" value="${DATA.body}">
		<input type="hidden" name="name" value="${DATA.name}">
		<input type="hidden" name="click" value="${DATA.click}">
		<input type="hidden" name="sdate" value="${DATA.sdate}">
<c:if test="${DATA.file.get(0).bino ne 0}">
	<c:forEach var="data" items="${DATA.file}"> 
		<input type="hidden" name="bino" value="${data.bino}">
		<input type="hidden" name="oriname" value="${data.oriname}">
		<input type="hidden" name="savename" value="${data.savename}">
	</c:forEach>
</c:if>
	</form>
    <div class="w3-content" style="max-width: 1000px;">
        <div class="w3-center w3-col ">
            <h2>상세보기페이지</h2>
        </div>
        <form method="post" action="/cls/board/boardEditProc.cls" class="w3-row w3-margin-top" id="efrm">
            <div class="w3-row  w3-right-align ">
                <div class="w3-button w3-red" id="update">수정</div>
                <div class="w3-button w3-red" id="delete">삭제</div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding w3-center">
                    <input type="text" name="title" value="${DATA.title}">
                </div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding">
                    <div style="display: flex;">
                        <div class="w3-margin-right" style="display: flex;">
                            <p class="w3-margin-right">작성자:</p>
                            <p class="w3-border-right" style="height: 25px; padding-right: 20px;">${DATA.name}</p>        
                        </div>
                        <div class="w3-margin-right" style="display: flex;">
                            <p class="w3-margin-right">작성일:</p>
                            <p class="w3-border-right" style="height: 25px; padding-right: 20px;">${DATA.sdate}</p>        
                        </div>     
                    </div>
                    <div style="display: flex;">                        
	                    <c:if test="${not empty DATA.file}">
                        	<p class="w3-margin-right" style="margin-top: 0px;">파일다운</p>                  
                        	<c:forEach var="pic" items="${DATA.file}" varStatus="st">
	                        	<a class="w3-margin-right" href="/clsProj/brdimg/${pic.savename}">링크${st.count}</a> 
							</c:forEach>
	                    </c:if>
                    </div>
                </div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding mih w3-margin-bottom" style="min-height: 700px;">
                    <textarea name="body" cols="105" rows="10">${DATA.body}</textarea>
                </div>
            </div>
        </form>
        <div class="w3-row w3-margin-top w3-margin-bottom">
            <div class=" w3-button w3-blue" id="list">목록으로</div>
            <div class=" w3-button w3-green" id="home">홈으로</div>
        </div>
      </div>
</body>
</html>