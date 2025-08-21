<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Bill" %>
<%@ page import="com.pahanaedu.model.BillItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Print Bill - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css" media="all">
    <style>
        @media print {
            .no-print { display: none; }
            body { padding: 0; margin: 0; }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="no-print"><a class="btn" href="javascript:window.print()">Print</a></div>
    <%
        Bill bill = (Bill) request.getAttribute("bill");
    %>
    <h1>Pahana Edu Bookshop</h1>
    <h2>Invoice #<%= bill.getId() %></h2>
    <p><b>Customer:</b> <%= bill.getCustomerName() %> (#<%= bill.getCustomerAccountNumber() %>)</p>
    <p><b>Date:</b> <%= bill.getCreatedAt() %></p>

    <table>
        <tr><th>Item</th><th>Unit Price (LKR)</th><th>Qty</th><th>Line Total (LKR)</th></tr>
        <%
            for (BillItem bi : bill.getItems()) {
        %>
        <tr>
            <td><%= bi.getItemName() %></td>
            <td><%= String.format("%.2f", bi.getUnitPrice()) %></td>
            <td><%= bi.getQuantity() %></td>
            <td><%= String.format("%.2f", bi.getLineTotal()) %></td>
        </tr>
        <%  } %>
        <tr>
            <td colspan="3" style="text-align:right"><b>Total Units:</b></td>
            <td><%= (int) bill.getTotalUnitsConsumed() %></td>
        </tr>
        <tr>
            <td colspan="3" style="text-align:right"><b>Grand Total (LKR):</b></td>
            <td><b><%= String.format("%.2f", bill.getTotalAmount()) %></b></td>
        </tr>
    </table>
</div>
</body>
</html>
