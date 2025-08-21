<%@ page import="com.pahanaedu.model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item Form - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
    <h1>Item</h1>
    <%
        Item i = (Item) request.getAttribute("item");
        long id = i != null ? i.getId() : 0;
    %>
    <form method="post" action="../app/items">
        <label>ID</label>
        <input type="number" name="id" value="<%= id %>" required />
        <label>Name</label>
        <input type="text" name="name" value="<%= i != null ? i.getName() : "" %>" required />
        <label>Description</label>
        <input type="text" name="description" value="<%= i != null ? i.getDescription() : "" %>" />
        <label>Unit Price (LKR)</label>
        <input type="number" step="0.01" name="unitPrice" value="<%= i != null ? i.getUnitPrice() : 0 %>" required />
        <button type="submit">Save</button>
        <a class="btn" href="../app/items?action=list">Cancel</a>
    </form>
</div>
</body>
</html>
