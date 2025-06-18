package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String mode = "";
		if (session.getAttribute("admin") != null) {
			mode = "admin";
		}

		session.invalidate();
		req.setAttribute("message", "ログアウトしました");

		if (mode.equals("admin")) {
			req.getRequestDispatcher("admin_login.jsp").forward(req, resp);
			return;
		} else {
			req.getRequestDispatcher("search_books.jsp").forward(req, resp);
			return;
		}
	}

}
