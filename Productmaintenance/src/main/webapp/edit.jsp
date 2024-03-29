<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集画面</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<%
	String productId = (String)request.getAttribute("productId");
	String productName = (String)request.getAttribute("productName");
	String price = String.valueOf(request.getAttribute("price"));
%>
	<h2>商品情報編集</h2>
	<form action="edit-servlet" method="POST">
		【商品ID】<br>
		<input type="text" name="productId" value="<%=productId %>" readonly><br><br>
		【商品名】<br>
		<input type="text" name="productName" value="<%=productName %>" required><br><br>
		【単価】<br>
		<input type="text" name="price" value="<%=price %>" required><br><br>
		<input type="submit" value="登録">
	</form>
	<%@ include file="msg.jsp" %>
	<br>
	<p><a href="search.jsp">検索画面に戻る</a></p>
</body>
</html>