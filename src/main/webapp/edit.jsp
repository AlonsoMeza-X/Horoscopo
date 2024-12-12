<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.horoscopo2.dto.UsuarioResponseDto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Editar Usuario</h1>

    <%
        UsuarioResponseDto usuario = (UsuarioResponseDto) request.getAttribute("usuario");
        if (usuario != null) {
    %>
    <form action="${pageContext.request.contextPath}/edit" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="<%= usuario.getUsername() %>" required>
        </div>
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= usuario.getNombre() %>" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= usuario.getEmail() %>" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="mb-3">
            <label for="confirmPassword" class="form-label">Confirmar Contraseña</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
        <a href="${pageContext.request.contextPath}/profile.jsp" class="btn btn-secondary">Cancelar</a>
    </form>
    <%
    } else {
    %>
    <p>No se encontró el usuario para editar.</p>
    <%
        }
    %>

    <% if ("1".equals(request.getParameter("error"))) { %>
    <div class="alert alert-danger" role="alert">
        Hubo un error al actualizar los datos. Intenta de nuevo.
    </div>
    <% } else if ("2".equals(request.getParameter("error"))) { %>
    <div class="alert alert-danger" role="alert">
        Las contraseñas no coinciden. Intenta de nuevo.
    </div>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
