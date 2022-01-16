package org.cnu.cookie.controller.member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;
import org.cnu.cookie.vo.MemberVO;

public class MemberInfo implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "member/memberInfo";
		
		HttpSession session = req.getSession();
		if(session.getAttribute("SID") == null) {
			req.setAttribute("isRedirect", true);
			view = "/cookie/member/login.cnu";
			return view;
		}
		
		// 데이터 받고
		String msg = req.getParameter("msg");
		if(msg != null) {
			req.setAttribute("MSG", msg);
		}
		
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getInfo((String)session.getAttribute("SID"));
		req.setAttribute("DATA", mVO);
		return view;
	}

}
