package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String mail = req.getParameter("mail");
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		String lName = req.getParameter("l_name");
		String fName = req.getParameter("f_name");
		String prefecture = req.getParameter("prefecture");
		String city = req.getParameter("city");
		String address1 = req.getParameter("address1");
		String address2 = req.getParameter("address2");
		String phone1 = req.getParameter("phone1");
		String phone2 = req.getParameter("phone2");
		String phone3 = req.getParameter("phone3");
		String fullPhone = phone1 + "-" + phone2 + "-" + phone3;

		Account ac = new Account(mail, pass1, lName, fName, prefecture, city,address1, address2, fullPhone);
		HttpSession session = req.getSession();
		session.setAttribute("account", ac);

		if (pass1.equals(pass2)) {
			String hiddenPass = "";
			for (int i = 0; i < pass1.length(); i++) {
				hiddenPass += "●";
			}
			req.setAttribute("hidden_pass", hiddenPass);
			req.getRequestDispatcher("signup_confirm.jsp").forward(req, resp);
			return;
		} else {
			session.removeAttribute("account");
			req.getRequestDispatcher("signup.jsp").forward(req, resp);
			return;
		}
	}
}
