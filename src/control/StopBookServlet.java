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

/**
 * Servlet implementation class StopServlet
 */
@WebServlet("/StopBookServlet")
public class StopBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("isbn");
		String query = req.getParameter("query");

		BookBL bl = new BookBL();
		Book book = bl.findBookByKey(isbn, true);
		boolean visible = true;
		boolean result = false;
		if (query.equals("stop")) {
			visible = false;
			result = bl.updateVisibility(book, visible);
		} else {
			visible = true;
			result = bl.updateVisibility(book, visible);
		}

		HttpSession session = req.getSession();
		if(result) {
			if (visible) {
				session.setAttribute("message", "再開しました");
			} else {
				session.setAttribute("message", "停止しました");
			}
		}else {
			session.setAttribute("message", "変更できませんでした");
		}
		session.setAttribute("message_from", req.getParameter("message_from"));

		String previousPage = (String) session.getAttribute("previous_page");
		if (previousPage.equals("admin_search_books.jsp")) {
			String category = (String) session.getAttribute("search_category");
			String title = (String) session.getAttribute("search_title");
			String author = (String) session.getAttribute("search_author");
			ArrayList<Book> list = bl.searchBooks(category, title, author, true);
			session.setAttribute("search_result", list);
		}
		resp.sendRedirect(previousPage);
	}

	/*@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}*/

}
