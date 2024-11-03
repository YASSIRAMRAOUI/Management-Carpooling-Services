<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Ride</title>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">
    <div class="flex">
        <jsp:include page="sidebar.jsp" />

        <div class="flex-1 p-6 place-content-center">
            <div class="max-w-md mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
                <h1 class="text-2xl font-semibold text-gray-800 mb-6">Create New Ride</h1>

                <form action="CreateRideServlet" method="post">
                    <div class="mb-4">
                        <label for="date" class="block text-gray-700 font-semibold">Date</label>
                        <input type="date" id="date" name="date" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500" required>
                    </div>

                    <div class="mb-4">
                        <label for="destination" class="block text-gray-700 font-semibold">Destination</label>
                        <input type="text" id="destination" name="destination" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500" required>
                    </div>

                    <div class="mb-4">
                        <label for="status" class="block text-gray-700 font-semibold">Status</label>
                        <select id="status" name="status" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500" required>
                            <option value="Pending">Pending</option>
                            <option value="Completed">Completed</option>
                            <option value="Cancelled">Cancelled</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <label for="fare" class="block text-gray-700 font-semibold">Fare</label>
                        <input type="number" step="0.01" id="fare" name="fare" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500" required>
                    </div>

                    <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700">Create Ride</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
