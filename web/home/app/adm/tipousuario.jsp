<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuário</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">    
    </head>
    <body>
        <!--        
            <div class="container">
                <%--<%@ include file="/home/app/modulos.jsp" %>--%>
            </div>
        -->
        <% ArrayList<TipoUsuario> dados = new TipoUsuario().getAllTableEntities(); %>
        <div class="container">
            <h1>Tipo Usuario</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Administrativo</th>
                        <th>Agendamento</th>
                        <th>Atendimento</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (TipoUsuario tp : dados) {%>
                    <tr>
                        <td><%= tp.getId()%></td>
                        <td><%= tp.getNome()%></td>
                        <td><%= tp.getModuloAdministrativo()%></td>
                        <td><%= tp.getModuloAgendamento()%></td>
                        <td><%= tp.getModuloAtendimento()%></td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home/app/adm/tipousuario_form.jsp?action=update&id=<%= tp.getId()%>">Alterar</a>
                        </td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home?action=delete&id=<%= tp.getId()%>&task=tipousuario" onclick="return confirm('Deseja excluir TipoUsuario <%= tp.getId()%> (<%= tp.getNome()%>) ?')" >Excluir</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <a href="<%= request.getContextPath()%>/home/app/adm/tipousuario_form.jsp?action=create" class="centralizado">Adicionar</a>
        <!--<div class="container">-->
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/home/app/menu.jsp" class="centralizado">Menu</a>
            </li>
        </ul>
        <!--</div>-->
    </body>
</html>