<%@page import="model.TipoUsuario"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String usuarioLogado = (String) session.getAttribute("usuario");
    TipoUsuario tipoUsuarioLogado = (TipoUsuario) session.getAttribute("tipo_usuario");

    if ((usuarioLogado == null)
            || (tipoUsuarioLogado == null)) {
        response.sendRedirect(request.getContextPath() + "/home/login.jsp");
    }
%>
<h1>Menu</h1>
<menu>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/home/app/menu.jsp">Menu</a>
        </li>

        <% if (tipoUsuarioLogado.getModuloAdministrativo().equals("S")) { %>
            <li>
                <a href="${pageContext.request.contextPath}/home/app/tipousuario.jsp">Tipo Usuário</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/home/app/usuario.jsp">Usuários</a>
            </li>
        <% } %>

        <li>
            <a href="${pageContext.request.contextPath}/home?task=logout">Logout <%= usuarioLogado %></a>
        </li>
    </ul>
</menu>

