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

public class AutenticadorAtm implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession sessao = httpServletRequest.getSession(false);

        TipoUsuario tipoUsuario = (TipoUsuario) sessao.getAttribute("tipo_usuario");

        if (tipoUsuario == null 
                || !tipoUsuario.getModuloAtendimento().equals("S")) {
            httpServletRequest.setAttribute("msg", "Acesso negado! Você não tem permissão para acessar esta área.");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/home/app/menu.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
