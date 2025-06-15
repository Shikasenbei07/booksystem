package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO implements GBookDBInfo {
	private Connection con = null;

	public CategoryDAO() {
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

	public ArrayList<Category> getAllCategory() {
		ArrayList<Category> list = new ArrayList<>();
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from category";

		try {
			pStmt = con.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Category c = new Category(
						rs.getInt("category_id"),
						rs.getString("category_name")
				);
				list.add(c);
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

		return list;
	}
}
