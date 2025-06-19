package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.BookBL;
import model.CategoryBL;

@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("isbn");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String message = (String) req.getAttribute("message");

		BookBL bl = new BookBL();
		Book book = bl.findBookByKey(isbn);

		HttpSession session = req.getSession();
		session.setAttribute("book", book);
		
		CategoryBL cbl = new CategoryBL();
		String categoryName = cbl.convertToCategoryName(book.getCategoryId());
		session.setAttribute("category_name", categoryName);

		session.setAttribute("search_category", category);
		session.setAttribute("search_title", title);
		session.setAttribute("search_author", author);
		if (message != null) {
			session.setAttribute("message", message);
		}
		resp.sendRedirect("book_detail.jsp?id=" + isbn);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}