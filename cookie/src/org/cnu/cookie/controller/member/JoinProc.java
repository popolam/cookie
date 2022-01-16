package org.cnu.cookie.controller.member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.MemberDao;
import org.cnu.cookie.vo.MemberVO;

public class JoinProc implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		// 뷰 전환방식 설정하고
		req.setAttribute("isRedirect", true);
		// 기본 뷰 설정하고 - 회원가입에 성공할 경우 뷰를 정해주자.
		String view = "/cookie/main.cnu";
		
		// 세션 검사하고
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		
		if(sid != null) {
			// 로그인 되어있는 상태이므로 메인페이지로 리다이렉트 시킨다.
			return "/cookie/main.cnu";
		}
		
		// 파라미터 받고
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String gen = req.getParameter("gen");
		String sano = req.getParameter("ano");
		int ano = Integer.parseInt(sano);
		
		// VO에 담고
		MemberVO mVO = new MemberVO();
		mVO.setName(name);
		mVO.setId(id);
		mVO.setPw(pw);
		mVO.setMail(mail);
		mVO.setTel(tel);
		mVO.setGen(gen);
		mVO.setAno(ano);
		
		// 데이터 베이스 작업하고 결과 받고
		MemberDao mDao = new MemberDao();
		int cnt = mDao.addMember(mVO);
		
		// 작업 결과에 따라서 처리하고
		if(cnt == 1) {
			// 회원가입에 성공한 경우이므로 로그인처리를 해주고 메인페이지로 보낸다.
			session.setAttribute("SID", id);
			// 뷰는 처음 정한 뷰를사용한다.
		} else {
			// 이 경우는 회원가입에 실패한 경우이므로 다시 회원가입 페이지로 보낸다.
			view = "/cookie/member/join.cnu";
		}
		
		return view;
	}

}
