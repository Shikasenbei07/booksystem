package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Book implements Serializable {
	private String isbn;
	private String title;
	private String author;
	private Timestamp releaseDate;
	private int price;
	private int categoryId;
	private boolean visible;

	public Book() {}
	public Book(String isbn, String title, String author, Timestamp releaseDate, int price, int categoryId, boolean visible) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.price = price;
		this.categoryId = categoryId;
		this.visible = visible;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}