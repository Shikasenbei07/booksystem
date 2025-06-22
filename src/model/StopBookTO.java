package model;

import java.io.Serializable;

public class StopBookTO implements Serializable{
	private String isbn;
	private boolean visible;
	
	public StopBookTO() {
	}
	
	public StopBookTO(String isbn, boolean visible) {
		this.isbn = isbn;
		this.visible = visible;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}

	
