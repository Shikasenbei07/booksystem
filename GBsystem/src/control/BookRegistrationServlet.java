package control;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookBL;

@WebServlet("/BookRegistrationServlet")
public class BookRegistrationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("isbn1")
				+ req.getParameter("isbn2")
				+ req.getParameter("isbn3")
				+ req.getParameter("isbn4")
				+ req.getParameter("isbn5");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String releaseDate = req.getParameter("release_date");
		Timestamp date = Timestamp.valueOf(LocalDate.parse(releaseDate).atStartOfDay());
		int price = Integer.parseInt(req.getParameter("price"));
		int categoryId = Integer.parseInt(req.getParameter("category"));

		Book book = new Book(isbn, title, author, date, price, categoryId);
		BookBL bl = new BookBL();
		boolean result = bl.registerBook(book);
		if (result) {
			req.setAttribute("message", "登録が完了しました");
		} else {
			req.setAttribute("message", "登録に失敗しました");
		}

		req.getRequestDispatcher("register_book.jsp").forward(req, resp);
	}

}
