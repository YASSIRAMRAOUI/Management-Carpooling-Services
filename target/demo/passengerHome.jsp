<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passenger Home</title>
    <!-- Tailwind CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>

<body class="bg-gray-100 text-gray-900 font-sans">
<div class="max-w-7xl mx-auto p-6">
    <!-- Header Section -->
    <h2 class="text-2xl font-semibold mb-4">
        Welcome, <span class="text-indigo-600"><%= session.getAttribute("name") %></span> (Passenger)
    </h2>
    <p class="mb-6 text-lg text-gray-700">This is your passenger dashboard.</p>

    <!-- Available Rides Section -->
    <h1 class="text-3xl font-bold mb-6 text-gray-800">Available Rides</h1>

    <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
        <thead class="bg-indigo-600 text-white">
        <tr>
            <th class="py-3 px-6 text-left">Driver Name</th>
            <th class="py-3 px-6 text-left">Departure</th>
            <th class="py-3 px-6 text-left">Destination</th>
            <th class="py-3 px-6 text-left">Fare</th>
            <th class="py-3 px-6 text-left">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ride" items="${availableRides}">
            <tr class="border-b hover:bg-gray-50">
                <td class="py-3 px-6"><span class="font-medium text-gray-700">${ride.driverName}</span></td>
                <td class="py-3 px-6">${ride.depart}</td>
                <td class="py-3 px-6">${ride.destination}</td>
                <td class="py-3 px-6">${ride.fare}</td>
                <td class="py-3 px-6">
                    <form action="PassengerHomeServlet" method="post" class="inline-flex space-x-2">
                        <input type="hidden" name="rideId" value="${ride.id}">
                        <input type="number" name="places" min="1" max="${ride.numberOfPlaces}" required class="border rounded-md py-2 px-4">
                        <button type="submit" name="action" value="accept"
                                class="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500">Accept</button>
                        <button type="submit" name="action" value="decline"
                                class="bg-red-500 hover:bg-red-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500">Decline</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Logout Link Section -->
    <div class="mt-8">
        <a href="LogoutServlet"
           class="text-indigo-500 hover:text-indigo-700 font-semibold text-lg">Logout</a>
    </div>
</div>
</body>

</html>
