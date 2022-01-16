package org.cnu.cookie.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CnuMain implements CnuController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		return "main";
	}

}
