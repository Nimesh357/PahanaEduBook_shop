<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Bill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bills - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
    <h1>Bills</h1>
    <a class="btn" href="../app/bills?action=new">Create New Bill</a>
    <table>
        <tr><th>ID</th><th>Customer</th><th>Created At</th><th>Total Units</th><th>Amount (LKR)</th><th>Actions</th></tr>
        <%
            List<Bill> list = (List<Bill>) request.getAttribute("bills");
            if (list != null) {
                for (Bill b : list) {
        %>
            <tr>
                <td><%= b.getId() %></td>
                <td><%= b.getCustomerName() %> (#<%= b.getCustomerAccountNumber() %>)</td>
                <td><%= b.getCreatedAt() %></td>
                <td><%= (int)b.getTotalUnitsConsumed() %></td>
                <td><%= String.format("%.2f", b.getTotalAmount()) %></td>
                <td>
                    <a href="../app/bills?action=print&id=<%=b.getId()%>" target="_blank">Print</a>
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
