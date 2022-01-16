package org.cnu.cookie.controller.board;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;

public class BoardWrite implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		// 뷰 설정
		String view = "board/boardWrite";
		
		// 세션 검사하고
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		
		if(sid == null) {
			// 로그인 안한 상태이므로 로그인페이지로 리다이렉트 시킨다.
			req.setAttribute("isRedirect", true);
			return "/cookie/member/login.cnu";
		}
		
		// 뷰 부르고
		return view;
	}

}
