package org.cnu.cookie.controller.member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;

public class IdCheck implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		// 뷰 전환방식 설정 : 비동기 통신으로 설정
		req.setAttribute("isRedirect", null);
		
		// 세션검사하고
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		
		if(sid != null) {
			// 로그인 되어있는 상태이므로 메인페이지로 리다이렉트 시킨다.
			req.setAttribute("isRedirect", true);
			return "/cookie/main.cnu";
		}
		
		// 파라미터 받고
		String id = req.getParameter("id");
		
		MemberDao mDao = new MemberDao();
		int cnt = mDao.idCount(id);
		
		StringBuffer buff = new StringBuffer("{");
		buff.append("\"msg\": ");
		if(cnt == 0) {
			buff.append("\"OK\"");
		} else {
			buff.append("\"NO\"");
		}
		buff.append("}");
		
		return buff.toString();
	}

}
