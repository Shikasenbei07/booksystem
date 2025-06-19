package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHistoryDetailDAO {
	private Connection con = null;

	public OrderHistoryDetailDAO(Connection con) {
		this.con = con;
	}

	public ArrayList<OrderHistoryDetail> getOrderHistoryDetails(String orderNo) {
		ArrayList<OrderHistoryDetail> list = new ArrayList<>();
		PreparedStatement pStmt = null;
		String sql = "select orders.order_no, orders.order_date, book.title, book.price, orders_detail.quantity" +
				" from orders inner join orders_detail" +
				" on orders.order_no = orders_detail.order_no" +
				" inner join book" +
				" on orders_detail.isbn = book.isbn" +
				" where orders.order_no = ?" +
				" order by order_no desc";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, orderNo);

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				OrderHistoryDetail history = new OrderHistoryDetail(
						rs.getString("order_no"),
						rs.getTimestamp("order_date"),
						rs.getString("title"),
						rs.getInt("price"),
						rs.getInt("quantity")
						);
				list.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
