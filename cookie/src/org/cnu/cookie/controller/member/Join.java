package org.cnu.cookie.controller.member;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;
import org.cnu.cookie.vo.MemberVO;

public class Join implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "member/join";
		
		// 세션 검사하고
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		
		if(sid != null) {
			// 로그인 되어있는 상태이므로 메인페이지로 리다이렉트 시킨다.
			req.setAttribute("isRedirect", true);
			return "/cookie/main.cnu";
		}
		
		// 아바타 리스트 가져오고
		MemberDao mDao = new MemberDao();
		ArrayList<MemberVO> list = mDao.avtList();
		
		// 데이터 심고
		req.setAttribute("LIST", list);
		
		// 뷰 반환하고
		return view;
	}

}
