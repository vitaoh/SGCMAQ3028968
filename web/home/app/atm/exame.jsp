<%@page import="model.Exame"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exame</title>
    </head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <body>
        <!--        
            <div class="container">
                <%--<%@ include file="/home/app/modulos.jsp" %>--%>
            </div>
        -->
        <% ArrayList<Exame> dados = new Exame().getAllTableEntities(); %>
        <div class="container">
            <h1>Exame</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Descricao</th>
                        <th>Valor</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Exame ex : dados) {%>
                    <tr>
                        <td><%= ex.getId()%></td>
                        <td><%= ex.getNome()%></td>
                        <td><%= ex.getDescricao()%></td>
                        <td>R$ <%= ex.getValor()%></td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home/app/atm/exame_form.jsp?action=update&id=<%= ex.getId()%>">Alterar</a>
                        </td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home?action=delete&id=<%= ex.getId()%>&task=exame" onclick="return confirm('Deseja excluir Exame <%= ex.getId()%> (<%= ex.getNome()%>) ?')" >Excluir</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <a href="<%= request.getContextPath()%>/home/app/atm/exame_form.jsp?action=create" class="centralizado">Adicionar</a>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/home/app/menu.jsp" class="centralizado">Menu</a>
            </li>
        </ul>
    </body>
</html>