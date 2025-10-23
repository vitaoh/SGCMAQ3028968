<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<h1>Menu</h1>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/home/app/menu.jsp">Menu</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/home/app/tipousuario.jsp">Tipo Usuário</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/home/app/usuario.jsp">Usuários</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/home?task=logout">Logout <%= session.getAttribute("usuario") %></a>
    </li>
</ul>
