package model;

public class OrderDetail {
	private String orderNo;
	private String isbn;
	private int quantity;

	public OrderDetail() {}
	public OrderDetail(String orderNo, String isbn, int quantity) {
		this.orderNo = orderNo;
		this.isbn = isbn;
		this.quantity = quantity;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
