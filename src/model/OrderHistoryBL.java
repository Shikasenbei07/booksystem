package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHistoryBL {
	public ArrayList<OrderHistory> getOrderHistory(String email){
		ArrayList<OrderHistory> list = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	
		try(Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)){
			OrderHistoryDAO dao = new OrderHistoryDAO(con);
			list = dao.getOrderHistory(email);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<OrderHistoryDetail> getOrderHistoryDetails(String order_no){
		ArrayList<OrderHistoryDetail> detailList = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	
		try(Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)){
			OrderHistoryDetailDAO dao = new OrderHistoryDetailDAO(con);
			detailList = dao.selectOrderDetails(order_no);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return detailList;
	}
	
	public int calcTotal(ArrayList<OrderHistoryDetail> detailList) {
		int total = 0;
		for(OrderHistoryDetail dL:detailList) {
			total += dL.getPrice() * dL.getQuentity();
		}
		return total;
	}

}