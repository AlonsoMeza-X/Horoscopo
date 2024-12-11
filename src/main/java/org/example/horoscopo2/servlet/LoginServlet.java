package org.example.horoscopo2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.horoscopo2.dto.UsuarioResponseDto;
import org.example.horoscopo2.service.UsuarioService;
import org.example.horoscopo2.service.UsuarioServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UsuarioService usuarioService;


    public LoginServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        if(usuarioService.autentificarUsuario(usuario, password)) {
            Optional<UsuarioResponseDto> usuarioResponseDtoOptional = usuarioService.findUsuarioByUsername(usuario);
            List<UsuarioResponseDto> usuarios = usuarioService.findAll();


            HttpSession session = req.getSession();
            session.setAttribute("username", usuarioResponseDtoOptional.get().getUsername());
            session.setAttribute("ListaUsuarios", usuarios);
            session.setAttribute("usuarioActual", usuarioResponseDtoOptional);

            resp.sendRedirect("bienvenida.jsp");
        } else {
            resp.sendRedirect("login.jsp?error=1");
        }

    }
}
