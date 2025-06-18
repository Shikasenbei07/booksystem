package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookBL {
	public ArrayList<Book> searchBooks(String category, String title, String author) {
		ArrayList<Book> list = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				BookDAO dao = new BookDAO(con);
				list = dao.searchBooks(category, title, author);

				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public Book findBookByKey(String isbn) {
		Book book = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				BookDAO dao = new BookDAO(con);
				book = dao.findBookByKey(isbn);

				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public boolean registerBook(Book book) {
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DBInfo.dbUrl, DBInfo.dbUser, DBInfo.dbPass)) {
			con.setAutoCommit(false);
			try {
				BookDAO dao = new BookDAO(con);
				result = dao.registerBook(book);

				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return  result;
	}

	public int calcTotalPrice(ArrayList<Book> books, ArrayList<Integer> quantities) {
		int total = 0;

		for (int i = 0; i < books.size(); i++) {
			total += books.get(i).getPrice() * quantities.get(i);
		}

		return total;
	}
}
