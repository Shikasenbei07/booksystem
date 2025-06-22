package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountBL;

@WebServlet("/SignupConfirmServlet")
public class SignupConfirmServlet extends HttpServlet {
	
	/*
	 * doGetは顧客情報登録確認画面を表示
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("signup_confirm.jsp").forward(req, resp);;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");

		AccountBL bl = new AccountBL();
		boolean result = bl.insertAccount(ac);
		if (result) {
			session.removeAttribute("account");
			resp.sendRedirect("signup_success.html");
			return;
		} else {
			session.removeAttribute("account");
			resp.sendRedirect("signup_failure.html");
			return;
		}
	}
}