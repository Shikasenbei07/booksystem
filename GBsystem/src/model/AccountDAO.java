package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	private Connection con = null;

	public AccountDAO(Connection con) {
		this.con = con;
	}

	public Account findAccountByKey(String email) {
		Account ac = null;
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from account"
				+ " where email = ?";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, email);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				ac = new Account(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("last_name"),
						rs.getString("first_name"),
						rs.getString("prefecture"),
						rs.getString("city"),
						rs.getString("address1"),
						rs.getString("address2"),
						rs.getString("phone")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ac;
	}

	public boolean insertAccount(Account ac) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "insert into account"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, ac.getEmail());
			pStmt.setString(2, ac.getPassword());
			pStmt.setString(3, ac.getLastName());
			pStmt.setString(4, ac.getFirstName());
			pStmt.setString(5, ac.getPrefecture());
			pStmt.setString(6, ac.getCity());
			pStmt.setString(7, ac.getAddress1());
			pStmt.setString(8, ac.getAddress2());
			pStmt.setString(9, ac.getPhone());

			int line = pStmt.executeUpdate();
			if (line == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
