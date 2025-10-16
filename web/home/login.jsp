<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <script>
            alert("<%= msg%>");
        </script>
        <%
            }
        %>

        <%
            HttpSession sessao = request.getSession(false);
            if (sessao != null) {
                response.sendRedirect("home/app/menu.jsp");
            }
        %>

        <%
            int id = -1;
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("id")) {
                    id = Integer.parseInt(c.getValue());
                }
            }
        %>

        <div class="container">
            <div class="form-container">
                <h1>Login</h1>
                <form action="<%= request.getContextPath() %>/home?task=login" method="post">
                    <label for="id">ID:</label>
                    <input type="text" 
                           id="id" 
                           name="id"
                           pattern="\d+" 
                           title="apenas dígitos" 
                           value="<%= id != -1 ? id : "" %>"
                           required>
                    <br/> 
                    <label for="id">Senha:</label>
                    <input type="password" 
                           id="senha" 
                           name="senha" 
                           required>
                    <br/> 
                    <input type="submit" name="Login" value="Entrar">
                </form>
            </div>
        </div>
    </body>
</html>
