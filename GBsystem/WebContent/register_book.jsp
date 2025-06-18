<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Administrator, model.Category, model.CategoryBL" %>
<%
	Administrator admin = (Administrator) session.getAttribute("administrator");
	if (admin == null) {
		response.sendRedirect("admin_login.jsp");
		return;
	}

	CategoryBL bl = new CategoryBL();
	ArrayList<Category> list = bl.getAllCategory();
	application.setAttribute("category", list);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新規書籍登録</title>
	</head>
	<body>
		<%
			if (request.getAttribute("message") != null) {
		%>
				<script>
					alert("${requestScope.message}");
				</script>
		<%
			}
		%>
		<h2>書籍の登録</h2>
		<form action="BookRegistrationServlet" method="post">
			書籍名：<input type="text" name="title" required><br>
			著者名：<input type="text" name="author" required><br>
			ISBN：<input type="text" name="isbn1" required>-<input type="text" name="isbn2" required>-<input type="text" name="isbn3" required>-<input type="text" name="isbn4" required>-<input type="text" name="isbn5" required><br>
			発売日：<input type="date" name="release_date" required><br>
			税込価格：<input type="text" name="price" required><br>
			種類：<select name="category" required>
				<option value="" selected>選択してください</option>
				<%
					ArrayList<Category> categories = (ArrayList<Category>) application.getAttribute("category");
					if(list != null) {
						for (Category c : categories) {
				%>
							<option value="<%= c.getCategoryId() %>"><%= c.getCategoryName() %></option>
				<%
						}
					}
				%>
			</select><br>
			<input type="submit" value="登録">
		</form>
		<button type="button" onclick="location.href='search_books.jsp'">検索画面に戻る</button>
	</body>
</html>