<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if (request.getAttribute("toLogin") == null) {
		response.sendRedirect("LoginServlet");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン</title>
</head>
<body>
	${requestScope.login_message}<br>
	<form action="LoginServlet" method="post">
		メールアドレス<input type="email" name="mail" required><br>
		パスワード　　<input type="password" name="pass" required><br>
		<input type="submit" value="ログイン">
	</form>
	<hr>
	<button type="button" onclick="location.href='SignupServlet'">新規登録</button><br>
	<br>
	<button type="button" onclick="location.href='SearchServlet'">検索TOPへ</button>
</body>
</html>