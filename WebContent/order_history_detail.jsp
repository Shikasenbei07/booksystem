<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.OrderHistoryDetail" %>
<%@ page import="model.OrderHistoryBL" %>
<%@ page import="model.Account" %>
    
<%
ArrayList<OrderHistoryDetail> detailList = (ArrayList<OrderHistoryDetail>)session.getAttribute("ohd");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴詳細</title>
</head>
<body>
		注文番号：${sessionScope.order_no}
		注文日	：${sessionScope.order_date}
			<table border="1">
			<tr>
				<th>書籍名</th>
				<th>税込価格</th>
				<th>注文数</th>
			</tr>
		<%
			for (OrderHistoryDetail ohd : detailList) {
		%>
				<tr>
					<td><%= ohd.getTitle() %></td>
					<td><%= ohd.getPrice() %></td>
					<td align="right"><%= ohd.getQuentity() %></td>
				</tr>
			<%
			}
			%>
				</table>
				
				合計金額：${sessionScope.total}


	<form action="OrderHistoryServlet" method="post">
		<input type="submit" value= "注文履歴一覧に戻る">
	</form>
	
	<button onclick="location.href='SearchServlet'">検索TOPへ</button>


</body>
</html>