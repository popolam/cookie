package org.cnu.cookie.controller;

import javax.servlet.*;
import javax.servlet.http.*;

public interface CnuController {
	String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException;
}
