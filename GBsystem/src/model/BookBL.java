package model;

public class BookBL {
	public boolean registerBook(Book book) {
		boolean result = false;

		BookDAO dao = new BookDAO();
		result = dao.registerBook(book);

		return  result;
	}
}
