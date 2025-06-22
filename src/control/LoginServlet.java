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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/*
	 * doGetはログイン画面に移動
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("toLogin", true);
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	/*
	 * doPostはログイン実行
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("mail");
		String pass = req.getParameter("pass");

		AccountBL bl = new AccountBL();
		Account ac = bl.login(email);

		if (ac == null) {
			req.setAttribute("login_message", "登録情報が見つかりません");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}

		if (pass.equals(ac.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute("account", ac);
			String previousPage = (String) session.getAttribute("previous_page");
			if (previousPage == null) {
				previousPage = "SearchServlet";
			}
			resp.sendRedirect(previousPage);
			return;
		} else {
			req.setAttribute("login_message", "パスワードが違います");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
	}
}