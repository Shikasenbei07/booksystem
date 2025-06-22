package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountBL {
	public Account login(String email) {
		Account ac = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				AccountDAO dao = new AccountDAO(con);
				ac = dao.findAccountByKey(email);

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

		return ac;
	}

	public boolean insertAccount(Account ac) {
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				AccountDAO dao = new AccountDAO(con);
				result = dao.insertAccount(ac);

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

		return  result;
	}
}