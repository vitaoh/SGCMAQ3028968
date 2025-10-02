<%@page import="model.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuário</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #2c3e50, #34495e);
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .form-container {
                background: #fff;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 4px 20px rgba(0,0,0,0.15);
                width: 100%;
                max-width: 500px;
            }

            h1 {
                text-align: center;
                margin-bottom: 25px;
                font-size: 2rem;
                color: #2c3e50;
            }

            label {
                display: block;
                margin: 12px 0 6px;
                font-weight: bold;
                color: #34495e;
            }

            input[type="text"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 1rem;
                margin-bottom: 15px;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus {
                border-color: #3498db;
                outline: none;
            }

            /* Checkboxes */
            .checkbox-group {
                margin: 15px 0;
            }

            .checkbox-group label {
                font-weight: normal;
                margin-left: 6px;
                color: #2c3e50;
            }

            .checkbox-group input {
                transform: scale(1.2);
                margin-right: 5px;
            }

            .btn-submit {
                width: 100%;
                padding: 12px;
                font-size: 1rem;
                font-weight: bold;
                background: #3498db;
                color: #fff;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: 0.3s;
            }

            .btn-submit:hover {
                background: #1f6fb2;
            }
        </style>
    </head>
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
        <h1>Tipo Usuario!</h1>
        <form action="<%= request.getContextPath()%>/home/tipousuario?action=<%= action%>" method="post">

            <label for="id">ID:</label>
            <input type="text" id="id" name="id" pattern="\d+" title="apenas dígitos" value="<%= (tp != null) ? tp.getId() : ""%>" <%= (tp != null) ? "readonly" : ""%> required>
            <br>

            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= ((tp != null) && (tp.getNome() != null)) ? tp.getNome() : ""%>">
            <br>

            <input type="checkbox" id="modulo_administrativo" name="modulo_administrativo" value="S" <%= ((tp != null) && (tp.getModuloAdministrativo().equals("S"))) ? "checked" : ""%>>
            <label for="modulo_administrativo">Modulo Administrativo:</label>
            <br>

            <input type="checkbox" id="modulo_agendamento" name="modulo_agendamento" value="S" <%= ((tp != null) && (tp.getModuloAgendamento().equals("S"))) ? "checked" : ""%>>
            <label for="modulo_agendamento">Modulo Agendamento:</label>
            <br>

            <input type="checkbox" id="modulo_atendimento" name="modulo_atendimento" value="S" <%= ((tp != null) && (tp.getModuloAtendimento().equals("S"))) ? "checked" : ""%>>
            <label for="modulo_atendimento">Modulo Atendimento:</label>
            <br>

            <input type="submit" name="Salvar" value="Salvar">
        </form>
    </body>
</html>