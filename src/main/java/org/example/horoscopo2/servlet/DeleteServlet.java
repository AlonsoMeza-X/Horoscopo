package org.example.horoscopo2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.horoscopo2.service.UsuarioService;
import org.example.horoscopo2.service.UsuarioServiceImpl;

import java.io.IOException;

@WebServlet(name ="DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    private final UsuarioService usuarioService;

    public DeleteServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        usuarioService.eliminarUsuario(id);

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("login.jsp?logout=success");
    }
}
