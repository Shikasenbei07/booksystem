package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.BookBL;

/**
 * Servlet implementation class CartChangeServlet
 */
@WebServlet("/CartChangeServlet")
public class CartChangeServlet extends HttpServlet {
	
	/*
	 * doGetはカート画面に移動する
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("to_cart", true);

		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		if (cart == null) {
			req.getRequestDispatcher("cart.jsp").forward(req, resp);
			return;
		}

		BookBL bl = new BookBL();
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<Integer> quantities = new ArrayList<>();
		for (String isbn : cart.keySet()) {
			Book book = bl.findBookByKey(isbn, false);
			books.add(book);
			quantities.add(cart.get(isbn));
		}
		int total = bl.calcTotalPrice(books, quantities);

		session.setAttribute("book_list", books);
		session.setAttribute("quantity_list", quantities);
		session.setAttribute("total_price", total);
		req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}
	
	/*
	 * doPostはカートの内容を書き換える
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("isbn");
		String query = req.getParameter("query");

		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		if (cart == null) {
			req.getRequestDispatcher("cart.jsp").forward(req, resp);
			return;
		}

		if (query.equals("add")) {
			cart.put(isbn, cart.get(isbn) + 1);
		} else if (query.equals("sub")) {
			if (cart.get(isbn) > 0) {
				cart.put(isbn, cart.get(isbn) - 1);
			}
		} else if (query.equals("del")) {
			cart.remove(isbn);
		}
		
		resp.sendRedirect("CartServlet");
	}

}
