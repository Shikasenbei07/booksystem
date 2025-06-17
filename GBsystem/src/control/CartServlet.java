package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String isbn = req.getParameter("isbn");

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

		req.getRequestDispatcher("search.jsp").forward(req, resp);
	}

}
