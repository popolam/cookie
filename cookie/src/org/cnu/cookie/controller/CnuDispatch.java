package org.cnu.cookie.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/*
 * 이 클래스는 .cnu로 오는 모든 요청을 받아서 
 * 요청에 맞는 요청처리 클래스를 실행할(dispatch) 서블릿 클래스이다. 
 */
@WebServlet("*.cnu")
public class CnuDispatch extends HttpServlet {
	private HashMap<String, CnuController> map;
	
	public void init(ServletConfig conf) throws ServletException {
		Properties prop = new Properties();
		FileInputStream fin = null;
		String path = this.getClass().getResource("/").getPath() + "resources/cnu.properties";
		try {
			fin = new FileInputStream(path);
			prop.load(fin);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch(Exception e) {}
		}
		
		map = new HashMap<String, CnuController>();
		
		Set set = prop.keySet();
		Iterator itor = set.iterator();
		while(itor.hasNext()) {
			String key = (String) itor.next();
			String sClass = (String) prop.get(key);
			CnuController cnu = null;
			try{
				cnu = (CnuController) Class.forName(sClass).newInstance();
				map.put(key, cnu);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", false);
		
		// 1. 전체 요청 내용 알아내고
		String  full = req.getRequestURI();
		// 2. 도메인을 찾아내고
		String domain = req.getContextPath();
		// 3. 실제 요청내용 알아내고
		String real = full.substring(domain.length());
		// 4. map에 집어넣고
		CnuController cnu = map.get(real);
		
		String view = cnu.exec(req, resp);
		if((Boolean)req.getAttribute("isRedirect") == null) {
			// 비동기 통신 응답인 경우
			PrintWriter pw = resp.getWriter();
			pw.print(view);
		} else if((Boolean)req.getAttribute("isRedirect")) {
			// 리다이렉트방식인 경우
			resp.sendRedirect(view);
		} else {
			// forward 인 경우
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
			rd.forward(req, resp);
		}
	}
	
	/*
	 * 요청방식에 따라 실행되는 함수가 있지만
	 * service함수가 작성되면 실행되지 않는다.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		/*
			GET 방식으로 요청할때 실행되는 함수
		 */
		System.out.println("######### Get request ##########");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		/*
			POST 방식으로 요청할때 실행되는 함수
		 */
		System.out.println("######### Post request ##########");
	}
}
