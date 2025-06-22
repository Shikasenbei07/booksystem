package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrator;
import model.AdministratorBL;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	
	/*
	 * doGetは管理者ログイン画面に移動
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("toLogin", true);
		
		req.getRequestDispatcher("admin_login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");

		AdministratorBL bl = new AdministratorBL();
		Administrator admin = bl.login(id);

		if (admin != null && pass.equals(admin.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute("administrator", admin);
			resp.sendRedirect("SearchServlet");
			return;
		} else {
			req.setAttribute("login_message", "登録情報が見つかりません");
			req.getRequestDispatcher("admin_login.jsp").forward(req, resp);
			return;
		}
	}
}