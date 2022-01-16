package org.cnu.cookie.controller.member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;

public class Signout implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		req.setAttribute("isRedirect", true);
		String view = "/cookie/main.cnu";
		
		// 로그인 체크하고
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("SID");
		if(id == null) {
			return view;
		}
		
		// 파라미터 받고
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		String pw = req.getParameter("pw");
		MemberDao mDao = new MemberDao();
		int cnt = mDao.delInfo(mno, pw);
		
		if(cnt != 1) {
			req.setAttribute("isRedirect", false);
			req.setAttribute("MNO", mno);
			req.setAttribute("MSG", "탈퇴처리에 실패했습니다.");
			req.setAttribute("VIEW", "/cookie/member/info.cnu");
			view = "member/redirect";
		} else {
			// 로그아웃처리하고
			session.removeAttribute("SID");
		}
		return view;
	}

}
