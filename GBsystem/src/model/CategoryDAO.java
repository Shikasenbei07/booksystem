package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
	private Connection con = null;

	public CategoryDAO(Connection con) {
		this.con = con;
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
		}

		return list;
	}
}
