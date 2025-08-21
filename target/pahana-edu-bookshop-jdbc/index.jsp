<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu Bookshop (JDBC) - Login</title>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Pahana Edu Bookshop (JDBC)</h1>
    <h2>Login</h2>
    <form method="post" action="login">
        <label>Username</label>
        <input type="text" name="username" required />
        <label>Password</label>
        <input type="password" name="password" required />
        <button type="submit">Login</button>
    </form>
    <div class="msg">
        <% String err = request.getParameter("error"); if (err != null) { %>
            <div class="error"><%= err %></div>
        <% } %>
        <% String msg = request.getParameter("msg"); if (msg != null) { %>
            <div class="info"><%= msg %></div>
        <% } %>
    </div>
    <p class="help-link"><a href="views/help.jsp">Help</a></p>
</div>
</body>
</html>
