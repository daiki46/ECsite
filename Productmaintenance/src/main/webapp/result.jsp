<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="style.css">
</head>	
<body>
<%@ include file="search-parts.jsp" %>
<%
	String productName = (String) request.getAttribute("productName");
	if(productName != null){		
		if(!(productName.equals(""))){
%>
			<p class="search-conditions">検索条件「<%=productName%>」</p>
<%
		}
	}
%>
	
	<h2>検索結果</h2>
<%
	List<Product> list = (List<Product>) session.getAttribute("result");
	if(list.size() > 0){
%>
		<table class="result-list">
			<tr>
				<th></th><th></th><th>商品ID</th><th>商品名</th><th>単価</th>
			</tr>
		
<%
			int cnt = 0;
			for(Product product : list){
%>
				<tr>
					<td>
						<form action="delete-servlet" method="POST">
							<input type="hidden" value=<%=cnt %> name="idx">
							<input type="submit" value="削除" >
						</form>	
					</td>
					<td>
						<form action="edit-screen-servlet" method="POST">
							<input type="hidden" value=<%=cnt %> name="idx">
							<input type="submit" value="編集">
						</form>
					</td>
					<td><%=product.getProductId() %></td>
					<td><%=product.getProductName() %></td>
					<td><%=product.getPrice() %></td>
				</tr>
<%
				cnt++;
			}
%>
	
		</table>

<%
	}
%>
<%@ include file="msg.jsp" %>
<br>
<form action="search.jsp">
	<input type="submit" value="検索画面へ戻る">
</form>
</body>
</html>