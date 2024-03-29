<%@ page pageEncoding="UTF-8"%>
<%-- エラーメッセージ --%>
	<%
		String errorMsg = (String)request.getAttribute("errorMsg") ;
		if(errorMsg != null){
	%>
			<p class="error-msg"><%= errorMsg %></p>
	<%
		}
	%>
