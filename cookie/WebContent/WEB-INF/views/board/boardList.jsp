<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BlackPink Board</title>
<link rel="stylesheet" href="/cookie/css/w3.css">
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<style>
    .mxw {
    	max-width: 1000px;
    }
    .bnone{
    	display: none;
    }
    .pd2 {
    	padding: 2px;
    }
    .content:nth-child(odd) {
    	background-color: #f2e6ff;
    }
</style>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#home').click(function(){
			$(location).attr('href', '/cookie/main.cnu');
		});
		
		$('#login').click(function(){
			$(location).attr('href', '/cookie/member/login.cnu');
		});
		
		$('#write').click(function(){
			$(location).attr('href', '/cookie/board/boardWrite.cnu');
		});
		
		$('.pbtn').click(function(){
			// 할일
			// 1. 어떤 버튼이 클릭이 됬는지 알아낸다.
			var str = $(this).text();
			$('#nowPage').val(str);
			$('#frm').attr('action', '/cookie/board/board.cnu');
			$('#frm').submit();
		});
		
		$('.pn').click(function(){
			var str = $(this).html();
			if(str == 'PRE'){
				$('#nowPage').val('${PAGE.startPage - 1}');
			} else if(str == 'NEXT'){
				$('#nowPage').val('${PAGE.endPage + 1}');
			} else{
				return;
			}
			$('#frm').attr('action', '/cookie/board/board.cnu');
			$('#frm').submit();
		});
		
		// 게시글 상세보기 이벤트 처리
		$('.content').click(function(){
			// 할일
			// 글 번호 알아낸다.
			var sno = $(this).attr('id');
			$('#bno').val(sno);
			$('#nowPage').val('${PAGE.nowPage}');
			$('#frm').attr('action', '/cookie/board/boardDetail.cnu');
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form method="post" id="frm">
		<input type="hidden" name="nowPage" id="nowPage">
		<input type="hidden" name="bno" id="bno">
	</form>
	
    <div class="w3-content mxw">
        <div class="w3-center w3-col w3-pink">
            <h2>BlackPink Board</h2>
        </div>
        <div class="w3-row w3-margin-bottom">
            <div class="w3-col w3-margin-bottom" style="margin-top: 5px;">
                <div class="w3-button w3-green w3-left" id="home">HOME</div>
     <c:if test="${not empty SID}">
                <div class="w3-button w3-red w3-right" id="write">글쓰기</div>
     </c:if>
     <c:if test="${empty SID}">
                <div class="w3-button w3-blue w3-right" id="login">LOGIN</div>
     </c:if>
            </div>
            <div class="w3-col w3-hoverable">
              <div>
                <div class="w3-col w3-deep-purple w3-center w3-border-bottom pd2">
                  <div class="w3-col m2 s12 w3-border-right">글번호</div>
                  <div class="w3-col m4 s12 w3-border-right">제목</div>
                  <div class="w3-col m2 s12 w3-border-right">작성자</div>
                  <div class="w3-col m3 s12 w3-border-right">작성일</div>
                  <div class="w3-col m1 s12">조회수</div>
                </div>
              </div>
              <%-- 게시글 리스트 출력 --%>
              <c:forEach var="data" items="${LIST}">
	              <div class="w3-col w3-hover-pink ]w3-text-gray w3-center w3-border-bottom content pd2" id="${data.bno}">
	                <div class="w3-col m2 s12 w3-border-right">${data.bno}</div>
	                <div class="w3-col m4 s12 w3-border-right">${data.title}</div>
	                <div class="w3-col m2 s12 w3-border-right">${data.id}</div>
	                <div class="w3-col m3 s12 w3-border-right">${data.sdate}</div>
	                <div class="w3-col m1 s12">${data.click}</div>
	              </div>
              </c:forEach>
              
            </div>
        </div>
        <div class="w3-center w3-margin-top">
			<div class="w3-bar w3-border">
				<c:if test="${PAGE.startPage eq 1}">
					<span class="w3-bar-item w3-light-gray">PRE</span>
				</c:if>
				<c:if test="${PAGE.startPage ne 1}">
					<span class="w3-bar-item w3-button w3-hover-blue pn">PRE</span>
				</c:if>
				<c:forEach var="pageNo" begin="${PAGE.startPage}" end="${PAGE.endPage}">
					<span class="w3-bar-item w3-border-left w3-button w3-hover-blue pbtn">${pageNo}</span>
				</c:forEach>
				<c:if test="${PAGE.endPage ne PAGE.totalPage}">
					<span class="w3-bar-item w3-border-left w3-button w3-hover-blue pn">NEXT</span>
				</c:if>
				<c:if test="${PAGE.endPage eq PAGE.totalPage}">
					<span class="w3-bar-item w3-border-left w3-light-gray">NEXT</span>
				</c:if>
				
			</div>
		</div>
      </div>
</body>
</html>