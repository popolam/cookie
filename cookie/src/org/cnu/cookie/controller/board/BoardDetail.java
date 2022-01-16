package org.cnu.cookie.controller.board;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.BoardDao;
import org.cnu.cookie.vo.BoardVO;

public class BoardDetail implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "board/boardDetail";
		
		// 파라미터 가져오고
		String sno = req.getParameter("bno");
		int bno = Integer.parseInt(sno);
		String nowPage = req.getParameter("nowPage");
		
		// 데이터 가져오고
		BoardDao bDao = new BoardDao();
		BoardVO bVO = bDao.getDetail(bno);
		
		if(bVO == null) {
			req.setAttribute("isRedirect", true);
			return "/cookie/board/board.cnu?nowPage=" + nowPage;
		}
		
		// 데이터 심고
		req.setAttribute("DATA", bVO);
		req.setAttribute("nowPage", nowPage);
		// 뷰 부르고
		return view;
	}

}
