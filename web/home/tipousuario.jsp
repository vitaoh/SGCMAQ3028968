<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuário</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                background: linear-gradient(135deg, #2c3e50, #34495e);
                color: #ecf0f1;
                padding: 30px;
            }

            .container {
                width: 95%;
                max-width: 1100px;
                margin: auto;
                background: #1e272e;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0 4px 20px rgba(0,0,0,0.4);
            }

            h1 {
                font-size: 2.2rem;
                text-align: center;
                margin-bottom: 20px;
                color: #f1c40f;
                letter-spacing: 1px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background: #fff;
                border-radius: 10px;
                overflow: hidden;
            }

            thead {
                background: #3498db;
                color: #fff;
            }

            th, td {
                padding: 14px 12px;
                text-align: center;
                font-size: 0.95rem;
                border-bottom: 1px solid #ddd;
            }

            tr:nth-child(even) {
                background: #f9f9f9;
            }

            tr:hover {
                background: #ecf0f1;
                transition: 0.3s ease-in-out;
            }

            .btn {
                display: inline-block;
                padding: 8px 14px;
                margin: 2px;
                border-radius: 6px;
                text-decoration: none;
                font-size: 0.9rem;
                font-weight: 500;
                transition: 0.2s;
            }

            .btn-edit {
                background: #27ae60;
                color: #fff;
            }

            .btn-edit:hover {
                background: #219150;
            }

            .btn-delete {
                background: #e74c3c;
                color: #fff;
            }

            .btn-delete:hover {
                background: #c0392b;
            }

            .btn-add {
                background: #2980b9;
                color: #fff;
                display: block;
                text-align: center;
                width: fit-content;
                margin: 20px auto 0;
            }

            .btn-add:hover {
                background: #21618c;
            }

            @media (max-width: 768px) {
                table {
                    font-size: 0.8rem;
                }

                th, td {
                    padding: 10px 8px;
                }

                h1 {
                    font-size: 1.6rem;
                }
            }
        </style>
    </head>
    <body>
        <% ArrayList<TipoUsuario> dados = new TipoUsuario().getAllTableEntities(); %>
        <h1>Tipo Usuario</h1>
        <div class="container">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Administrativo</th>
                        <th>Agendamento</th>
                        <th>Atendimento</th>
                        <th>Ações</th>
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
                            <a href="<%= request.getContextPath()%>/home/tipousuario_form.jsp?action=update&id=<%= tp.getId()%>">Alterar</a>
                            <a href="<%= request.getContextPath()%>/home/tipousuario?action=delete&id=<%= tp.getId()%>" onclick="return confirm('Deseja excluir TipoUsuario <%= tp.getId()%> (<%= tp.getNome()%>) ?')" >Excluir</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <a href="<%= request.getContextPath()%>/home/tipousuario_form.jsp?action=create">Adicionar</a>
    </body>
</html>