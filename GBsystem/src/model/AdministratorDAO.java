package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDAO implements GBookDBInfo {
	private Connection con = null;

	public AdministratorDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Administrator findAdministratorByKey(String id) {
		Administrator admin = null;
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from administrator"
				+ " where id = ?";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				admin = new Administrator(
						rs.getString("id"),
						rs.getString("password")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return admin;
	}
}
