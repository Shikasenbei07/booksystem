package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

public class OrderBL {
	public boolean registerOrder(String email, HashMap<String, Integer> cart) {
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				String orderNo = UUID.randomUUID().toString();
				Timestamp orderDate = new Timestamp(System.currentTimeMillis());
				Order order = new Order(orderNo, email, orderDate);
				
				OrderDAO dao1 = new OrderDAO(con);
				result = dao1.insertOrder(order);
				if (!result) {
					con.rollback();
					return result;
				}
				
				OrderDetailDAO dao2 = new OrderDetailDAO(con);
				for (String isbn : cart.keySet()) {
					OrderDetail detail = new OrderDetail(orderNo, isbn, cart.get(isbn));
					result = dao2.insertOrderDetail(detail);
					if (!result) {
						con.rollback();
						return result;
					}
				}

				con.commit();
				result = true;
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
		
		return result;
	}
}
