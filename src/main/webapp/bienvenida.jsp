<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.horoscopo2.dto.UsuarioResponseDto" %>
<%@ page import="java.util.Optional" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login");
        return;
    }

    List<UsuarioResponseDto> usuarios = (List<UsuarioResponseDto>) session.getAttribute("ListaUsuarios");
    Optional<UsuarioResponseDto> usuarioActual = (Optional<UsuarioResponseDto>) session.getAttribute("usuarioActual");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenida</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title text-center mb-4">Bienvenido, <%= usuarioActual.get().getUsername() %></h2>
                    <p class="text-center">Has iniciado sesión correctamente.</p>

                    <!-- Información del usuario actual -->
                    <div class="card mb-4">
                        <div class="card-header">
                            Tu información
                        </div>
                        <div class="card-body">
                            <p><strong>Username:</strong> <%= usuarioActual.get().getUsername() %></p>
                            <p><strong>Email:</strong> <%= usuarioActual.get().getEmail() %></p>
                            <p><strong>Nickname:</strong> <%= usuarioActual.get().getUsername() %></p>
                        </div>
                    </div>

                    <!-- Tabla de usuarios -->
                    <div class="card">
                        <div class="card-header">
                            Usuarios registrados
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Nickname</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% if (usuarios != null && !usuarios.isEmpty()) {
                                        for (UsuarioResponseDto usuario : usuarios) { %>
                                    <tr class="<%= usuario.getUsername().equals(usuarioActual.get().getUsername()) ? "table-primary" : "" %>">
                                        <td><%= usuario.getUsername() %></td>
                                        <td><%= usuario.getEmail() %></td>
                                        <td><%= usuario.getUsername() %></td>
                                    </tr>
                                    <% }
                                    } else { %>
                                    <tr>
                                        <td colspan="3" class="text-center">No hay usuarios registrados</td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="d-grid gap-2 mt-4">
                        <a href="logout" class="btn btn-danger">Cerrar sesión</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
