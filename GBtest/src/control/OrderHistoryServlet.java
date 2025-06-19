package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.OrderHistory;
import model.OrderHistoryBL;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");
		if (ac == null) {
			session.setAttribute("previous_page", "OrderHistoryServlet");
			resp.sendRedirect("LoginServlet");
		} else {
			doPost(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");
		if (ac == null) {
			session.setAttribute("previous_page", "OrderHistoryServlet");
			req.getRequestDispatcher("LoginServlet").forward(req, resp);
			return;
		}

		OrderHistoryBL bl = new OrderHistoryBL();
		ArrayList<OrderHistory> list = bl.getOrderHistories(ac.getEmail());
		session.setAttribute("order_history", list);

		req.getRequestDispatcher("order_history.jsp").forward(req, resp);
	}

}
