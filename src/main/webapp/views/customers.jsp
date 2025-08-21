<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customers - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
    <h1>Customers</h1>
    <a class="btn" href="../app/customers?action=new">Add New Customer</a>
    <table>
        <tr><th>Account #</th><th>Name</th><th>Address</th><th>Telephone</th><th>Units</th><th>Actions</th></tr>
        <%
            List<Customer> list = (List<Customer>) request.getAttribute("customers");
            if (list != null) {
                for (Customer c : list) {
        %>
            <tr>
                <td><%= c.getAccountNumber() %></td>
                <td><%= c.getName() %></td>
                <td><%= c.getAddress() %></td>
                <td><%= c.getTelephone() %></td>
                <td><%= c.getUnitsConsumed() %></td>
                <td>
                    <a href="../app/customers?action=edit&accountNumber=<%=c.getAccountNumber()%>">Edit</a> |
                    <a href="../app/customers?action=delete&accountNumber=<%=c.getAccountNumber()%>"
                        onclick="return confirm('Delete this customer?')">Delete</a>
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
