package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.CategoryBL;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryBL bl = new CategoryBL();
		ArrayList<Category> list = bl.getAllCategory();

		HttpSession session = req.getSession();
		session.setAttribute("categories", list);

		String previousPage = (String) session.getAttribute("previous_page");
		resp.sendRedirect(previousPage);
	}

}
