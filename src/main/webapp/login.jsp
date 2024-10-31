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
