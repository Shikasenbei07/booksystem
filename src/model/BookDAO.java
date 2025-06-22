package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	private Connection con = null;

	public BookDAO(Connection con) {
		this.con = con;
	}

	public ArrayList<Book> searchBooks(String category, String title, String author, boolean isAdmin) {
		ArrayList<Book> list = new ArrayList<>();
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from book"
				+ " inner join visibility on book.isbn = visibility.isbn"
				+ " where category_id like ? and title like ? and author like ?";
		if (!isAdmin) {
			sql += " and visible = ?";
		}

		try {
			pStmt = con.prepareStatement(sql);
			if (category.equals("all")) {
				pStmt.setString(1, "%");
			} else {
				pStmt.setInt(1, Integer.parseInt(category));
			}
			pStmt.setString(2, "%" + title + "%");
			pStmt.setString(3, "%" + author + "%");
			if (!isAdmin) {
				pStmt.setBoolean(4, true);
			}

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Book book = new Book(
						rs.getString("isbn"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getTimestamp("release_date"),
						rs.getInt("price"),
						rs.getInt("category_id"),
						rs.getBoolean("visible")
						);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public Book findBookByKey(String isbn, boolean isAdmin) {
		Book book = null;
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from book"
				+ " inner join visibility on book.isbn = visibility.isbn"
				+ " where book.isbn = ?";
		if (!isAdmin) {
			sql += " and visible = ?";
		}

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, isbn);
			if (!isAdmin) {
				pStmt.setBoolean(2, true);
			}
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				book = new Book(
						rs.getString("isbn"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getTimestamp("release_date"),
						rs.getInt("price"),
						rs.getInt("category_id"),
						rs.getBoolean("visible")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public boolean insertBook(Book book) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "insert into book"
				+ " values (?, ?, ?, ?, ?, ?)";

		try {
			if (book.getPrice() < 0) {
				throw new SQLException("負の値が含まれています");
			}
			
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, book.getIsbn());
			pStmt.setString(2, book.getTitle());
			pStmt.setString(3, book.getAuthor());
			pStmt.setTimestamp(4, book.getReleaseDate());
			pStmt.setInt(5, book.getPrice());
			pStmt.setInt(6, book.getCategoryId());

			int line = pStmt.executeUpdate();
			if (line == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean setVisibility(Book book, boolean visible) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "insert into visibility"
				+ " values (?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, book.getIsbn());
			pStmt.setBoolean(2, visible);

			int line = pStmt.executeUpdate();
			if (line == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean updateBook(Book book) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "update book"
				+ " set title = ?, author = ?, release_date = ?, price = ?, category_id = ?"
				+ " where isbn = ?";

		try {
			if (book.getPrice() < 0) {
				throw new SQLException("負の値が含まれています");
			}
			
			pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, book.getTitle());
			pStmt.setString(2, book.getAuthor());
			pStmt.setTimestamp(3, book.getReleaseDate());
			pStmt.setInt(4, book.getPrice());
			pStmt.setInt(5, book.getCategoryId());
			pStmt.setString(6, book.getIsbn());
			
			int line = pStmt.executeUpdate();
			if (line == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean updateVisibility(Book book, boolean visible) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "update visibility"
				+ " set visible = ?"
				+ " where isbn = ?";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setBoolean(1, visible);
			pStmt.setString(2, book.getIsbn());

			int line = pStmt.executeUpdate();
			if (line == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}