package org.cnu.cookie.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.sql.*;

import org.cnu.cookie.db.*;
import org.cnu.cookie.sql.*;
import org.cnu.cookie.vo.*;

public class MemberDao {
	private CnuDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MemberSQL mSQL;
	
	public MemberDao() {
		db = new CnuDBCP();
		mSQL = new MemberSQL();
	}

	/**
	 * 로그인 처리 데이터베이스작업 전담 처리함수
	 */
	public int getLogin(String id, String pw) {
		// 반환값 변수
		int cnt = 0;
		
		// 1. 커넥션 가져오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN);
		// 3. 명령전달도구(pstmt) 가져오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 결과값 꺼내서 변수에 기억하고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원반환하고
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 7. 반환값 내보내고
		return cnt;
	}
	
	/**
	 * 회원정보 조회 데이터베이스 작업 전담 처리함수
	 */
	public MemberVO getInfo(String id) {
		MemberVO mVO = new MemberVO();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_INFO);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			int mno = rs.getInt("mno");
			String name = rs.getString("name");
			id = rs.getString("id");
			String mail = rs.getString("mail");
			String tel = rs.getString("tel");
			int ano = rs.getInt("ano");
			String avatar = rs.getString("avatar");
			Date jdate = rs.getDate("joindate");
			Time jtime = rs.getTime("joindate");
			String gen = rs.getString("gen");
			
			mVO.setMno(mno);
			mVO.setId(id);
			mVO.setName(name);
			mVO.setMail(mail);
			mVO.setTel(tel);
			mVO.setAno(ano);
			mVO.setAvatar(avatar);
			mVO.setJdate(jdate);
			mVO.setJtime(jtime);
			mVO.setGen(gen);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return mVO;
	}
	
	/**
	 * 회원탈퇴 데이터베이스작업 전담 처리함수
	 */
	public int delInfo(int mno, String pw) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.DEL_INFO);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			pstmt.setString(2, pw);
			
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
	 * 아바타 리스트 조회 데이터베이스 작업 전담 처리함수
	 */
	public ArrayList<MemberVO> avtList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_AVT_LIST);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setAno(rs.getInt("ano"));
				mVO.setAvatar(rs.getString("avatar"));
				mVO.setGen(rs.getString("gen"));
				
				list.add(mVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return list;
	}
	
	/**
	 * 회원 정보 수정 데이터베이스 작업 전담 처리함수
	 */
	public int editInfo(String tmp, String id) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.EDIT_INFO);
		sql = sql.replaceAll("###", tmp);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setString(1, id);
			
		 	cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(stmt);
			db.close(con);
		}
		return cnt;
	}
	
	/**
	 * 아이디 데이터베이스 조회 전담 처리함수
	 */
	public int idCount(String id) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.ID_CHECK);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	/**
	 *  회원 가입 처리 데이터베이스 작업 전담 처리함수
	 */
	public int addMember(MemberVO mVO) {
		// 반환값 변수
		int cnt = 0;
		
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = mSQL.getSQL(mSQL.ADD_MEMB);
		// 명령 전달도구 준비하고( 채워야할 데이터가 있으므로 pstmt 준비 )
		pstmt = db.getPSTMT(con, sql);
		
		try {
			// 데이터채워서 질의명령 완성하고(질의명령에서 작성한 필드의 ? 순서대로 채워주자...)
			pstmt.setString(1, mVO.getName());
			pstmt.setString(2, mVO.getId());
			pstmt.setString(3, mVO.getPw());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getTel());
			pstmt.setString(6, mVO.getGen());
			pstmt.setInt(7, mVO.getAno());
			
			// 질의명령 보내고 변경된 행 수 반환받고
			// 사용함수는 executeUpdate()
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		// 카운트반환해주고
		return cnt;
	}
}
