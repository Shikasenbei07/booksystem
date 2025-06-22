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
	
	public ArrayList<OrderHistoryDetail> selectOrderDetails(String order_no){
		OrderHistoryDetail temp = null;
		ArrayList<OrderHistoryDetail> detailList = null;
		
		try {
			PreparedStatement pStmt = con.prepareStatement(" select o.order_no, o.order_date, b.title, b.price, od.quantity from orders as o inner join orders_detail as od on o.order_no = od.order_no inner join book as b on od.isbn = b.isbn where o.order_no = ? order by order_no desc");
			pStmt.setString(1, order_no);
			ResultSet rs = pStmt.executeQuery();

			detailList = new ArrayList<>();			
			while (rs.next()) {
				temp = new OrderHistoryDetail(
						rs.getString("order_no"),
						rs.getTimestamp("order_date"),
						rs.getString("title"),
						rs.getInt("price"),
						rs.getInt("quantity"));
						
				detailList.add(temp);
			}
			rs.close();
			pStmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return detailList;

	}
		
}


