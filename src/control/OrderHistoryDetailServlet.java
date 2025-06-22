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
	
	/*
	 * doGetは注文履歴詳細を表示
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<OrderHistoryDetail> detailList = null;
		int total = 0; 
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String order_no = req.getParameter("order_no");
		String order_date = req.getParameter("order_date");
		
		Account ac = (Account) session.getAttribute("account");
		
		if(ac == null) {
			session.setAttribute("previous_page", "OrderHistoryServlet");
			resp.sendRedirect("LoginServlet");
		} else {
			OrderHistoryBL bl = new OrderHistoryBL(); 
			detailList = bl.getOrderHistoryDetails(order_no);
			total = bl.calcTotal(detailList);
			
			session.setAttribute("order_no", order_no);
			session.setAttribute("order_date", order_date);
			session.setAttribute("ohd", detailList);
			session.setAttribute("total", total);
			req.getRequestDispatcher("order_history_detail.jsp").forward(req,resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
