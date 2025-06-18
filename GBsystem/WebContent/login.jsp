<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン</title>
</head>
<body>
	${requestScope.login_message}<br>
	<form action="LoginServlet" method="post">
		メールアドレス<input type="email" name="mail"><br>
		パスワード　　<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
	<hr>
	<button type="button" onclick="location.href='signup.jsp'">新規登録</button>
</body>
</html>