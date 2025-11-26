<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
    </head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <body>
        <!--        
            <div class="container">
        <%--<%@ include file="/home/app/modulos.jsp" %>--%>
    </div>
        -->
        <%
            Usuario us = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));

                    us = new Usuario();
                    us.setId(id);
                    us.load();
                }
            }
        %>
        <div class="form-container">
            <h1><%= action.equals("create") ? "Adicionar" : "Editar"%> Usuario</h1>
            <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=usuario" method="post">

                <label for="id">ID:</label>
                <input type="text" 
                       id="id" 
                       name="id" 
                       pattern="\d+" 
                       title="apenas dígitos" 
                       value="<%= (us != null) ? us.getId() : ""%>" <%= (us != null) ? "readonly" : ""%> required>
                <br>

                <label for="nome">Nome:</label>
                <input type="text" 
                       id="nome" 
                       name="nome" 
                       value="<%= ((us != null) && (us.getNome() != null)) ? us.getNome() : ""%>">
                <br>

                <label for="cep">CEP:</label>
                <input type="text" 
                       id="cep" 
                       name="cep"
                       pattern="\d{5}-d\{3}"
                       title="DDDDD-DDD">
                <input type="button"
                       id="buscar_por_cep"
                       name="buscar_por_cep"
                       value="Buscar endereço">
                <br>

                <label for="endereco">Endereço:</label>
                <textarea id="endereco" 
                          name="endereco" 
                          rows="3" 
                          cols="40" 
                          required><%= (us != null && us.getEndereco() != null) ? us.getEndereco() : ""%></textarea>
                <br>

                <label for="senha">Senha:</label>
                <input type="password" 
                       id="senha" 
                       name="senha" 
                       value="<%= ((us != null)) ? us.getSenha() : ""%>" required>
                <br>

                <label for="cpf">CPF:</label>
                <input type="text" 
                       id="cpf" 
                       name="cpf" 
                       pattern="\d{3}\.d\{3}\.d{3}-\d{2}" 
                       title="DDD.DDD.DDD-DD" 
                       value="<%= ((us != null) && (us.getCpf() != null)) ? us.getCpf() : ""%>">
                <br>

                <label for="tipoUsuario">Tipo Usuario:</label>
                <input type="text" 
                       id="tipoUsuario" 
                       name="tipoUsuario" 
                       pattern="\d+" 
                       title="apenas dígitos" 
                       value="<%= (us != null) ? us.getTipoUsuarioId() : ""%>" <%= (us != null) ? "readonly" : ""%> required>
                <br>

                <label for="convenio">Convênio:</label>
                <input type="text" 
                       id="convenio" 
                       name ="convenio" 
                       pattern="\d+" 
                       title="apenas dígitos"
                       value="<%= ((us != null) && (us.getConvenioId() != 0)) ? us.getConvenioId() : ""%>">
                <br/>

                <input type="submit" name="Salvar" value="Salvar" class="centralizado">
            </form>
        </div>
    <li>
        <a href="${pageContext.request.contextPath}/home/app/adm/usuario.jsp" class="centralizado">Voltar</a>
    </li>
</body>
</html>