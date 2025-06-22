package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDAO {
	private Connection con = null;

	public OrderDetailDAO(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrderDetail(OrderDetail detail) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "insert into orders_detail"
				+ " values (?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, detail.getOrderNo());
			pStmt.setString(2, detail.getIsbn());
			pStmt.setInt(3, detail.getQuantity());

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
