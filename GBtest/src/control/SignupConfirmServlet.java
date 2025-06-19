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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("account");

		AccountBL bl = new AccountBL();
		boolean result = bl.insertAccount(ac);
		if (result) {
			req.setAttribute("message", "登録が完了しました");
		} else {
			req.setAttribute("message", "登録に失敗しました");
		}

		req.getRequestDispatcher("signup_result.jsp").forward(req, resp);
	}
}