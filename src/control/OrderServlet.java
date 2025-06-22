package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.OrderBL;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");
		if (ac == null) {
			session.setAttribute("previous_page", "cart.jsp");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		
		String email = ac.getEmail();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		
		OrderBL bl = new OrderBL();
		boolean result = bl.registerOrder(email, cart);
		if (result) {
			session.setAttribute("order_message", "購入処理が完了しました");
			session.removeAttribute("cart");
			session.removeAttribute("book_list");
			session.removeAttribute("quantity_list");
		} else {
			session.setAttribute("order_message", "購入に失敗しました");
		}
		
		req.getRequestDispatcher("order_result.jsp").forward(req, resp);
	}

}
