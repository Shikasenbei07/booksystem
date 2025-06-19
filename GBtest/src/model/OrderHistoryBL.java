package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHistoryBL {
	public ArrayList<OrderHistory> getOrderHistories(String email) {
		ArrayList<OrderHistory> list = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				OrderHistoryDAO dao = new OrderHistoryDAO(con);
				list = dao.getOrderHistories(email);

				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<OrderHistoryDetail> getOrderHistoryDetails(String orderNo) {
		ArrayList<OrderHistoryDetail> list = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				OrderHistoryDetailDAO dao = new OrderHistoryDetailDAO(con);
				list = dao.getOrderHistoryDetails(orderNo);

				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int calcTotal(ArrayList<OrderHistoryDetail> histories) {
		int total = 0;
		for (OrderHistoryDetail o : histories) {
			total += o.getPrice() * o.getQuantity();
		}
		return total;
	}
}
