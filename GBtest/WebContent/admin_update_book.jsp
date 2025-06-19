<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.*"%>
<%
	session.setAttribute("previous_page", "admin_update_book.jsp");

	Book book = (Book) request.getAttribute("book");
	String categoryName = (String) request.getAttribute("category_name");
	if (book == null) {
		response.sendRedirect("admin_search_books.jsp");
		return;
	}
	session.setAttribute("before_book", book);

	ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
	if (categories == null) {
		response.sendRedirect("CategoryServlet");
		return;
	}
	
	String message = (String) request.getAttribute("message");
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>書籍情報更新</title>
		<link rel="stylesheet" href="top_style.css" type="text/css">
	</head>
	<body>
		<%
			if (message != null) {
		%>
				<div id="alertBox"><%= message %></div>
				<script>
					// 1秒後にアラートをフェードアウトして非表示に
					setTimeout(() => {
						document.getElementById('alertBox').classList.add('hide');
					}, 1000);
				</script>
		<%
			}
		%>
		<h1>書籍情報の更新</h1>
		<form action="BookUpdateServlet" method="post">
			ISBN　<%= book.getIsbn() %>
			<table border="1">
				<tr>
					<th>項目</th>
					<th>変更前</th>
					<th>変更後</th>
				</tr>
				<tr>
					<td>書籍名</td>
					<td><%= book.getTitle() %></td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>著者名</td>
					<td><%= book.getAuthor() %></td>
					<td><input type="text" name="author"></td>
				</tr>
				<tr>
					<td>発売日</td>
					<td><%= book.getReleaseDate().toLocalDateTime().toLocalDate() %></td>
					<td><input type="date" name="release_date"></td>
				</tr>
				<tr>
					<td>税込価格</td>
					<td align="right">¥<%= String.format("%,d", book.getPrice()) %></td>
					<td align="right">¥<input type="number" name="price"></td>
				</tr>
				<tr>
					<td>書籍の種類</td>
					<td><%= categoryName %></td>
					<td>
						<select name="category_id">
							<%
								String sc = (String) request.getAttribute("search_category");
								if (sc == null) {
									sc = "all";
								}
								for (Category c : categories) {
							%>
									<option value="<%= c.getCategoryId() %>" <%= sc.equals(Integer.toString(c.getCategoryId())) ? "selected" : "" %>>
										<%= c.getCategoryName() %>
									</option>
							<%
								}
							%>
						</select>
					</td>
				</tr>
			</table>
			<input type="hidden" value="<%= book.getIsbn() %>" name="isbn">
			<input type="submit" value="更新">
		</form>
		<button onclick="location.href='admin_search_books.jsp'">戻る</button>
	</body>
</html>