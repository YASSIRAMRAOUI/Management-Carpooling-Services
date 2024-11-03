<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Carpooling Service</title>
    <style>
        /* Basic styling for the page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        header, footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 1em 0;
            width: 100%;
        }

        .container {
            width: 100%;
            max-width: 800px;
            padding: 20px;
            box-sizing: border-box;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex: 1;
        }

        h1, h2 {
            color: #333;
        }

        p {
            color: #666;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 5px;
            color: #fff;
            background-color: #333;
            text-decoration: none;
            border-radius: 5px;
        }

        .button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

    <!-- Header Section -->
    <header>
        <h1>Carpooling Service</h1>
        <p>Your easy way to find rides and connect with fellow travelers.</p>
    </header>

    <!-- Main Content Section -->
    <div class="container">
        <h2>Welcome to Our Carpooling Service</h2>
        <p>Join our community of drivers and passengers who share rides, save on travel costs, and contribute to reducing carbon emissions. Whether you’re a driver with extra seats or a passenger looking for a convenient ride, our platform connects you with the right people.</p>

        <!-- Navigation Buttons -->
        <div>
            <a href="login.jsp" class="button">Login</a>
            <a href="register.jsp" class="button">Register</a>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 Carpooling Service - All Rights Reserved</p>
    </footer>

</body>
</html>
