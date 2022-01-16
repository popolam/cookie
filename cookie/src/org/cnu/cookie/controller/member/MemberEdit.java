package org.cnu.cookie.controller.member;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;
import org.cnu.cookie.vo.MemberVO;

public class MemberEdit implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "member/edit";
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			// 로그인 안한 경우
			req.setAttribute("isRedirect", true);
			view = "/cookie/member/login.cnu";
			return view;
		}
		// 파라미터 받고
		String id = req.getParameter("id");
		if(!sid.equals(id)) {
			// 로그인 아이디와 수정 아이디가 다른경우
			req.setAttribute("isRedirect", true);
			view = "/cookie/member/info.cnu";
			return view;
		}
		
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getInfo(id);
		ArrayList<MemberVO> list = mDao.avtList();
		
		// 데이터 심고
		req.setAttribute("DATA", mVO);
		req.setAttribute("LIST", list);
		return view;
	}

}
