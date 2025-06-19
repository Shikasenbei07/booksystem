package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderHistory implements Serializable {
	private String orderNo;
	private Timestamp orderDate;
	private int total;

	public OrderHistory() {}
	public OrderHistory(String orderNo, Timestamp orderDate, int total) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.total = total;
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

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
