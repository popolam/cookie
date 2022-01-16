<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#frm').submit();
	});
</script>
</head>
<body>
	<form method="POST" action="${VIEW}" id="frm" name="frm">
		<input type="hidden" name="mno" id="mno" value="${MNO}">
		<input type="hidden" name="msg" id="msg" value="${MSG}">
	</form>
</body>
</html>