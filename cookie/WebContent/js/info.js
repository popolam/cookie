$(document).ready(function(){
	$('#hbtn').click(function(){
		$(location).attr('href', '/cookie/');
	});
	
	$('#dbtn').click(function(){
		$('#leave').css('display', 'block');
	});
	
	$('#del').click(function(){
		var sno = $('#mno2').val();
		var spw = $('#pw').val();
		if(!(sno && spw)){
			alert('필수 입력 사항을 확인하세요!');
			return;
		} else {
			$('#dfrm').submit();
		}
	});
	
	$('#iebtn').click(function(){
		$('#dfrm').attr('action', '/cookie/member/edit.cnu').submit();
	});
});