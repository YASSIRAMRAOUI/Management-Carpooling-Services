<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    // Session validation
    if (session == null || session.getAttribute("user_id") == null || !"Driver".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Home</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("name") %> (Driver)</h2>
    <p>This is the driver's dashboard.</p>
    <!-- Add driver-specific functionalities here -->
    
    <a href="LogoutServlet">Logout</a>
</body>
</html>
