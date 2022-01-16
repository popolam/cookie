package org.cnu.cookie.controller.board;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.CnuController;

public class ErrorMsg implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		String view = "board/errorMsg";
		return view;
	}

}
