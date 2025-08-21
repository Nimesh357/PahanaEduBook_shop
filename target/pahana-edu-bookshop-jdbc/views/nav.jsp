<%@ page import="com.pahanaedu.model.User" %>
<div class="topbar">
    <%
        User u = (User) session.getAttribute("user");
    %>
    <div><a href="home.jsp">Pahana Edu</a></div>
    <div class="spacer"></div>
    <div>Signed in as <b><%= u != null ? u.getFullName() : "" %></b></div>
    <div><a href="../logout">Logout</a></div>
</div>
