package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
	private String orderNo;
	private String email;
	private Timestamp orderDate;
	private String isbn;
	private int quantity;

	public Order() {}
	public Order(String orderNo, String email, Timestamp orderDate, String isbn, int quantity) {
		this.orderNo = orderNo;
		this.email = email;
		this.orderDate = orderDate;
		this.isbn = isbn;
		this.quantity = quantity;
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

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
