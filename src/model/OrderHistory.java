package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderHistory implements Serializable{
	private String order_no;
	private Timestamp order_date;
	private int total;
	
	public OrderHistory() {

	}
	
	
	public OrderHistory(String order_no, Timestamp order_date, int total) {
		this.order_no = order_no;
		this.order_date = order_date;
		this.total = total;
	}


	public String getOrder_no() {
		return order_no;
	}


	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}


	public Timestamp getOrder_date() {
		return order_date;
	}


	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
