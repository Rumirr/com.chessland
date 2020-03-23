<%@page import="com.chessland.model.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Chess Land</title>
</head>
<body>

	<h1><%=user.getEmail()%></h1>
	<p>Locale -> <%=user.getLocale() %></p>
	<p>Nombre -> <%=user.getName() %></p>
	<p>Apellido -> <%=user.getSurname() %></p>
	<img alt="" src="<%=user.getImgUrl() %>">
</body>
</html>