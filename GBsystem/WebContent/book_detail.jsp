<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*, java.time.LocalDate" %>

<%
	String isbn = request.getParameter("id");
	if (isbn == null) {
		response.sendRedirect("search_books.jsp");
		return;
	}
	Account ac = (Account) session.getAttribute("account");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>書籍情報詳細</title>
		<link rel="stylesheet" href="top_style.css" type="text/css">
	</head>
	<body>
		<%
			if (session.getAttribute("message") != null) {
		%>
				<div id="alertBox">${sessionScope.message}</div>
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

		<h1>${sessionScope.book['title']}</h1>
		<h2>${sessionScope.book['author']}　著</h2>
		ISBN　　${sessionScope.book['isbn']}<br>
		<%
			Book book = (Book) session.getAttribute("book");
		%>
		発売日　<%= book.getReleaseDate().toLocalDateTime().toLocalDate() %>
		<h3>¥<%= String.format("%,d", book.getPrice()) %>（税込）</h3>
		<form action="CartServlet" method="post">
			<input type="hidden" name="isbn" value="<%= book.getIsbn() %>">
			<input type="hidden" name="category" value="${sessionScope.search_category}">
			<input type="hidden" name="title" value="${sessionScope.search_title}">
			<input type="hidden" name="author" value="${sessionScope.search_author}">
			<input type="hidden" name="target_servlet" value="BookDetailServlet">
			<input type="submit" value="カートに入れる">
		</form>
		<button onclick="location.href='search_books.jsp'">戻る</button>
	</body>
</html>