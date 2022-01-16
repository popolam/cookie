package org.cnu.cookie.controller.board;

//import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnu.cookie.controller.*;
import org.cnu.cookie.dao.*;
import org.cnu.cookie.vo.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BoardWriteProc implements CnuController {

	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		req.setAttribute("isRedirect", true);
		String view = "/cookie/board/board.cnu";
		
		req.setAttribute("isRedirect", true);
		// 할일
		// 세션검사
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/cookie/member/login.cnu";
		}
		
		/*
		지금까지는 parameter  방식으로 데이터가 오므로.....
			req.getParameter(); 이면 되었는데...
	
		지금은 파라메터 방식이 아닌 스트림 방식으로 데이터가 오기에
		받는 방식도 스트림 방식으로 받아주어야 한다.
	
		요사이는 스트림 방식으로 처리해주는 라이브러리가 많이 
		보급되어 있다.
		우리가 사용할 cos.jar에서는 
			MultipartRequest  라는 클래스가 그 역활을 담당한다.
		사용법(new 시키는 법)
			new MultiparRequest(req, 저장경로, 업로드할 파일의 최대크기,
				엔코딩방식, 파일이름 충돌날 경우 정책);
	
		이 클래스를 new 시키면
		1.	byte[]로 날라온 데이터를 우리가 사용하기 편하도록
			파라메터 방식으로 변환시켜준다.
		2.	이 클래스만의 특징으로 파일업로드가 완성된다.
			즉, 서버의 지정한 디렉토리에 파일이 저장된다.
	
		2번 문제 때문에 가장 중요한 기능이 저장 경로를 지정하는 것이다.
		저장 경로를 만드는 방법
		==>		저장된 파일의 사용 목적에 따라서 경로가 달라진다.
			1.	다운로드만을 위해서 저장한다면....
					아무데나 저장해도 된다.
					예>		String path = "D:\\upload";
			2.	화면에 사용하기 위해서 저장한다면....
					반드시 리얼패스를 찾아서 저장해야한다.
					예>		String path = 
					req.getSession().getServletContext().getRealPath("imgs");
	
		나머지 파라메터 설명
		3.	업로드할 파일의 최대 크기를 바이트 단위로 지정한다.
			예>		int	size = 1024 * 1024 * 1024 * 10;		10G
		4.	엔코딩 방식 지정이란?
			파일의 이름이 한글인 경우 파일의 이름이 깨질 수 있다.
			이런 경우를 대비해서 한글 파일의 이름을 복구할 
			엔코딩 방식을 지정하는 것이다.
			예>		encoding = "utf-8";
		5.	이름이 중복되는 경우 해결하는 정책
			이것은 아직은 한가지 방법만 제공되고 있다.
			파일 이름뒤에 (1), (2) 등의 숫자를 이용해서 변경하는 방식
				DefaultFileRenamePolicy p = new DefaultFileRenamePolicy();
	
		지금부터 스트림 방식으로 날라온 데이터를
		파라메터 방식으로 변환시키자.
		 */
		
		// 저장경로 가져오고...
		String path = req.getSession().getServletContext().getRealPath("img");

		// 스트림방식으로 전송된 데이터를 파라미터 방식으로 변화시킨다.
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(req, path + "/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
			/*
				이제 이 작업이 에러없이 완료되면 
				byte[]에 파라메터로 바뀌고
				서버에 업로드가 완료된다.
			*/		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/*
				일반 컨트롤러보다 한가지 작업을 더해야 하는데...
				byte[]을 파라메토로 바꾸는 작업이다.
			
				파라메터 받고
					★★★★
					스트림 방식으로 날라온 데이터를 파라메터 바꾸어서
					저장하고 있는데....
					저장된곳은 req가 아니고 multi가 저장한다.
				예		제목을 받고자 하면....파라메터 방식에서는...
						String title = req.getParameter("title");
		*/
		
		// 데이터 꺼내고
		String title = multi.getParameter("title");
		String body = multi.getParameter("body");
		
		/*
		 * VO를 사용해서 처리해도 되지만 여기서는 Map을 사용해서 처리하는 것으로 한다.
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", sid);
		map.put("title", title);
		map.put("body", body);
		// 디비작업하고
		BoardDao bDao = new BoardDao();
		int cnt = bDao.addBoard(map);
		
		if(cnt != 1) {
			return view;
		}
		
		// cnt == 1 인 경우...
		// 데이터베이스에 이미지 데이터를 추가한다.
		
		boolean bool = bDao.addImgInfo(multi, sid);
		if(!bool) {
			view = "/cookie/board/errorMsg.cnu";
		}
		
		return view;
	}

}
