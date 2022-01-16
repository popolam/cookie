package org.cnu.cookie.db;
/**
 * 이 클래스는 커넥션 풀에 있는 커넥션을 이용해서
 * 데이터베이스 작업을 할 유틸리티적인 클래스
 */
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class CnuDBCP {
	// 커넥션풀을 관리한 클래스 변수
	public DataSource ds;
	
	public CnuDBCP() {
		/*
			이 클래스를 new 시키면
			context.xml 파일에 등록된 자원을 가지고 오도록 한다.
			이처럼 context.xml 과 같은 파일에 등록된 자원을 가지고 오는 기법을
			JNDI(Java Naming and Directory Interface) 기법이라고 한다.
		 */
		try {
			// 1. context.xml 파일에 등록된 자원을 알아낸다.
			InitialContext context = new InitialContext();
			// 2. 그중에서 필요한 자원을 얻어낸다.
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
			/*
				찾을 이름을 정하는 규칙
					java:/comp/env/찾을이름
				위 작업이 성공하면 커넥션 풀을 찾았고 
				데이터베이스를 사용할 수 있게 된다.
			 */
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
		필요한 순간에 접속이 필요하다.
		접속은 직접하는 것이 아니고
		커넥션 풀이 가지고 있는 커넥션을 하나 빌려와서 사용하는 것이다.
	 */
	public Connection getCon() {
		Connection con = null;
		try {
			// 커넥션 풀을 관리하는 DataSource에서 하나를 꺼내온다.
			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public Statement getSTMT(Connection con) {
		Statement stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public void close(Object o) {
		try {
			if(o instanceof Connection) {
				((Connection) o).close();
			} else if(o instanceof Statement) {
				((Statement) o).close();
			} else if(o instanceof PreparedStatement) {
				((PreparedStatement) o).close();
			} else if(o instanceof ResultSet) {
				((ResultSet) o).close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
