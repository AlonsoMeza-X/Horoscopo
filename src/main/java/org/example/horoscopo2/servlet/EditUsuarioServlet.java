package org.example.horoscopo2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.horoscopo2.dto.UsuarioCreateDto;
import org.example.horoscopo2.dto.UsuarioResponseDto;
import org.example.horoscopo2.service.UsuarioService;
import org.example.horoscopo2.service.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@WebServlet(name = "EditUsuarioServlet", value = "/edit")
public class EditUsuarioServlet extends HttpServlet {
    private final UsuarioService usuarioService;

    public EditUsuarioServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        if (username != null) {
            // Obtener los datos del usuario desde el servicio
            Optional<UsuarioResponseDto> usuarioResponseDto = usuarioService.findUsuarioByUsername(username);


            if (usuarioResponseDto.isPresent()) {
                // Pasar los datos del usuario al JSP
                req.setAttribute("usuario", usuarioResponseDto.get());
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
            } else {
                // Redirigir al login si no se encuentra el usuario
                resp.sendRedirect("login.jsp");
            }
        } else {
            // Redirigir al login si no hay sesión activa
            resp.sendRedirect("login.jsp");
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        UsuarioCreateDto usuario = new UsuarioCreateDto(id, nombre, username, email, password);
        System.out.println(usuario);
        if (usuarioService.update(usuario, confirmPassword)) {
            resp.sendRedirect("login.jsp");
        } else {
            resp.sendRedirect("edit.jsp?error=1");
        }
    }
}
