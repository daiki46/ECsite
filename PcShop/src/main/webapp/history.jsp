<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Sales" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="header-navi.jsp" %>
	
	<h2>購入履歴</h2>
	
	<%
		List<Sales> listSales = (List<Sales>)request.getAttribute("history");
		if(listSales != null){
	%>
			<table class="history-list">
				<tr>
					<th>売上ID</th><th>商品ID</th><th>商品名</th><th>単価</th><th>数量</th><th>購入日</th>
				</tr>
	<%
				for(Sales sales : listSales){
	%>
				<tr>
					<td><%=sales.getSalesId() %></td>
					<td><%=sales.getProductId() %></td>
					<td><%=sales.getProductName() %></td>
					<td><%=sales.getPriceString() %></td>
					<td><%=sales.getQuantity() %></td>
					<td><%=sales.getSalesDate() %></td>
				</tr>
	<%
				}
	%>
			</table>
			
	<%
		}
	%>
			
	<%@ include file="msg.jsp" %>
	
	
</body>
</html>