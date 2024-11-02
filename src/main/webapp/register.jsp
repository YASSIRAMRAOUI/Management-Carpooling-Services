<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <form action="RegisterServlet" method="post">
        <h2>Register</h2>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        
        <label for="phone_number">Phone Number:</label>
        <input type="text" id="phone_number" name="phone_number">
        
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="Driver">Driver</option>
            <option value="Passenger">Passenger</option>
        </select>
        
        <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
      %>
        <div class="error-message" style="color: red;"><%= errorMessage %></div>
      <% } %>

        <input type="submit" value="Register">
        <p>Do you have an account? <a href="login.jsp">Login here</a></p>
    </form>
</body>
</html>

<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f4f4f4;
        margin: 0;
    }

    h2 {
        text-align: center;
        color: #333;
    }

    form {
        background-color: #fff;
        border-radius: 5px;
        padding: 20px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        width: 300px;
    }

    label {
        font-weight: bold;
        color: #555;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"],
    select {
        width: 100%;
        padding: 8px;
        margin: 5px 0 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 3px;
        cursor: pointer;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }

    .error-message {
        color: red;
        text-align: center;
        margin: 10px;
    }

    p {
    margin-top: 15px;
    font-size: 14px;
    color: #555;
    text-align: center;
}

    p a {
    color: #4CAF50;
    text-decoration: none;
}

    p a:hover {
    text-decoration: underline;
}
</style>
