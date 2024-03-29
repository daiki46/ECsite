<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h2>商品登録</h2>
	<form action="regist-servlet" method="POST">
		【商品ID】<br>
		<input type="text" name="productId" required><br><br>
		【商品名】<br>
		<input type="text" name="productName" required><br><br>
		【単価】<br>
		<input type="text" name="price" required><br><br>
		<input type="submit" value="登録">
	</form>
	<%@ include file="msg.jsp" %>
	<br>
	<p><a href="search.jsp">検索画面に戻る</a></p>
	
</body>
</html>