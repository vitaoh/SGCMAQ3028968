package controller;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TipoUsuario;

public class AutenticadorAdm implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession sessao = httpServletRequest.getSession(false);

        if (sessao == null) {
            httpServletRequest.setAttribute("msg", "Por favor, faça o login!");
            httpServletRequest.getRequestDispatcher("/home/login.jsp").forward(request, response);
            return; 
        }

        String usuario = (String) sessao.getAttribute("usuario");
        TipoUsuario tipoUsuario = (TipoUsuario) sessao.getAttribute("tipo_usuario");

        if (tipoUsuario == null || !tipoUsuario.getModuloAdministrativo().equals("S")) {
            httpServletRequest.setAttribute("msg", "Acesso negado! Você não tem permissão para acessar esta área.");
            httpServletRequest.getRequestDispatcher("/home/app/menu.jsp").forward(request, response);
            return;
        } else {
            chain.doFilter(request, response);
        }
    }
}
