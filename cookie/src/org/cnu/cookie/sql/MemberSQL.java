package org.cnu.cookie.sql;

public class MemberSQL {
	public final int SEL_LOGIN 		=	1001;
	public final int SEL_INFO 		=	1002;
	public final int SEL_AVT_LIST 	=	1003;
	public final int ID_CHECK 		=	1004;
	
	public final int DEL_INFO 		=	2001;
	public final int EDIT_INFO 		=	2002;
	
	public final int ADD_MEMB 		=	3001;

	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_LOGIN:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND id = ? ");
			buff.append("	AND pw = ? ");
			break;
		case ID_CHECK:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case SEL_INFO:
			buff.append("SELECT ");
			buff.append("	mno, name, id, mail, tel, m.ano, savename avatar, joindate, m.gen ");
			buff.append("FROM ");
			buff.append("	member m, avatar a ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND m.ano = a.ano ");
			buff.append("	AND id = ? ");
			break;
		case SEL_AVT_LIST:
			buff.append("SELECT ");
			buff.append("	ano, savename avatar, gen ");
			buff.append("FROM ");
			buff.append("	avatar ");
			break;
		case DEL_INFO:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("	mno = ? ");
			buff.append("	AND pw = ? ");
			break;
		case EDIT_INFO:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	### ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case ADD_MEMB:
			buff.append("INSERT INTO ");
			buff.append("	member(mno, name, id, pw, mail, tel, gen, ano) ");
			buff.append("VALUES( ");
			buff.append("	(SELECT NVL(MAX(mno) + 1, 1001) FROM member), ");
			buff.append("	?, ?, ?, ?, ?, ?, ? ");
			buff.append(") ");
			break;
		}
		return buff.toString();
	}

}
