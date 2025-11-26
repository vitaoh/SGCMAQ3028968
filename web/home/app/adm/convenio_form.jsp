<%@page import="model.Convenio"%>
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
        <%
            Convenio cv = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));

                    cv = new Convenio();
                    cv.setId(id);
                    cv.load();
                }
            }
        %>
        <div class="form-container">
            <h1><%= action.equals("create") ? "Adicionar" : "Editar" %> Convenio</h1>
            <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=convenio" method="post">

                <label for="id">ID:</label>
                <input type="text" 
                       id="id" 
                       name="id" 
                       pattern="\d+" 
                       title="apenas dígitos" 
                       value="<%= (cv != null) ? cv.getId() : ""%>" <%= (cv != null) ? "readonly" : ""%> required>
                <br>

                <label for="nome">Nome:</label>
                <input type="text" 
                       id="nome" 
                       name="nome" 
                       value="<%= ((cv != null) && (cv.getNome() != null)) ? cv.getNome() : ""%>" required>
                <br>

                <label for="cnpj">CNPJ:</label>
                <input type="text" 
                       id="cnpj" 
                       name="cnpj" 
                       pattern="\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}" 
                       title="DD.DDD.DDD/DDDD-DD" 
                       value="<%= ((cv != null) && (cv.getCnpj() != null)) ? cv.getCnpj() : ""%>">
                <br>

                <label for="telefone">Telefone:</label>
                <input type="text" 
                       id="telefone" 
                       name="telefone"
                       pattern="^(55)?(?:([1-9]{2})?)(\d{4,5})(\d{4})$"
                       title="(00)00000-0000" 
                       value="<%= ((cv != null) && (cv.getTelefone() != null)) ? cv.getTelefone() : ""%>">
                <br>

                <label for="valor">Valor:</label>
                <input type="text" 
                       id="valor" 
                       name="valor" 
                       value="<%= ((cv != null) && (cv.getValor() != null)) ? cv.getValor() : ""%>" required>
                <br>

                <input type="submit" name="Salvar" value="Salvar" class="centralizado">
            </form>
        </div>
    <li>
        <a href="${pageContext.request.contextPath}/home/app/adm/convenio.jsp" class="centralizado">Voltar</a>
    </li>
</body>
</html>