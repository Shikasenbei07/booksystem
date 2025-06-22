package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StopBookBL {
	public boolean StopBook(String isbn)  {
		//StopBookTO stopBookTO = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		boolean successflg = false;
		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				StopBookDAO dao = new StopBookDAO(con);
				successflg = dao.findStopBookByKey(isbn);

				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return successflg;
	}
	
}
