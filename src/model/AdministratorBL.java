package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministratorBL {
	public Administrator login(String id) {
		Administrator admin = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				AdministratorDAO dao = new AdministratorDAO(con);
				admin = dao.findAdministratorByKey(id);

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

		return admin;
	}
}