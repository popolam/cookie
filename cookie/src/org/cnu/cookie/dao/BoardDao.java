package org.cnu.cookie.dao;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import org.cnu.cookie.db.*;
import org.cnu.cookie.sql.*;
import org.cnu.cookie.vo.*;

import com.oreilly.servlet.MultipartRequest;

import org.cnu.cookie.util.*;

/**
 * 이 클래스는 게시판관련 데이터베이스 작업을 처리하기 위한 클래스
 * @author	전은석
 * @see		org.cnu.cookie.sql.BoardSQL
 */
public class BoardDao {
	private CnuDBCP db;
	
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private BoardSQL bSQL;
	
	public BoardDao() {
		/*
		 * 이 클래스가 객체가 만들어질때 필요한 것들은 객체로 만들어 준비해준다.
		 */
		db = new CnuDBCP();
		bSQL = new BoardSQL();
	}
	
	/**
	 * 게시글 총 갯수 조회 데이터베이스 작업 전담 처리함수
	 */
	public int getTotal() {
		int cnt = 0;
		
		// Connection
		con = db.getCon();
		// 질의명령가져오고
		String sql = bSQL.getSQL(bSQL.ALL_COUNT);
		// 명령전달도구 가져오고
		stmt = db.getSTMT(con);
		try {
			// 질의명령 보내고 결과받고
			rs = stmt.executeQuery(sql);
			// 결과 꺼내고
			rs.next(); // 작업줄 내리고
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원반환하고
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		// 총갯수반환하고
		return cnt;
	}
	
	/**
	 * 게시글 리스트 조회 데이터베이스작업 전담 처리함수
	 */
	public ArrayList<BoardVO> getList(PageUtil page){
		// 반환값 변수
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		// Connection
		con = db.getCon();
		// 질의명령
		String sql = bSQL.getSQL(bSQL.SEL_ALL);
		// 명령전달도구 준비하고
		pstmt = db.getPSTMT(con, sql);
		
		try{
			// 질의명령 완성하고
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			// 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				// 반복해서 VO 만들고
				BoardVO bVO = new BoardVO();
				// 결과 꺼내서 VO에 담고
				int bno = rs.getInt("bno");
				int mno = rs.getInt("mno");
				String id = rs.getString("id");
				String title = rs.getString("title");
				Date wdate = rs.getDate("wdate");
				Time wtime = rs.getTime("wdate");
				int click = rs.getInt("click");
				
				bVO.setBno(bno);
				bVO.setMno(mno);
				bVO.setId(id);
				bVO.setTitle(title);
				bVO.setWdate(wdate);
				bVO.setWtime(wtime);
				bVO.setClick(click);
				
				// VO list에 담고
				list.add(bVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원반환하고
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		// list 반환하고
		return list;
	}

	/**
	 * 게시글 입력 데이터베이스 작업 전담 처리함수
	 */
	public int addBoard(HashMap<String, String> map) {
		int cnt = 0;
		
		// 할일
		// 1. 커넥션 가져오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = bSQL.getSQL(bSQL.ADD_BRD);
		// 3. pstmt 가져오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("title"));
			pstmt.setString(3, map.get("body"));
			// 5. 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	/**
	 * 파일 정보 입력 데이터베이스 작업 전담 처리함수
	 */
	public boolean addImgInfo(MultipartRequest multi, String id) {
		boolean bool = false;
		
		// 업로드된 파일 갯수 변수
		int cnt = 0;
		// 데이터베이스에 추가된 행 수
		int result = 0;
		// 할일
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = bSQL.getSQL(bSQL.ADD_BRDIMG);
		// 3. pstmt 가져오고
		pstmt = db.getPSTMT(con, sql);
			
		/*
			파일 정보 등록하자
			비록 파일이 업로드는 완료되었지만
			이것은 단순히 서버의 특정 폴더에 파일을 저장한 것 뿐이다.
			이 파일이 누구것인지? 등등의 정보를 전혀 알지 못한다.
			업로드된 파일의 정보는 데이터베이스에서 기록해 놓아야
			그파일의 실제 주인을 찾을 수 있게 된다.
		
			참고	mulit가 가진 주요 함수
					자신이 업로드한 파일의 정보를 알려주는 함수
			getFile("키값")		업로드된 파일의 정보를 알려준다.
			getFilesystemName("키값");
					업로드된 파일의 실제 저장된 이름을 알려준다.
			getOriginalFileName("키값");
					업로드된 파일의 원래 이름을 알려준다.
			getFileNames()
					업로드된 파일의 모든 키값을 알려준다.
		*/
		
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			// 업로드된 파일의 키(name)값을 알아내고
			String key = (String) en.nextElement();
			// 해당 키값을 가지고 있는 파일 이름을 알아낸다.
			String oriname = multi.getOriginalFileName(key);
			if(oriname == null || oriname.length() == 0) {
				// 이 경우는 파일을 선택하지 않은 경우이므로 다음 회차로 진행한다.
				continue;
			}
			++cnt;
			// 실제 저장된 이름을 알아낸다.
			String savename = multi.getFilesystemName(key);
			// 파일 사이즈 알아내고
			File file = multi.getFile(key);
			long len = file.length();
			
			try{
				// 5. 질의명령 완성
				pstmt.setString(1, id);
				pstmt.setString(2, oriname);
				pstmt.setString(3, savename);
				pstmt.setLong(4, len);
				// 6. 보내고 결과받고
				result += pstmt.executeUpdate(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		db.close(pstmt);
		db.close(con);
		// 7. 결과 비교해서 반환해주고
		if(cnt == result) {
			bool = true;
		}
		
		return bool;
	}
	
	/**
	 * 게시물 상세보기 데이터베이스 조회 전담 처리함수
	 */
	public BoardVO getDetail(int bno) {
		BoardVO bVO = new BoardVO();
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_DETAIL);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bVO.setBno(rs.getInt("bno"));
				bVO.setMno(rs.getInt("mno"));
				bVO.setId(rs.getString("id"));
				bVO.setTitle(rs.getString("title"));
				bVO.setBody(rs.getString("body"));
				bVO.setWdate(rs.getDate("wdate"));
				bVO.setWtime(rs.getTime("wdate"));
				bVO.setClick(rs.getInt("click") + 1);
				
				if(rs.getString("oriname") != null) {
					FileVO fVO = new FileVO();
					fVO.setFno(rs.getInt("fno"));
					fVO.setOriname(rs.getString("oriname"));
					fVO.setSavename(rs.getString("savename"));
					
					list.add(fVO);
				}
			}
			
			bVO.setFile(list);
			
			db.close(rs);
			db.close(pstmt);
			
			// 모두 가져오면 클릭수를 올려주자.
			sql = bSQL.getSQL(bSQL.ADD_CLICK);
			pstmt = db.getPSTMT(con, sql);
			
			pstmt.setInt(1, bno);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt != 1) {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return bVO;
	}
	
	/**
	 * 게시글삭제 데이터베이스작업 전담 처리함수
	 */
	public int delBoard(int bno, String id) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.DEL_BRD);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			pstmt.setString(2, id);
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
}
