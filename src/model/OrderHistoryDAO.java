package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHistoryDAO {
	private Connection con;
	
	public OrderHistoryDAO(Connection con) {
		this.con = con;
	}
	
	public ArrayList<OrderHistory> getOrderHistory(String email){
		PreparedStatement pStmt = null;
		OrderHistory temp = null;
		ArrayList<OrderHistory> list = null;
		
		try {
			pStmt = con.prepareStatement("select o.order_no, o.order_date, sum(od.quantity*b.price) as total "
					+ " from orders as o inner join orders_detail as od"
					+ " on o.order_no = od.order_no"
					+ " inner join book as b"
					+ " on od.isbn = b.isbn where o.email = ?"
					+ " group by o.order_no, o.order_date"
					+ " order by order_date desc");
			pStmt.setString(1, email);
			ResultSet rs = pStmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				temp = new OrderHistory(
						rs.getString("order_no"),
						rs.getTimestamp("order_date"), 
						rs.getInt("total"));
				
				list.add(temp);
			}
			rs.close();
			pStmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
