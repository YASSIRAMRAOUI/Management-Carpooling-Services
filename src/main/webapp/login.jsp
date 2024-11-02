<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Form</title>
    <link rel="stylesheet" href="Styles/index.css">
  </head>
  <body>
    <form action="LoginServlet" method="POST">
      <h2>Login Form</h2>
      
      <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
      %>
        <div class="error-message" style="color: red;"><%= errorMessage %></div>
      <% } %>
      
      <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required />
      </div>
      
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />
      </div>
      
      <div>
        <button type="submit">Login</button>
      </div>
      
      <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    </form>
  </body>
</html>

<Style>
body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f2f5;
    font-family: Arial, sans-serif;
    margin: 0;
}

form {
    background-color: #ffffff;
    width: 300px;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
}

h2 {
    color: #333;
    margin-bottom: 20px;
}

label {
    font-weight: bold;
    color: #555;
    display: block;
    margin: 10px 0 5px;
    text-align: left;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 8px;
    margin: 5px 0 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

button[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}

button[type="submit"]:hover {
    background-color: #45a049;
}

.error-message {
    color: red;
    margin-bottom: 15px;
}

p {
    margin-top: 15px;
    font-size: 14px;
    color: #555;
}

p a {
    color: #4CAF50;
    text-decoration: none;
}

p a:hover {
    text-decoration: underline;
}

</Style>