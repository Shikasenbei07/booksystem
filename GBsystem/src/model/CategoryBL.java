package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBL {
	public ArrayList<Category> getAllCategory() {
		ArrayList<Category> list = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				CategoryDAO dao = new CategoryDAO(con);
				list = dao.getAllCategory();

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

		return list;
	}
}
