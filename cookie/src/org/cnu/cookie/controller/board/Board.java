package org.cnu.cookie.controller.board;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.CnuController;
import org.cnu.cookie.dao.BoardDao;
import org.cnu.cookie.util.PageUtil;
import org.cnu.cookie.vo.BoardVO;

public class Board implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "board/boardList";
		
		// 파라미터 가져오고
		String spage = req.getParameter("nowPage");
		int nowPage = 1;
		if(spage == null) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(spage);
		}
		BoardDao bDao = new BoardDao();
		// 총 개시글 갯수가져오고
		int total = bDao.getTotal();
		// PageUtil 준비하고
		PageUtil page = new PageUtil(nowPage, total);
		// 게시글 목록 가져오고
		ArrayList<BoardVO> list = bDao.getList(page);
		
		
		// 데이터 심고
		req.setAttribute("PAGE", page);
		req.setAttribute("LIST", list);
		
		// 뷰 부르고
		return view;
	}

}
