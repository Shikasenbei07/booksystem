package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderHistoryDetail implements Serializable {

	private String order_no;
	private Timestamp order_date;
	private String title;
	private int price;
	private int quentity;
	
	public OrderHistoryDetail() {
		
	}
	
	public OrderHistoryDetail(String order_no, Timestamp order_date, String title, int price, int quentity) {
		this.order_no = order_no;
		this.order_date = order_date;
		this.title = title;
		this.price = price;
		this.quentity = quentity;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuentity() {
		return quentity;
	}

	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}
	
	
	
}
