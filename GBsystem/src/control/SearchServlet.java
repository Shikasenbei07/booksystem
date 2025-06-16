package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookBL;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String author = req.getParameter("author");

		BookBL bl = new BookBL();
		ArrayList<Book> list = bl.searchBooks(category, title, author);
		req.setAttribute("search_result", list);

		req.getRequestDispatcher("search_books.jsp").forward(req, resp);
	}
}
