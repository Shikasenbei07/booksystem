package control;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Order;
import model.OrderBL;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");
		String email = ac.getEmail();
		String orderNo = UUID.randomUUID().toString();
		Timestamp orderDate = new Timestamp(System.currentTimeMillis());
		Order order = new Order(orderNo, email, orderDate);

		@SuppressWarnings("unchecked")
		HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		OrderBL bl = new OrderBL();
		boolean result = bl.registerOrder(order, cart);
	}

}
