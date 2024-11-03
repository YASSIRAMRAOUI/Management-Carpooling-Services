<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Driver Dashboard</title>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

<div class="flex">

    <jsp:include page="sidebar.jsp" />

    <div class="flex-1 p-10">

        <header class="mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Welcome, <%= session.getAttribute("name") %>!</h1>
            <p class="text-gray-600 mt-2">Here's a summary of your driving activities.</p>
        </header>

        <section class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
            <div class="bg-white shadow-lg rounded-lg p-6 text-center">
                <h3 class="text-xl font-semibold text-gray-700">Total Rides</h3>
                <p class="text-2xl font-bold text-blue-600"><c:out value="${totalRides}"/></p>
            </div>
            <div class="bg-white shadow-lg rounded-lg p-6 text-center">
                <h3 class="text-xl font-semibold text-gray-700">Pending Requests</h3>
                <p class="text-2xl font-bold text-blue-600"><c:out value="${pendingRequests}"/></p>
            </div>
            <div class="bg-white shadow-lg rounded-lg p-6 text-center">
                <h3 class="text-xl font-semibold text-gray-700">Earnings</h3>
                <p class="text-2xl font-bold text-green-600">$<c:out value="${earnings}"/></p>
            </div>
        </section>

        <!-- Quick Actions -->
        <section class="mb-8">
            <h2 class="text-2xl font-semibold text-gray-800 mb-4">Quick Actions</h2>
            <div class="space-x-4">
                <button onclick="window.location.href='createRide.jsp'" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                    <i class="fas fa-plus-circle mr-2"></i>
                    Create New Ride
                </button>
                <button onclick="window.location.href='rideRequests.jsp'" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                    <i class="fas fa-tasks mr-2"></i>
                    View Ride Requests
                </button>
            </div>
        </section>

        <!-- Recent Rides Section -->
        <section class="mb-8">
            <h2 class="text-2xl font-semibold text-gray-800 mb-4">
                <i class="fas fa-file-invoice mr-2 fa-sm"></i>
                Recent Rides
            </h2>
            <div class="overflow-x-auto">
                <table class="min-w-full bg-white border rounded-lg shadow-lg">
                    <thead class="bg-blue-600 text-white">
                        <tr>
                            <th class="w-1/4 px-4 py-3">Date</th>
                            <th class="w-1/4 px-4 py-3">Destination</th>
                            <th class="w-1/4 px-4 py-3">Status</th>
                            <th class="w-1/4 px-4 py-3">Fare</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ride" items="${recentRides}">
                            <tr class="text-center border-t">
                                <td class="px-4 py-3"><c:out value="${ride.date}"/></td>
                                <td class="px-4 py-3"><c:out value="${ride.destination}"/></td>
                                <td class="px-4 py-3">
                                    <c:choose>
                                        <c:when test="${ride.status == 'Completed'}">
                                            <span class="text-green-600">Completed</span>
                                        </c:when>
                                        <c:when test="${ride.status == 'Cancelled'}">
                                            <span class="text-red-600">Cancelled</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-yellow-600">Pending</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="px-4 py-3">$<c:out value="${ride.fare}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</div>

</body>
</html>
