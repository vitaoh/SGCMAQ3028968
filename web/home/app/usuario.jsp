<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
    </head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <body>
        <% ArrayList<Usuario> dados = new Usuario().getAllTableEntities(); %>
        <div class="container">
            <h1>Usuario</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Tipo Usuario</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Usuario us : dados) {%>
                    <tr>
                        <td><%= us.getId()%></td>
                        <td><%= us.getNome()%></td>
                        <td><%= us.getCpf()%></td>
                        <td><%= us.getTipoUsuarioId()%></td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home/app/usuario_form.jsp?action=update&id=<%= us.getId()%>">Alterar</a>
                        </td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home?action=delete&id=<%= us.getId()%>&task=usuario" onclick="return confirm('Deseja excluir Usuario <%= us.getId()%> (<%= us.getNome()%>) ?')" >Excluir</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <a href="<%= request.getContextPath()%>/home/app/usuario_form.jsp?action=create" class="centralizado">Adicionar</a>
    </body>
</html>