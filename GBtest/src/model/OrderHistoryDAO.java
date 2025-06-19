package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHistoryDAO {
	private Connection con = null;

	public OrderHistoryDAO(Connection con) {
		this.con = con;
	}

	public ArrayList<OrderHistory> getOrderHistories(String email) {
		ArrayList<OrderHistory> list = new ArrayList<>();
		PreparedStatement pStmt = null;
		String sql = "select orders.order_no, orders.order_date, sum(quantity * price)" +
				" from orders inner join orders_detail" +
				" on orders.order_no = orders_detail.order_no" +
				" inner join book" +
				" on orders_detail.isbn = book.isbn" +
				" where email = ?" +
				" group by orders.order_no, orders.order_date" +
				" order by order_date desc";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, email);

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				OrderHistory history = new OrderHistory(
						rs.getString("order_no"),
						rs.getTimestamp("order_date"),
						rs.getInt("sum(quantity * price)")
						);
				list.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
