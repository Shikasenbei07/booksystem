<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("message") == null) {
		response.sendRedirect("cart.jsp");
		return;
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入確認</title>
	</head>
	<body>
		<h1>${sessionScope.order_message}</h1>
		<button type="button" onclick="location.href='search_books.jsp'">検索トップへ</button>
	</body>
</html>