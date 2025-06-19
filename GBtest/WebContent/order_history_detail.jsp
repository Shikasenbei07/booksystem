<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>

<%
	ArrayList<OrderHistoryDetail> details = (ArrayList<OrderHistoryDetail>) session.getAttribute("order_history_detail");
	if (details == null) {
		session.setAttribute("previous_page", "OrderHistoryServlet");
		response.sendRedirect("LoginServlet");
		return;
	}

	int total = (Integer) session.getAttribute("total_price");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文履歴詳細</title>
	</head>
	<body>
		<h2>注文詳細</h2>
		<%
			if (details == null) {
		%>
				注文情報がありません<br>
		<%
			} else {
		%>
				注文番号：<%= details.get(0).getOrderNo() %>
				<table border="1">
					<tr>
						<th>書籍名</th>
						<th>単価</th>
						<th>注文数</th>
					</tr>
					<%
						for (OrderHistoryDetail o : details) {
					%>
							<tr>
								<td><%= o.getTitle() %></td>
								<td align="right">¥<%= String.format("%,d", o.getPrice()) %></td>
								<td align="right"><%= o.getQuantity() %></td>
							</tr>
					<%
						}
					%>
				</table>
				<h2>合計金額：¥<%= String.format("%,d", total) %></h2>
		<%
			}
		%>
	</body>
	<button type="button" onclick="location.href='OrderHistoryServlet'">戻る</button><br>
	<button type="button" onclick="location.href='SearchServlet'">検索トップへ</button>
</html>