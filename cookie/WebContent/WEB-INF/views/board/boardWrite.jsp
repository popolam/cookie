<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판글쓰기</title>
<link rel="stylesheet" href="/cookie/css/w3.css">
<script type="text/javascript" src="/cookie/js/jquery-3.6.0.min.js"></script>
<style>
	.dnone {
		display: none;
	}
    .pd3 {
    	padding: 3px;
    }
    .imgFrame {
    	display: inline-block;
    	width: 80px;
    	height: 80px;
    	padding: 5px;
    }
    .img {
    	display: inline-block;
    	width: 70px;
    	height: auto;
    }
</style>
<script type="text/javascript">
	var cnt = 1;
	
	function setNum(){
		cnt = cnt + 1;
		return cnt;
	}
	
	function addTg(e, el){
		var t1 = $('.file').last().attr('id');
		var len = $('.file').length;
		var t2 = $(el).attr('id');
		if( len != 1 && (t1 != t2)){
			var no = $(el).attr('id').substring(4);
			$('#imgfr' + no + ' > img').attr('src', URL.createObjectURL(e.target.files[0]));
			return;
		} 
		
		var tmp = $(el).val();
		var sname = $(el).attr('name');
		sname = sname.substring(4);
		if(!tmp){
			$(el).remove();
			
			$('#imgfr' + sname).remove();
			var num = $('#imgbox > .imgFrame').length;
			if(num == 0){
				$('#imgbox').addClass('dnone');
			}
			return;
		}
		
		var path = URL.createObjectURL(e.target.files[0]);
		cnt = setNum();
		var tag1 = '<input class="file w3-input" type="file" name="file' + cnt + '" id="file' + cnt + '">';
		var tag2 = '<div class="w3-display-container w3-card imgFrame" id="imgfr' + (cnt - 1) + '">' +
					'<img class="w3-display-middle img" src="' + path + '">' +
					'</div>'
					;
		$('#imgbox').removeClass('dnone').append(tag2);
		
		$('#imgfr'+ (cnt -1)).click(function(){
			var tg = $(this);
			var sname = $('#file' + (cnt -1)).val();
			sname = sname.substring(sname.lastIndexOf('\\') + 1);
			sname = sname.substring(sname.lastIndexOf('/') + 1);
			var tmp = confirm('*** [ ' + sname + ' ] 파일 첨부를 취소하겠습니까?');
			if(tmp) removeTg(tg);
			
			var num = $('#imgbox > .imgFrame').length;
			if(num == 0){
				$('#imgbox').addClass('dnone');
			}
		});
		
		$('.box').append(tag1);
		$('.box > .file').last().change(function(evt){
			addTg(evt, this);
		});
	}
	
	function removeTg(el){
		var no = $(el).attr('id');
		no = no.substring(5);
		$('#file' + no).remove();
		$(el).remove();
	}
	
	$(document).ready(function(){
		$('#write').click(function(){
			// 데이터 유효성 체크
			var title = $('#title').val();
			var body = $('#body').val();
			if(!title){
				$('#title').focus();
				return;
			}
			if(!body){
				$('#body').focus();
				return;
			}
			
			// 마지막 file 태그비활성
			$('.file').last().prop('disabled', 'true');
			
			// 데이터 전송
			$('#frm').submit();
		});
		
		$('.file').change(function(e){
			addTg(e, this);
		});
	});
</script>
</head>
<body>
    <div class="w3-content" style="max-width: 1000px; min-width: 350px;">
        <div class="w3-center w3-col w3-card w3-pink">
            <h2>BlackPink Wirte</h2>
        </div>
        <form method="post" action="/cookie/board/boardWriteProc.cnu" id="frm"
        		class="w3-row w3-margin-top" encType="multipart/form-data">
            <div class="w3-row w3-margin-top pd3">
                <div class="w3-card w3-padding w3-round-medium">
                    <input class="w3-input" type="text" name="title" id="title" placeholder="제목 입력" style="width: 960px;">
                </div>
            </div>
            <div class="w3-row w3-card w3-round-medium w3-padding w3-margin-top w3-left-align pd3">
                <div class="box">
	                <input class="file w3-input" type="file" name="file1" id="file1">
                </div>
                <div class="w3-col dnone w3-center w3-margin-top" id="imgbox"></div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-round-medium w3-padding w3-margin-bottom">
                    <textarea class="w3-input w3-border-0" name="body" id="body" 
                    			cols="105" rows="10" placeholder="본문 입력" 
                    			style="resize: none;"></textarea>
                </div>
            </div>
        </form>
        <div class="w3-row">
            <div class=" w3-button w3-red" id="cancel">취소</div>
            <div class=" w3-button w3-green w3-right" id="write">완료</div>
        </div>
      </div>
</body>
</html>