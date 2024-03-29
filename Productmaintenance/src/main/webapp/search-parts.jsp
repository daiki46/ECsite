<%@ page pageEncoding="UTF-8"%>
<%-- 検索パーツ --%>
<h2>商品検索</h2>
	【商品名】（部分一致）
	<form action="search-servlet" method="post">
		<input type="text" name="productName">
		<br>
		<input type="submit" value="検索">
	</form>
	<br>
	<form action="regist.jsp" method="post">
		<input type="submit" value="新規登録">
	</form>