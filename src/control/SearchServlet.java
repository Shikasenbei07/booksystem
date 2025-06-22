package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.BookBL;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	/*
	 * doGetは検索画面に移動
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("toSearch", true);

		HttpSession session = req.getSession();
		String messageTarget = (String) session.getAttribute("message_target");
		if(messageTarget != null && !messageTarget.equals("SearchServlet")) {
			session.removeAttribute("message");
		}

		if (session.getAttribute("administrator") != null) {
			req.getRequestDispatcher("admin_search_books.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("search_books.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String author = req.getParameter("author");

		HttpSession session = req.getSession();
		session.removeAttribute("message");
		boolean isAdmin = false;
		if (session.getAttribute("administrator") != null) {
			isAdmin = true;
		}

		BookBL bl = new BookBL();
		ArrayList<Book> list = bl.searchBooks(category, title, author, isAdmin);
		session.setAttribute("search_result", list);

		session.setAttribute("search_category", category);
		session.setAttribute("search_title", title);
		session.setAttribute("search_author", author);

		String previousPage = (String) session.getAttribute("previous_page");
		req.setAttribute("toSearch", true);
		req.getRequestDispatcher(previousPage).forward(req, resp);
	}
}