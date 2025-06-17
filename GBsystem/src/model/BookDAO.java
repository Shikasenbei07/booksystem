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

	public ArrayList<Book> searchBooks(String category, String title, String author) {
		ArrayList<Book> list = new ArrayList<>();
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from book"
				+ " where category_id like ? and title like ? and author like ?";

		try {
			pStmt = con.prepareStatement(sql);
			if (category.equals("all")) {
				pStmt.setString(1, "%");
			} else {
				pStmt.setInt(1, Integer.parseInt(category));
			}
			pStmt.setString(2, "%" + title + "%");
			pStmt.setString(3, "%" + author + "%");

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Book book = new Book(
						rs.getString("isbn"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getTimestamp("release_date"),
						rs.getInt("price"),
						rs.getInt("category_id")
						);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public Book findBookByKey(String isbn) {
		Book book = null;
		PreparedStatement pStmt = null;
		String sql = "select *"
				+ " from book"
				+ " where isbn = ?";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, isbn);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				book = new Book(
						rs.getString("isbn"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getTimestamp("release_date"),
						rs.getInt("price"),
						rs.getInt("category_id")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public boolean registerBook(Book book) {
		boolean result = false;
		PreparedStatement pStmt = null;
		String sql = "insert into book"
				+ " values (?, ?, ?, ?, ?, ?)";

		try {
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
}
