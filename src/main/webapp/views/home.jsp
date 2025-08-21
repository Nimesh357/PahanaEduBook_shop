<%@ page import="com.pahanaedu.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Pahana Edu BookShop</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<% com.pahanaedu.model.User u = (User) session.getAttribute("user"); %>
<div class="topbar">
    <div>Welcome, <b><%= u != null ? u.getFullName() : "" %></b></div>
    <div><a href="../logout">Logout</a></div>
</div>

<div class="container">
    <h1>Dashboard</h1>
    <div class="grid">
        <a class="card" href="../app/customers?action=list">Manage Customers</a>
        <a class="card" href="../app/items?action=list">Manage Items</a>
        <a class="card" href="../app/bills?action=list">Billing</a>
        <a class="card" href="help.jsp">Help</a>
    </div>
</div>
</body>
</html>
