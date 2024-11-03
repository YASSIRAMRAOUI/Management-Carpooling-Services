<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="w-full max-w-md px-6 py-8 bg-white rounded-lg shadow-lg">
        <h2 class="text-3xl font-semibold text-center text-gray-700 mb-4">Create an Account</h2>
        <p class="text-center text-gray-500 mb-8">Join our community today</p>
        
        <% String errorMessage = (String) request.getAttribute("errorMessage"); if (errorMessage != null) { %>
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4" role="alert">
            <span class="block sm:inline"><%= errorMessage %></span>
        </div>
        <% } %>
        
        <form action="RegisterServlet" method="POST">
            <div class="mb-4">
                <label for="name" class="block text-sm font-medium text-gray-600">Name</label>
                <input type="text" id="name" name="name" required
                       class="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div class="mb-4">
                <label for="email" class="block text-sm font-medium text-gray-600">Email</label>
                <input type="email" id="email" name="email" required
                       class="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div class="mb-4">
                <label for="password" class="block text-sm font-medium text-gray-600">Password</label>
                <input type="password" id="password" name="password" required
                       class="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div class="mb-4">
                <label for="phone_number" class="block text-sm font-medium text-gray-600">Phone Number</label>
                <input type="text" id="phone_number" name="phone_number"
                       class="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div class="mb-6">
                <label for="role" class="block text-sm font-medium text-gray-600">Role</label>
                <select id="role" name="role" required
                        class="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="">Select a role</option>
                    <option value="Driver">Driver</option>
                    <option value="Passenger">Passenger</option>
                </select>
            </div>
            <button type="submit" class="w-full px-4 py-2 font-semibold text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
                Register
            </button>
        </form>
        
        <p class="mt-6 text-sm text-center text-gray-600">
            Already have an account? <a href="login.jsp" class="text-blue-500 hover:underline">Log in here</a>
        </p>
    </div>
</body>
</html>