<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.setAttribute("account_mode", "admin"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理者ログイン</title>
	</head>
	<body>
		<h2>管理者用ログインページ</h2>
		<form action="AdminLoginServlet" method="post">
			id　　　　　<input type="text" name="id"><br>
			パスワード<input type="password" name="pass"><br>
			<input type="submit" value="ログイン">
		</form>
	</body>
</html>