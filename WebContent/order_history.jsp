<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.OrderHistory" %>
<%@ page import="model.OrderHistoryBL" %>
<%@ page import="model.Account" %>

<%
ArrayList<OrderHistory> list = (ArrayList<OrderHistory>)session.getAttribute("oh");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文履歴一覧</title>
	</head>
	<body>
		<%
			if(list != null){
		%>
			<table border="1">
			<tr>
				<th>注文番号</th>
				<th>注文日</th>
				<th>支払い合計金額</th>
				<th></th>
			</tr>
		<%
			for (OrderHistory oh : list) {
		%>
				<tr>
					<td><%= oh.getOrder_no() %></td>
					<td><%= oh.getOrder_date().toLocalDateTime().toLocalDate() %></td>
					<td align="right"><%= oh.getTotal() %></td>
					<td>
						<form action="OrderHistoryDetailServlet" method="post">
						<input type="hidden" name="order_no" value="<%=oh.getOrder_no()%>">
						<input type="hidden" name="order_date" value="<%=oh.getOrder_date().toLocalDateTime().toLocalDate()%>">
							<input type="submit" value= "詳細">
						</form>
					</td>
					</tr>
			<%
			}
			%>
				</table>
			<%
				}else{
					%>
					注文履歴はありません。
					<%
				}
			%>

		<button onclick="location.href='SearchServlet'">検索TOPへ</button>
	</body>
</html>