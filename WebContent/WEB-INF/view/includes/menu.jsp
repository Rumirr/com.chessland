<%@page import="com.chessland.model.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User user = (User) request.getAttribute("user");
%>



<nav class="navbar navbar-expand-sm navbar-dark bg-secondary fixed-top">
	<a class="navbar-brand" href="index.html"><img
		src="img/horse-15.svg" alt="Crown" id="logo"
		class="d-inline-block align-top"></a>
	<ul class="navbar-nav text-right">
		<li class="nav-item active"><a class="nav-link" href="index.html">Inicio</a></li>
	</ul>

	<%
		if (user == null) {
		out.append("<div class='g-signin2 ml-auto mr-2 mr-sm-0' data-onsuccess='onSignIn'></div>");
	} else {
		out.append(
		"<a href='Perfil' id='fotoPerfil' data-toggle='tooltip' data-placement='bottom' title='Perfil' class='ml-auto mr-2'>");
		out.append("<img src='" + user.getImgUrl() + "' alt='foto de perfil' class='rounded-circle'>");
		out.append("</a>");
		out.append("<button type='button' class='btn btn-outline-danger'>Logout</button>");
	}
	%>


</nav>