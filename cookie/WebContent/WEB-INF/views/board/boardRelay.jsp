<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Relay</title>
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#frm').submit();
	});
</script>
</head>
<body>
	<form method="post" action="/cls/board/boardDetail.cls" id="frm">
		<input type="hidden" name="bno" value="${DATA.bno}">
		<input type="hidden" name="title" value="${DATA.title}">
		<input type="hidden" name="body" value="${DATA.body}">
		<input type="hidden" name="name" value="${DATA.name}">
		<input type="hidden" name="click" value="${DATA.click}">
		<input type="hidden" name="sdate" value="${DATA.sdate}">
		<input type="hidden" name="nowPage" value="${nowPage}">
<c:if test="${not empty DATA.file}">
	<c:forEach var="data" items="${DATA.file}"> 
		<input type="hidden" name="bino" value="${data.bino}">
		<input type="hidden" name="oriname" value="${data.oriname}">
		<input type="hidden" name="savename" value="${data.savename}">
	</c:forEach>
</c:if>
	</form>
</body>
</html>