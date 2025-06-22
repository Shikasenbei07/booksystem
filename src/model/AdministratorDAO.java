package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDAO {
	private Connection con = null;

	public AdministratorDAO(Connection con) {
		this.con = con;
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
		}

		return admin;
	}
}