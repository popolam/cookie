package org.cnu.cookie.controller.member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;

public class EditProc implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "member/redirect";
		
		// 세션 검사하고
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/cookie/member/login.cnu";
		}
		
		// 파라미터 받고
		String pw = req.getParameter("pw");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String sano = req.getParameter("ano");
		int ano = 0;
		if(sano != null) {
			ano = Integer.parseInt(sano);
		}
		
		StringBuffer buff = new StringBuffer();
		
		if(pw != null) {
			buff.append("	pw = '" + pw + "', ");
		}
		
		if(mail != null) {
			buff.append("	mail = '" + mail + "', ");
		}
		
		if(tel != null) {
			buff.append("	tel = '" + tel +  "', ");
		}
		
		if(ano != 0) {
			buff.append("	ano = " + ano + ", ");
		}
		
		String tmp = buff.substring(0, buff.lastIndexOf(","));
		
		MemberDao mDao = new MemberDao();
		
		int cnt = mDao.editInfo(tmp, sid);
		
		if(cnt == 1) {
			req.setAttribute("MSG", "회원 정보가 수정되었습니다.");
		} else {
			req.setAttribute("MSG", "회원 정보 수정에 실패했습니다.");
		}
		req.setAttribute("VIEW", "/cookie/member/info.cnu");
		
		return view;
	}

}
