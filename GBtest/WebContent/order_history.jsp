<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>

<%
	ArrayList<OrderHistory> histories = (ArrayList<OrderHistory>) session.getAttribute("order_history");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文履歴</title>
	</head>
	<body>
		<h2>注文履歴一覧</h2>
		<%
			if (histories == null) {
		%>
				注文情報がありません<br>
		<%
			} else {
		%>
				<table border="1">
					<tr>
						<th>注文番号</th>
						<th>注文日</th>
						<th>合計金額</th>
						<th></th>
					</tr>
					<%
						for (OrderHistory o : histories) {
					%>
							<tr>
								<td><%= o.getOrderNo() %></td>
								<td><%= o.getOrderDate().toLocalDateTime().toLocalDate() %></td>
								<td align="right">¥<%= String.format("%,d", o.getTotal()) %></td>
								<td>
									<form action="OrderHistoryDetailServlet" method="post">
										<input type="hidden" name="order_no" value="<%= o.getOrderNo() %>">
										<input type="submit" value="詳細">
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
	<button type="button" onclick="location.href='SearchServlet'">検索トップへ</button>
</html>