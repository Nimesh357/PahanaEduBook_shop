<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Items - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
    <h1>Items</h1>
    <a class="btn" href="../app/items?action=new">Add New Item</a>
    <table>
        <tr><th>ID</th><th>Name</th><th>Description</th><th>Unit Price (LKR)</th><th>Actions</th></tr>
        <%
            List<Item> list = (List<Item>) request.getAttribute("items");
            if (list != null) {
                for (Item i : list) {
        %>
            <tr>
                <td><%= i.getId() %></td>
                <td><%= i.getName() %></td>
                <td><%= i.getDescription() %></td>
                <td><%= String.format("%.2f", i.getUnitPrice()) %></td>
                <td>
                    <a href="../app/items?action=edit&id=<%=i.getId()%>">Edit</a> |
                    <a href="../app/items?action=delete&id=<%=i.getId()%>"
                        onclick="return confirm('Delete this item?')">Delete</a>
                </td>
            </tr>
        <%
                }
            }
        %>
    </table>
</div>
</body>
</html>
