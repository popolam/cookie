package org.cnu.cookie.controller.board;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.BoardDao;

public class Delete implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		req.setAttribute("isRedirect", true);
		/*
			여기서는 GET 방식으로 파라미터를 전달하면서 리다이렉트시키기로 한다.
		 */
		
		String view = "/cookie/board/board.cnu";
		
		// 세션 검사하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/cookie/member/login.cnu";
		}
		
		// 파라미터 받고
		String sno = req.getParameter("bno");
		int bno = Integer.parseInt(sno);
		String nowPage = req.getParameter("nowPage");
		
		// 글 삭제처리하고
		BoardDao bDao = new BoardDao();
		int cnt = bDao.delBoard(bno, sid);
		if(cnt != 1) {
			view = "/cookie/board/boardDetail.cnu?nowPage=" + nowPage + "&bno=" + bno;
		} else {
			view = view + "?nowPage=" + nowPage;
		}
		
		return view;
	}

}
