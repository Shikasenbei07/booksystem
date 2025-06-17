<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.BookBL, model.Book" %>

<%
	String isbn = request.getParameter("id");
	BookBL bl = new BookBL();
	Book book = bl.findBookByKey(isbn);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍情報詳細</title>
</head>
<body>
	<h1><%= book.getTitle() %></h1>
	<h2><%= book.getAuthor() %>　著</h2>
	ISBN <%= book.getIsbn() %><br>
	発売日　<%= book.getReleaseDate() %><br>
	<h3>¥<%= book.getPrice() %>（税込）</h3>
	<button onclick="location.href='search_books.jsp'">戻る</button>
</body>
</html>