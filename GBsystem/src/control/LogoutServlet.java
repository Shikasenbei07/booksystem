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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		session.removeAttribute("account");
		session.removeAttribute("administrator");

		String mode = (String) session.getAttribute("account_mode");
		if (mode.equals("admin")) {
			resp.sendRedirect("admin_login.jsp");
		} else {
			resp.sendRedirect("search_books.jsp");
		}
	}

}
