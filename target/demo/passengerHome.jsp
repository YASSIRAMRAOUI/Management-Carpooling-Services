<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>Available Rides</h1>
    <table>
        <thead>
        <tr>
            <th>Driver Name</th>
            <th>Depart</th>
            <th>Destination</th>
            <th>Fare</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ride" items="${availableRides}">
            <tr>
                <td>${ride.driverName}</td>
                <td>${ride.depart}</td>
                <td>${ride.destination}</td>
                <td>${ride.fare}</td>
                <td>
                    <form action="PassengerHomeServlet" method="post">
                        <input type="hidden" name="rideId" value="${ride.id}">
                        <input type="submit" name="action" value="accept">
                        <input type="submit" name="action" value="decline">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <a href="LogoutServlet">Logout</a>
</body>
</html>
