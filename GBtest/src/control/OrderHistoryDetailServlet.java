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
import model.OrderHistoryBL;
import model.OrderHistoryDetail;

@WebServlet("/OrderHistoryDetailServlet")
public class OrderHistoryDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("order_history_detail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String orderNo = req.getParameter("order_no");

		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");
		if (ac == null) {
			session.setAttribute("previous_page", "OrderHistoryDetailServlet");
			req.getRequestDispatcher("LoginServlet").forward(req, resp);
			return;
		}

		OrderHistoryBL bl = new OrderHistoryBL();
		ArrayList<OrderHistoryDetail> list = bl.getOrderHistoryDetails(orderNo);
		session.setAttribute("order_history_detail", list);
		int total = bl.calcTotal(list);
		session.setAttribute("total_price", total);
		req.getRequestDispatcher("order_history_detail.jsp").forward(req, resp);
	}

}
