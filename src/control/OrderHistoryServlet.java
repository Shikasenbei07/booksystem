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

/**
 * Servlet implementation class OrderHistoryServlet
 */
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	
	/*
	 * doGetは注文履歴画面を表示
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<OrderHistory> list = null;
		
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		
		Account ac = (Account) session.getAttribute("account");
		
		if(ac == null) {
			session.setAttribute("previous_page", "OrderHistoryServlet");
			resp.sendRedirect("LoginServlet");
		}else {
			OrderHistoryBL bl = new OrderHistoryBL();
			list = bl.getOrderHistory(ac.getEmail());

			session.setAttribute("oh", list);
			
			req.getRequestDispatcher("order_history.jsp").forward(req, resp);
		}
	}

}
