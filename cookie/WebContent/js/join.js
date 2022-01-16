$(document).ready(function(){
	$('#rbtn').click(function(){
		// 리셋버튼이 클릭된 경우
		// $('#frm').reset();
		
		$('#repwmsg').css('display', 'none');
		
		document.frm.reset();
	});
	
	// 홈버튼 클릭이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/cookie/main.cnu');
	});
	
	$('#idck').click(function(){
		// 할일
		// 1. 아이디 읽고
		var sid = $('#id').val();
		// 2. 아이디 유효성검사하고
		if(!sid) {
			alert('아이디를 입력하세요!');
			return;
		}
		alert('### id : ' + sid);
		// 3. 서버에 아이디보내고 검사하고 ==> 비동기 통신 처리
		$.ajax({
			url: '/cookie/member/idCheck.cnu',
			type: 'post',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(obj){
				if(obj.msg == 'OK'){
					// 4-1. 사용가능한 아이디인 경우 처리
					$('#idmsg').removeClass('w3-text-red').addClass('w3-text-green').html('* 사용가능한 아이디입니다.');
				} else {
					// 4-2. 사용 불가능한 아이디인 겨우 처리
					$('#idmsg').removeClass('w3-text-green').addClass('w3-text-red').html('# 사용 불가능한 아이디입니다. #');
				}
				$('#idmsg').css('display', 'block');
			},
			error: function(){
				alert('# 통신 실패 #');
			}
		});
	});
	
	// 성별 체크 이벤트
	$('[name="gen"]').change(function(){
		var st = $('#avtfr').css('display');
		
		var sgen = $(this).val();
		
		function avtFunc(){
			if(sgen == 'M'){
				$('#favt').css('display', 'none');
				$('#mavt').css('display', 'block');
				$('#avtfr').stop().slideDown(500);
			} else {
				$('#mavt').css('display', 'none');
				$('#favt').css('display', 'block');
				$('#avtfr').stop().slideDown(500);
			}
		}
		
		if(st != 'none'){
			$('#avtfr').stop().slideUp(500, avtFunc);
		} else {
			avtFunc();
		}
	});
	
	
	// 비밀번호 체크 이벤트 처리
	$('#repw').keyup(function(){
		// 비밀번호를 읽고
		var pw = $('#pw').val();
		// 입력된 확인비밀번호를 읽고
		var repw = $('#repw').val();
		// 두개를 비교해서 메세지 출력한다.
		if(pw == repw) {
			// 같은 경우
			$('#mail').focus();
			$('#repwmsg').removeClass('w3-text-red').addClass('w3-text-green').html('* 비밀번호가 일치합니다.');
			$('#repwmsg').css('display', 'block');
		} else {
			// 다른 경우
			$('#repwmsg').removeClass('w3-text-green').addClass('w3-text-red').html('# 비밀번호가 일치하지 않습니다.');
			$('#repwmsg').css('display', 'block');
			
		}
	});
	
	// 회원가입버튼 이벤트 처리
	$('#jbtn').click(function(){
		// 할일
		// 입력된 데이터를 읽어온다. ==> 데이터 유효성 검사
		var name = $('#name').val();
		var id= $('#id').val();
		var pw = $('#pw').val();
		var repw = $('#repw').val();
		var mail = $('#mail').val();
		var tel = $('#tel').val();
		var gen = $('[name="gen"]:checked').val();
		var ano = $('[name="ano"]:checked').val();
		
		// 입력되지 않은 데이터가 있으면 뷰로 다시 돌려보낸다.
		if(!(name && id && pw && repw && mail && tel && gen && ano && (pw == repw))){
			// 이 경우는 입력사항이 누락되었거나 비밀번호가 다른 경우이므로 다시 돌려보낸다.
			return;
		}
		
		// 모두입력했으면 서버로 데이터를 보낸다.
		$('#frm').attr('action', '/cookie/member/joinProc.cnu');
		$('#frm').submit();
	});
	
	/* 회원 정보수정 이벤트 처리 */
	$('#ebtn').click(function(){
		var cmail = $('#cmail').text();
		var ctel = $('#ctel').text();
		var cano = $('#cano').text();
		
		var pw = $('#pw').val();
		var repw = $('#repw').val();
		var mail = $('#mail').val();
		var tel = $('#tel').val();
		var ano = $('[name="ano"]:checked').val();
		
		if(pw){
			if(pw == repw){
				$('#repw').prop('disabled', 'true');
			} else {
				$('#repw').val('').focus();
				return;
			}
		} else {
			$('#pw').prop('disabled', 'true');
			$('#repw').prop('disabled', 'true');
		}
		
		if(cmail == mail){
			$('#mail').prop('disabled', 'true');
		}
		
		if(ctel == tel){
			$('#tel').prop('disabled', 'true');
		}
		
		if(cano == ano){
			$('[name="ano"]:checked').prop('checked', '');
		}
		
		if(pw || (cmail != mail) || (ctel != tel) || (cano != ano)) {
			$('#frm').submit();
		}
	});
});