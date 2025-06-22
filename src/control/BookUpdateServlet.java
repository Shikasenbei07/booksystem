package control;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.BookBL;
import model.CategoryBL;

@WebServlet("/BookUpdateServlet")
public class BookUpdateServlet extends HttpServlet {

	/*
	 * doGetは書籍毎の更新画面に移動する
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("id");

		BookBL bl = new BookBL();
		Book book = bl.findBookByKey(isbn, true);
		req.setAttribute("book", book);

		CategoryBL cbl = new CategoryBL();
		String categoryName = cbl.convertToCategoryName(book.getCategoryId());
		req.setAttribute("category_name", categoryName);

		req.getRequestDispatcher("admin_update_book.jsp").forward(req, resp);
	}

	/*
	 * doPostは書籍情報を更新する
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String isbn = req.getParameter("isbn");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String strReleaseDate = req.getParameter("release_date");
		String strPrice = req.getParameter("price");
		String strCategoryId = req.getParameter("category_id");

		HttpSession session = req.getSession();
		Book beforeBook = (Book) session.getAttribute("before_book");

		if (title.equals("")) {
			title = beforeBook.getTitle();
		}

		if (author.equals("")) {
			author = beforeBook.getAuthor();
		}

		Timestamp releaseDate;
		if (strReleaseDate.equals("")) {
			releaseDate = beforeBook.getReleaseDate();
		} else {
			LocalDate localDate = LocalDate.parse(strReleaseDate);
			LocalDateTime localDateTime = localDate.atStartOfDay();
			releaseDate = Timestamp.valueOf(localDateTime);
		}

		int price;
		if (strPrice.equals("")) {
			price = beforeBook.getPrice();
		} else {
			price = Integer.parseInt(strPrice);
		}

		int categoryId;
		if (strCategoryId == null) {
			categoryId = beforeBook.getCategoryId();
		} else {
			categoryId = Integer.parseInt(strCategoryId);
		}

		boolean visible = beforeBook.isVisible();

		Book changedBook = new Book(isbn, title, author, releaseDate, price, categoryId, visible);
		BookBL bl = new BookBL();
		boolean result = bl.updateBook(changedBook);

		if (result) {
			session.setAttribute("message", "更新しました");
			req.setAttribute("book", changedBook);
		} else {
			session.setAttribute("message", "更新できませんでした");
			req.setAttribute("book", beforeBook);
		}

		session.setAttribute("message_from", req.getParameter("message_from"));

		resp.sendRedirect("BookUpdateServlet?id=" + isbn);
	}
}