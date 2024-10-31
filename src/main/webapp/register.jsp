<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>Register</h2>
    <form action="RegisterServlet" method="post">
        <label for="name">Name:</label><br/>
        <input type="text" id="name" name="name" required><br/><br/>
        
        <label for="email">Email:</label><br/>
        <input type="email" id="email" name="email" required><br/><br/>
        
        <label for="password">Password:</label><br/>
        <input type="password" id="password" name="password" required><br/><br/>
        
        <label for="phone_number">Phone Number:</label><br/>
        <input type="text" id="phone_number" name="phone_number"><br/><br/>
        
        <label for="role">Role:</label><br/>
        <select id="role" name="role" required>
            <option value="Driver">Driver</option>
            <option value="Passenger">Passenger</option>
        </select><br/><br/>
        
        <input type="submit" value="Register">
    </form>

    <!-- Display error message if any -->
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
</body>
</html>
