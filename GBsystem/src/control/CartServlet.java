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

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	/*
	 * doPostはカートに商品の情報を追加する
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("isbn");
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String targetServlet = req.getParameter("target_servlet");

		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
			session.setAttribute("cart", cart);
		}

		if (cart.containsKey(isbn)) {
			cart.put(isbn, cart.get(isbn) + 1);
		} else {
			cart.put(isbn, 1);
		}

		req.setAttribute("search_category", category);
		req.setAttribute("search_title", title);
		req.setAttribute("search_author", author);
		req.setAttribute("message", "カートに追加しました");
		req.getRequestDispatcher(targetServlet).forward(req, resp);
	}

	/*
	 * doGetはカート画面に移動する
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		if (cart == null) {
			resp.sendRedirect("cart.jsp");
			return;
		}

		BookBL bl = new BookBL();
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<Integer> quantities = new ArrayList<>();
		for (String isbn : cart.keySet()) {
			Book book = bl.findBookByKey(isbn);
			books.add(book);
			quantities.add(cart.get(isbn));
		}
		int total = bl.calcTotalPrice(books, quantities);

		session.setAttribute("book_list", books);
		session.setAttribute("quantity_list", quantities);
		session.setAttribute("total_price", total);
		req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}

}
