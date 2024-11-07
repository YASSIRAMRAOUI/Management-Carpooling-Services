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
    <div class="flex h-screen">

        <!-- Include the sidebar -->
        <jsp:include page="sidebar.jsp" />

        <!-- Main content area -->
        <div class="flex-1 p-8 overflow-y-auto">
            
            <!-- Header -->
            <header class="mb-8">
                <h1 class="text-4xl font-extrabold text-gray-900">Welcome, <%= session.getAttribute("name") %>!</h1>
                <p class="text-lg text-gray-600 mt-2">Here's a summary of your driving activities.</p>
            </header>

            <!-- Statistics Cards -->
            <section class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
                <div class="bg-white shadow-md rounded-lg p-6 text-center">
                    <h3 class="text-xl font-semibold text-gray-700 mb-2">Total Rides</h3>
                    <p class="text-4xl font-bold text-blue-600"><c:out value="${totalRides}"/></p>
                </div>
                <div class="bg-white shadow-md rounded-lg p-6 text-center">
                    <h3 class="text-xl font-semibold text-gray-700 mb-2">Pending Requests</h3>
                    <p class="text-4xl font-bold text-yellow-500"><c:out value="${pendingRequests}"/></p>
                </div>
                <div class="bg-white shadow-md rounded-lg p-6 text-center">
                    <h3 class="text-xl font-semibold text-gray-700 mb-2">Earnings</h3>
                    <p class="text-4xl font-bold text-green-600">$<c:out value="${earnings}"/></p>
                </div>
            </section>

            <!-- Quick Actions -->
            <section class="mb-12">
                <h2 class="text-2xl font-semibold text-gray-800 mb-6">Quick Actions</h2>
                <div class="flex space-x-4">
                    <button onclick="window.location.href='createRide.jsp'" class="flex items-center bg-blue-600 text-white px-6 py-3 rounded-lg shadow hover:bg-blue-700 transition duration-200">
                        <i class="fas fa-plus-circle mr-2"></i>
                        Create New Ride
                    </button>
                    <button onclick="window.location.href='rideRequests.jsp'" class="flex items-center bg-blue-600 text-white px-6 py-3 rounded-lg shadow hover:bg-blue-700 transition duration-200">
                        <i class="fas fa-tasks mr-2"></i>
                        View Ride Requests
                    </button>
                </div>
            </section>

            <!-- Recent Rides Section -->
            <section>
                <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
                    <i class="fas fa-car mr-2"></i>
                    Recent Rides
                </h2>
                <div class="overflow-x-auto">
                    <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
                        <thead class="bg-gray-200">
                            <tr>
                                <th class="px-6 py-3 text-left text-sm font-medium text-gray-700">Date</th>
                                <th class="px-6 py-3 text-left text-sm font-medium text-gray-700">Destination</th>
                                <th class="px-6 py-3 text-left text-sm font-medium text-gray-700">Status</th>
                                <th class="px-6 py-3 text-left text-sm font-medium text-gray-700">Fare</th>
                            </tr>
                        </thead>
                        <tbody class="text-gray-700">
                            <c:forEach var="ride" items="${recentRides}">
                                <tr class="border-b hover:bg-gray-50">
                                    <td class="px-6 py-4"><c:out value="${ride.date}"/></td>
                                    <td class="px-6 py-4"><c:out value="${ride.destination}"/></td>
                                    <td class="px-6 py-4">
                                        <c:choose>
                                            <c:when test="${ride.status == 'Completed'}">
                                                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-green-100 text-green-800">
                                                    <i class="fas fa-check-circle mr-1"></i>
                                                    Completed
                                                </span>
                                            </c:when>
                                            <c:when test="${ride.status == 'Cancelled'}">
                                                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-red-100 text-red-800">
                                                    <i class="fas fa-times-circle mr-1"></i>
                                                    Cancelled
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-yellow-100 text-yellow-800">
                                                    <i class="fas fa-hourglass-half mr-1"></i>
                                                    Pending
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="px-6 py-4">$<c:out value="${ride.fare}"/></td>
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
