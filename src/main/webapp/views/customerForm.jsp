<%@ page import="com.pahanaedu.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Form - Pahana Edu</title>
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
    <h1>Customer</h1>
    <%
        Customer c = (Customer) request.getAttribute("customer");
        long accountNumber = c != null ? c.getAccountNumber() : 0;
    %>
    <form method="post" action="../app/customers">
        <label>Account Number</label>
        <input type="number" name="accountNumber" value="<%= accountNumber %>" required />
        <label>Name</label>
        <input type="text" name="name" value="<%= c != null ? c.getName() : "" %>" required />
        <label>Address</label>
        <input type="text" name="address" value="<%= c != null ? c.getAddress() : "" %>" required />
        <label>Telephone</label>
        <input type="text" name="telephone" value="<%= c != null ? c.getTelephone() : "" %>" required />
        <label>Units Consumed (optional)</label>
        <input type="number" name="unitsConsumed" value="<%= c != null ? c.getUnitsConsumed() : 0 %>" />
        <button type="submit">Save</button>
        <a class="btn" href="../app/customers?action=list">Cancel</a>
    </form>
</div>
</body>
</html>
