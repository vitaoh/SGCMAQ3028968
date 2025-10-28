<%@page import="model.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuário</title>
    </head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <body>
        <%
            TipoUsuario tp = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));

                    tp = new TipoUsuario();
                    tp.setId(id);
                    tp.load();
                }
            }
        %>

        <div class="form-container">
            <h1>Tipo Usuário</h1>

            <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=tipousuario" method="post">

                <label for="id">ID</label>
                <input type="text" id="id" name="id" pattern="\d+" title="Apenas dígitos" value="<%= (tp != null) ? tp.getId() : ""%>" <%= (tp != null) ? "readonly" : ""%> required>

                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" value="<%= ((tp != null) && (tp.getNome() != null)) ? tp.getNome() : ""%>">

                <div class="checkbox-group">
                    <div class="checkbox-item">
                        <input type="checkbox" id="modulo_administrativo" name="modulo_administrativo" value="S"
                               <%= ((tp != null) && (tp.getModuloAdministrativo().equals("S"))) ? "checked" : ""%>>
                        <label for="modulo_administrativo">Módulo Administrativo</label>
                    </div>

                    <div class="checkbox-item">
                        <input type="checkbox" id="modulo_agendamento" name="modulo_agendamento" value="S"
                               <%= ((tp != null) && (tp.getModuloAgendamento().equals("S"))) ? "checked" : ""%>>
                        <label for="modulo_agendamento">Módulo Agendamento</label>
                    </div>

                    <div class="checkbox-item">
                        <input type="checkbox" id="modulo_atendimento" name="modulo_atendimento" value="S"
                               <%= ((tp != null) && (tp.getModuloAtendimento().equals("S"))) ? "checked" : ""%>>
                        <label for="modulo_atendimento">Módulo Atendimento</label>
                    </div>
                </div>
                <button type="submit" class="centralizado">Salvar</button>
            </form>
        </div>            
    <li>
        <a href="${pageContext.request.contextPath}/home/app/tipousuario.jsp" class="centralizado">Voltar</a>
    </li>
</body>
</html>