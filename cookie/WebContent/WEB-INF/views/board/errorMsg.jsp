<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Insert Error</title>
<link rel="stylesheet" href="/cookie/css/w3.css">
<link rel="stylesheet" href="/cookie/css/user.css">
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn').click(function(){
			location.href = '/cookie/board/board.cnu';
		});
	});
</script>
</head>
<body>
	<h2 class="w3-center w3-text-red">게시글은 정상적으로 입력되었으나</h2>
	<h2 class="w3-center w3-text-red">파일 정보가 정상적으로 입력되지 않았습니다!</h2>
	<h2 class="w3-center w3-text-red">자세한 사항은 <b> 관리자 </b>에게 문의하세요!</h2>
	<div class="w3-center">
		<div class="w3-button w3-blue w-200 inblock" id="btn">게시판 가기</div>
	</div>
</body>
</html>