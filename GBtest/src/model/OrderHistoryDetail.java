package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderHistoryDetail implements Serializable {
	private String orderNo;
	private Timestamp orderDate;
	private String title;
	private int price;
	private int quantity;

	public OrderHistoryDetail() {}
	public OrderHistoryDetail(String orderNo, Timestamp orderDate, String title, int price, int quantity) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
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

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
