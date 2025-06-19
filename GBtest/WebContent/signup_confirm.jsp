<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="account" class="model.Account" type="model.Account" scope="session" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登録情報確認</title>
	</head>
	<body>
		<h2>以下の内容で登録しますか？</h2>
		メールアドレス：${account.email}<br>
		パスワード：${requestScope.hidden_pass}<br>
		氏名：${account.lastName} ${account.firstName}<br>
		住所：${account.prefecture}${account.city}${account.address1}${account.address2}<br>
		電話番号：${account.phone}
		<form action="SignupConfirmServlet" method="post">
			<input type="submit" value="登録">
		</form>
	</body>
</html>