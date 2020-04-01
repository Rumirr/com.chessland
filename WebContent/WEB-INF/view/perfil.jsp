<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="includes/head.jsp" %>
</head>
<body>

	<%@include file="includes/menu.jsp" %>
	
	<div class="container mt-5 pt-5">
        <img src="<%=user.getImgUrl() %>" alt="Foto de perfil" width="100" class="mx-auto d-block rounded-circle" id="imgPerfil">
        <button type="button" class="btn btn-block btn-outline-warning mt-2" onclick="changePhoto();">Cambiar foto</button>
        <h3 class="text-center mt-4">Estadísticas</h3>
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">Partidas totales
                <span class="badge badge-primary">10</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">Empates
                <span class="badge badge-info">1</span>

            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">Partidas perdidas
                <span class="badge badge-danger">6</span>

            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">Partidas ganadas
                <span class="badge badge-success">7</span>

            </li>
        </ul>
        <h3 class="text-center mt-4">Lista de partidas jugadas anteriormente</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Color pieza</th>
                    <th>Estado</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td scope="row" class="align-middle">10/10/2010</td>
                    <td class="align-middle">Blancas</td>
                    <td class="text-danger align-middle">Perdido</td>
                    <td class="align-middle"><button type="button" class="btn btn-block btn-outline-secondary">Ver jugadas</button></td>
                </tr>
                <tr>
                    <td scope="row" class="align-middle">10/10/2010</td>
                    <td class="align-middle">Negras</td>
                    <td class="text-success align-middle">Ganado</td>
                    <td class="align-middle"><button type="button" class="btn btn-block btn-outline-secondary">Ver jugadas</button></td>
                </tr>
            </tbody>
        </table>
    </div>
	
	<%@include file="includes/footer.jsp" %>
</body>
</html>