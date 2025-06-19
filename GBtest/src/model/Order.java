package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
	private String orderNo;
	private String email;
	private Timestamp orderDate;

	public Order() {}
	public Order(String orderNo, String email, Timestamp orderDate) {
		this.orderNo = orderNo;
		this.email = email;
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
}