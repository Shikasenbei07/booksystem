<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Category, model.CategoryBL, model.Book" %>
<%
	CategoryBL bl = new CategoryBL();
	ArrayList<Category> list = bl.getAllCategory();
	application.setAttribute("category", list);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>書籍検索</title>
	</head>
	<body>
		<button type="button" onclick="location.href='register_book.jsp'">新規書籍登録</button>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="ログアウト">
		</form>
		<hr>
		<form action="SearchServlet" method="post">
			種類：<select name="category">
				<option value="all" selected>すべて</option>
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
			</select>
			書籍名<input type="text" name="title">
			著者名<input type="text" name="author">
			<input type="submit" value="検索"><br>
		</form>

			<%
				ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("search_result");
				if(books != null) {
			%>
					<table border="1">
						<tr>
							<th>書籍名</th>
							<th>著者名</th>
							<th>税込価格</th>
							<th></th>
							<th></th>
						</tr>
					<%
						for (Book b : books) {
					%>
							<tr>
								<td><%= b.getTitle() %></td>
								<td><%= b.getAuthor() %></td>
								<td align="right"><%= b.getPrice() %></td>
								<td>
									<form action="BookDetailServlet">
										<input type="hidden" name="isbn" value="<%= b.getIsbn() %>">
										<input type="submit" value="更新">
									</form>
								</td>
								<td><button onclick="location.href='SuspensionServlet'">取り扱い停止</button></td>
							</tr>
			<%
						}
			%>
					</table>
			<%
				}
			%>

	</body>
</html>