package org.cnu.cookie.controller.member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.CnuController;

public class Login implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		// 반환값 변수 만들고
		String view = "member/login";
		
		// 로그인 검사하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid != null) {
			req.setAttribute("isRedirect", true);
		/*
			CnuDispatch.service() 함수 내에서 
				리다이렉트할 것인지 포워드 시킬것인지 아니면 비동기 통신인지를 분기해서 처리하고 있으므로
				여기서 전달받은 req객체의 속성을 변경해주면 된다.
		 */
			view = "/cookie/main.cnu";
		}
		
		// 뷰 반환해주고
		return view;
	}

}
