<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Bill - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
    <h1>Create Bill</h1>
    <form method="post" action="../app/bills">
        <label>Customer Account Number</label>
        <input type="number" name="accountNumber" required />

        <h3>Items</h3>
        <table>
            <tr><th>ID</th><th>Name</th><th>Unit Price (LKR)</th><th>Quantity</th></tr>
            <%
                List<Item> items = (List<Item>) request.getAttribute("items");
                if (items != null) {
                    for (Item it : items) {
            %>
            <tr>
                <td><%= it.getId() %></td>
                <td><%= it.getName() %></td>
                <td><%= String.format("%.2f", it.getUnitPrice()) %></td>
                <td><input type="number" min="0" name="qty_<%= it.getId() %>" value="0"/></td>
            </tr>
            <%      }
                }
            %>
        </table>
        <button type="submit">Generate Bill</button>
        <a class="btn" href="../app/bills?action=list">Cancel</a>
        <div class="msg">
            <% String error = request.getParameter("error");
               if (error != null) { %>
               <div class="error"><%= error %></div>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>
