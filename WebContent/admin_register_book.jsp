<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>

<%
	session.setAttribute("previous_page", "admin_register_book.jsp");

	Administrator admin = (Administrator) session.getAttribute("administrator");
	if (admin == null) {
		response.sendRedirect("admin_login.jsp");
		return;
	}

	ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
	if (categories == null) {
		response.sendRedirect("CategoryServlet");
		return;
	}

	String messageTarget = (String) session.getAttribute("message_target");
	String message = (String) session.getAttribute("message");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新規書籍登録</title>
		<link rel="stylesheet" href="top_style.css" type="text/css">
	</head>
	<body>
		<%
			if (message != null) {
		%>
				<script>
					alert("<%= message %>");
				</script>
		<%
			}
		%>
		<h2>書籍の登録</h2>
		<form action="BookRegistrationServlet" method="post">
			書籍名：<input type="text" name="title" required><br>
			著者名：<input type="text" name="author" required><br>
			ISBN：<input type="number" name="isbn1" required>-<input type="number" name="isbn2" required>-<input type="number" name="isbn3" required>-<input type="number" name="isbn4" required>-<input type="number" name="isbn5" required><br>
			発売日：<input type="date" name="release_date" required><br>
			税込価格：<input type="number" name="price" required><br>
			種類：<select name="category" required>
				<option value="" selected>選択してください</option>
				<%
					for (Category c : categories) {
				%>
						<option value="<%= c.getCategoryId() %>"><%= c.getCategoryName() %></option>
				<%
					}
				%>
			</select><br>
			<input type="submit" value="登録">
		</form>
		<button type="button" onclick="location.href='admin_search_books.jsp'">検索画面に戻る</button>
	</body>
</html>