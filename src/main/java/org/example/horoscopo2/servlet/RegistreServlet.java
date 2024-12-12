package org.example.horoscopo2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.horoscopo2.dto.UsuarioCreateDto;
import org.example.horoscopo2.service.UsuarioService;
import org.example.horoscopo2.service.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name ="RegistreServlet", value = "/register")
public class RegistreServlet extends HttpServlet {
    private final UsuarioService usuarioService;

    public RegistreServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        Date fechaNacimiento = Date.valueOf((req.getParameter("fechaNacimiento")));
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        UsuarioCreateDto usuario = new UsuarioCreateDto(nombre, username, email, fechaNacimiento, password);
        if (usuarioService.registarUsuario(usuario, confirmPassword)) {
            resp.sendRedirect("login.jsp");
        } else {
            resp.sendRedirect("index.jsp?error=1");
        }
    }
}
