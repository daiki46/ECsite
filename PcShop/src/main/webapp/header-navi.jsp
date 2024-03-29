<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Cart" %>
<%@ page import="model.Store" %>
<%-- 店舗名・ユーザIDの表示 --%>
<%
	Store storeHdr = (Store) session.getAttribute("store");
	Cart cartHdr = (Cart) session.getAttribute("cart");
	if((storeHdr == null) || cartHdr == null){
		request.setAttribute("errorMsg", "再ログインをお願いします。");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}else{
%>
		<h1><%=storeHdr.getName() %></h1>
		<hr>
		ユーザID：<%=cartHdr.getUserId() %>
		<hr>
		
		<%-- ナビ表示 --%>
		<ul>
			<li><a href="select.jsp">商品選択</a></li>
			<li><a href="cart.jsp">カート内一覧</a></li>
			<li><a href="get-history-servlet">購入履歴</a></li>
			<li><a href="chg-pass.jsp">パスワード変更</a></li>
			<li><a href="logout-servlet">ログアウト</a></li>
		</ul>
			<hr>
<%
	}
%>