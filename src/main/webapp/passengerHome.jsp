<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    // Session validation
    if (session == null || session.getAttribute("user_id") == null || !"Passenger".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Passenger Home</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("name") %> (Passenger)</h2>
    <p>This is the passenger's dashboard.</p>
    <!-- Add passenger-specific functionalities here -->
    
    <a href="LogoutServlet">Logout</a>
</body>
</html>
