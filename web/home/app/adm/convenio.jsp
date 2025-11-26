<%@page import="model.Convenio"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Convenio</title>
    </head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <body>
        <!--        
            <div class="container">
        <%--<%@ include file="/home/app/modulos.jsp" %>--%>
    </div>
        -->
        <% ArrayList<Convenio> dados = new Convenio().getAllTableEntities(); %>
        <div class="container">
            <h1>Convenio</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CNPJ</th>
                        <th>Telefone</th>
                        <th>Valor</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Convenio cv : dados) {%>
                    <tr>
                        <td><%= cv.getId()%></td>
                        <td><%= cv.getNome()%></td>
                        <td><%= cv.getCnpj() != null ? cv.getCnpj() : "-"%></td>
                        <td><%= cv.getTelefone() != null ? cv.getTelefone() : "-"%></td>
                        <td>R$ <%= cv.getValor()%></td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home/app/adm/convenio_form.jsp?action=update&id=<%= cv.getId()%>">Alterar</a>
                        </td>
                        <td> 
                            <a href="<%= request.getContextPath()%>/home?action=delete&id=<%= cv.getId()%>&task=convenio" onclick="return confirm('Deseja excluir Convenio <%= cv.getId()%> (<%= cv.getNome()%>) ?')" >Excluir</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <a href="<%= request.getContextPath()%>/home/app/adm/convenio_form.jsp?action=create" class="centralizado">Adicionar</a>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/home/app/menu.jsp" class="centralizado">Menu</a>
            </li>
        </ul>
    </body>
</html>