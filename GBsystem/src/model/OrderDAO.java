package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
	private Connection con = null;

	public OrderDAO(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order order) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "insert into order"
				+ " values (?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, order.getOrderNo());
			pStmt.setString(2, order.getEmail());
			pStmt.setTimestamp(3, order.getOrderDate());

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
