<%@page import="model.Exame"%>
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
        <%
            Exame ex = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));

                    ex = new Exame();
                    ex.setId(id);
                    ex.load();
                }
            }
        %>
        <div class="form-container">
            <h1> <%= action.equals("create") ? "Adicionar" : "Editar"%> Exame</h1>
            <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=exame" method="post">

                <label for="id">ID:</label>
                <input type="text" 
                       id="id" 
                       name="id" 
                       pattern="\d+" 
                       title="apenas dígitos" 
                       value="<%= (ex != null) ? ex.getId() : ""%>" <%= (ex != null) ? "readonly" : ""%> 
                       required>
                <br>

                <label for="nome">Nome:</label>
                <input type="text" 
                       id="nome" 
                       name="nome" 
                       value="<%= ((ex != null) && (ex.getNome() != null)) ? ex.getNome() : ""%>" 
                       required>
                <br>

                <label for="descricao">Descricao:</label>
                <textarea id="descricao" 
                          name="descricao" 
                          rows="3" 
                          cols="40" 
                          required><%= (ex != null && ex.getDescricao() != null) ? ex.getDescricao() : ""%></textarea>
                <br>

                <label for="valor">Valor:</label>
                <input type="text" 
                       id="valor" 
                       name="valor" 
                       value="<%= ((ex != null) && (ex.getValor() != null)) ? ex.getValor() : ""%>" 
                       required>
                <br>

                <input type="submit" name="Salvar" value="Salvar" class="centralizado">
            </form>
        </div>
    <li>
        <a href="${pageContext.request.contextPath}/home/app/atm/exame.jsp" class="centralizado">Voltar</a>
    </li>
</body>
</html>