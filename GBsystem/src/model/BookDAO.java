package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO implements GBookDBInfo {
	private Connection con = null;

	public BookDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
}
