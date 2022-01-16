<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판상세보기</title>
<link rel="stylesheet" href="/cookie/css/w3.css">
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<style>
    
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#home').click(function(){
			location.href = '/cookie/main.cnu';
		});
		$('#list').click(function(){
			$('#frm').submit();
		});
		
		$('#delete').click(function(){
			$('#frm').attr('action', '/cookie/board/delete.cnu');
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form method="post" action="/cookie/board/board.cnu" id="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
		<input type="hidden" name="bno" value="${DATA.bno}">
	</form>
    <div class="w3-content" style="max-width: 1000px;">
        <div class="w3-center w3-pink w3-col ">
            <h2>상세보기페이지</h2>
        </div>
        <div class="w3-row w3-margin-top">
       <c:if test="${SID eq DATA.id}">
            <div class="w3-row  w3-right-align ">
                <div class="w3-button w3-red" id="update">수정</div>
                <div class="w3-button w3-red" id="delete">삭제</div>
            </div>
       </c:if>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding w3-center">
                    <h2>${DATA.title}</h2>
                </div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding">
                    <div style="display: flex;">
                        <div class="w3-margin-right" style="display: flex;">
                            <p class="w3-margin-right">작성자:</p>
                            <p class="w3-border-right" style="height: 25px; padding-right: 20px;">${DATA.id}</p>        
                        </div>
                        <div class="w3-margin-left" style="display: flex;">
                            <p class="w3-margin-right">작성일:</p>
                            <p class="w3-border-right" style="height: 25px; padding-right: 20px;">${DATA.sdate}</p>        
                        </div>     
                    </div>
                    <div style="display: flex;">                        
	                    <c:if test="${not empty DATA.file}">
                        	<p class="w3-margin-right" style="margin-top: 0px;">파일다운</p>                  
                        	<c:forEach var="pic" items="${DATA.file}" varStatus="st">
	                        	<a class="w3-margin-right" href="/cookie/img/upload/${pic.savename}">링크${st.count}</a> 
							</c:forEach>
	                    </c:if>
                    </div>
                </div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding mih w3-margin-bottom" style="min-height: 100px;">
                    <div>${DATA.body}</div>
                </div>
            </div>
        </div>
        <div class="w3-row w3-margin-top w3-margin-bottom">
            <div class=" w3-button w3-blue w3-right" id="list">목록으로</div>
            <div class=" w3-button w3-green w3-left" id="home">홈으로</div>
        </div>
      </div>
</body>
</html>