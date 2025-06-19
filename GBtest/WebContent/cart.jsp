<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート情報</title>
	</head>
	<body>
		<h2>カート内容</h2>
		<%
			ArrayList<Book> books = (ArrayList<Book>) session.getAttribute("book_list");
			ArrayList<Integer> quantities = (ArrayList<Integer>) session.getAttribute("quantity_list");
			if (books == null) {
		%>
				カートの中身がありません<br>
		<%
			} else {
		%>
				<table border="1">
					<tr>
						<th>書籍名</th>
						<th>著者名</th>
						<th>単価</th>
						<th>数量</th>
					</tr>
					<%
						for (int i = 0; i < books.size(); i++) {
					%>
							<tr>
								<td><%= books.get(i).getTitle() %></td>
								<td><%= books.get(i).getAuthor() %></td>
								<td align="right">¥<%= String.format("%,d", books.get(i).getPrice()) %></td>
								<td align="right"><%= quantities.get(i) %></td>
							</tr>
					<%
						}
					%>
				</table><br>
				<h3>合計金額：¥<%= String.format("%,d", (Integer) session.getAttribute("total_price")) %></h3>
				<form action="OrderServlet" method="post">
					<input type="submit" value="購入">
				</form>
		<%
			}
		%>
		<button type="button" onclick="location.href='search_books.jsp'">検索トップへ</button>
	</body>
</html>