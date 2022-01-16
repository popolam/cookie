package org.cnu.cookie.sql;

public class BoardSQL {
	public final int ALL_COUNT	=	1001;
	public final int SEL_ALL	=	1002;
	public final int SEL_DETAIL = 	1003;
	
	public final int DEL_BRD	=	2001;
	public final int ADD_CLICK	=	2002;
	
	public final int ADD_BRD	=	3001;
	public final int ADD_BRDIMG	=	3002;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case ALL_COUNT:
			buff.append("SELECT ");
			buff.append("    count(*) cnt ");
			buff.append("FROM ");
			buff.append("    board ");
			buff.append("WHERE ");
			buff.append("    isshow = 'Y' ");
			break;
		case SEL_ALL:
			buff.append("SELECT ");
			buff.append("    bno, title, mno, id, wdate, click ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rno, bno, title, mno, id, wdate, click ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    bno, title, mno, id, wdate, bclick click ");
			buff.append("                FROM ");
			buff.append("                    member m, board b ");
			buff.append("                WHERE ");
			buff.append("                    b.isshow = 'Y' ");
			buff.append("                    AND bmno = mno ");
			buff.append("                ORDER BY ");
			buff.append("                    wdate DESC ");
			buff.append("            ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");
			break;
		case SEL_DETAIL:
			buff.append("SELECT ");
			buff.append("    bno, mno, fno, title, body, id, wdate, bclick click, oriname, savename ");
			buff.append("FROM ");
			buff.append("    board, member, fileinfo ");
			buff.append("WHERE ");
			buff.append("    bmno = mno ");
			buff.append("    AND bno = fbno(+) ");
			buff.append("    AND bno = ? ");
			break;
		case DEL_BRD:
			buff.append("UPDATE ");
			buff.append("	board ");
			buff.append("SET ");
			buff.append("	isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("	bno = ? ");
			buff.append("	AND bmno = (SELECT mno FROM member WHERE id = ?) ");
			break;
		case ADD_CLICK:
			buff.append("UPDATE ");
			buff.append("	board ");
			buff.append("SET ");
			buff.append("	bclick = bclick + 1 ");
			buff.append("WHERE ");
			buff.append("	bno = ? ");
			break;
		case ADD_BRD:
			buff.append("INSERT INTO ");
			buff.append("	board(bno, bmno, title, body) ");
			buff.append("VALUES( ");
			buff.append("	(SELECT NVL(MAX(bno) + 1, 10001) FROM board), ");
			buff.append("	(SELECT mno FROM member WHERE id = ?), ");
			buff.append("	?, ? ");
			buff.append(")");
			break;
		case ADD_BRDIMG:
			buff.append("INSERT INTO ");
			buff.append("	fileinfo(fno, fbno, dir, oriname, savename, len) ");
			buff.append("VALUES( ");
			buff.append("	(SELECT NVL(MAX(fno) + 1, 1000) FROM fileinfo), ");
			buff.append("	(SELECT MAX(bno) FROM board WHERE bmno = ");
			buff.append("											( ");
			buff.append("												SELECT ");
			buff.append("													mno ");
			buff.append("												FROM ");
			buff.append("													member ");
			buff.append("												WHERE ");
			buff.append("													id = ? ");
			buff.append("											) ");
			buff.append("	), ");
			buff.append("	'/img/upload', ?, ?, ? ");
			buff.append(")");
			break;
		}
		return buff.toString();
	}
}
