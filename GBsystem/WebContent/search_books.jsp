<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>

<%
	session.setAttribute("previous_page", "search_books.jsp");
	ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
	if (categories == null) {
		response.sendRedirect("CategoryServlet");
		return;
	}
	Account ac = (Account) session.getAttribute("account");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>書籍検索</title>
		<link rel="stylesheet" href="top_style.css" type="text/css">
	</head>
	<body>
		<%
			if (request.getAttribute("message") != null) {
		%>
				<div id="alertBox">${requestScope.message}</div>
				<script>
				  // 1秒後にアラートをフェードアウトして非表示に
				  setTimeout(() => {
				    document.getElementById('alertBox').classList.add('hide');
				  }, 1000);
				</script>
		<%
			}
		%>

		<div class="form-container">
			<%
				if (ac == null) {
			%>
					<form action="login.jsp" method="post">
						<input type="submit" value="ログイン">
					</form>
			<%
				}
			%>
			<form action="CartServlet" method="get">
				<input type="submit" value="カート">
			</form>
			<form action="OrderHistoryServlet" method="get">
				<input type="submit" value="注文履歴">
			</form>
		</div>
		<%
			if (ac != null) {
		%>
				<div class="text-right">
					<%= ac.getLastName() %> <%= ac.getFirstName() %>さん
					<form action="LogoutServlet" method="post">
						<input type="submit" value="ログアウト">
					</form>
				</div>
		<%
			}
		%>

		<hr>

		<form action="SearchServlet" method="post">
			種類：<select name="category">
				<%
					String sc = (String) request.getAttribute("search_category");
					if (sc == null) {
						sc = "all";
					}
				%>
				<option value="all" <%= sc.equals("all") ? "selected" : "" %>>すべて</option>
				<%
					for (Category c : categories) {
				%>
						<option value="<%= c.getCategoryId() %>" <%= sc.equals(Integer.toString(c.getCategoryId())) ? "selected" : "" %>>
							<%= c.getCategoryName() %>
						</option>
				<%
					}
				%>
			</select>
			書籍名<input type="text" name="title" value="${requestScope.search_title}">
			著者名<input type="text" name="author" value="${requestScope.search_author}">
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
								<td align="right">¥<%= String.format("%,d", b.getPrice()) %></td>
								<td>
									<form action="BookDetailServlet">
										<input type="hidden" name="isbn" value="<%= b.getIsbn() %>">
										<input type="hidden" name="category" value="${requestScope.search_category}">
										<input type="hidden" name="title" value="${requestScope.search_title}">
										<input type="hidden" name="author" value="${requestScope.search_author}">
										<input type="submit" value="詳細">
									</form>
								</td>
								<td>
									<form action="CartServlet" method="post">
										<input type="hidden" name="isbn" value="<%= b.getIsbn() %>">
										<input type="hidden" name="category" value="${requestScope.search_category}">
										<input type="hidden" name="title" value="${requestScope.search_title}">
										<input type="hidden" name="author" value="${requestScope.search_author}">
										<input type="hidden" name="target_servlet" value="SearchServlet">
										<input type="submit" value="カートに入れる">
									</form>
								</td>
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