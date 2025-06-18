package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class OrderBL {
	public boolean registerOrder(Order order, HashMap<String, Integer> cart) {
		boolean result1 = false;
		boolean result2 = false;
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				OrderDAO dao1 = new OrderDAO(con);
				result1 = dao1.insertOrder(order);

				OrderDetailDAO dao2 = new OrderDetailDAO(con);
				int lines = 0;
				for (String key : cart.keySet()) {
					OrderDetail od = new OrderDetail(order.getOrderNo(), key, cart.get(key));
					if(dao2.insertOrderDetail(od)) {
						lines += 1;
					}
				}
				if (lines == cart.size()) {
					result2 = true;
				}

				if (result1 && result2) {
					result = true;
				}

				con.commit();
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

		return  result;
	}
}
