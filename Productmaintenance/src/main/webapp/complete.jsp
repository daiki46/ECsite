<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メッセージ</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<%
	String msg = (String)request.getAttribute("msg");
%>
	<h2>メッセージ</h2>
	<p><%=msg %>処理が完了しました。</p>
	<a href="search.jsp">検索画面に戻る</a>
</body>
</html>