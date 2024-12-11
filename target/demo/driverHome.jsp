<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<head>
    <title>Driver Dashboard</title>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">
<% String rideAddedMessage = (String) request.getAttribute("rideAddedMessage"); %>
<% if (rideAddedMessage != null) { %>
    <div id="alert" class="fixed top-5 right-5 bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded shadow-lg transition-opacity duration-1000 ease-out" role="alert">
        <strong class="font-bold">Success!</strong>
        <span class="block sm:inline"><%= rideAddedMessage %></span>
        <button type="button" onclick="document.getElementById('alert').style.display='none';" class="absolute top-0 bottom-0 right-0 px-4 py-3">
            <svg class="fill-current h-6 w-6 text-green-700" role="button" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                <path d="M14.348 14.849a1 1 0 001.415 0l.003-.003a1 1 0 000-1.415L11.414 10l4.352-4.343a1 1 0 10-1.415-1.415L10 8.586 5.657 4.243a1 1 0 00-1.414 1.415L8.586 10l-4.343 4.342a1 1 0 101.415 1.415L10 11.414l4.343 4.352a1 1 0 001.005.083z"/>
            </svg>
        </button>
    </div>
    <script>
        setTimeout(function() {
            document.getElementById('alert').classList.add('opacity-0');
        }, 5000);
        setTimeout(function() {
            document.getElementById('alert').remove();
        }, 6000);
    </script>
<% } %>


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
                    <p class="text-4xl font-bold text-green-600"><c:out value="${earnings}"/> Dh</p>
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
                    <button onclick="window.location.href='RideRequestServlet'" class="flex items-center bg-blue-600 text-white px-6 py-3 rounded-lg shadow hover:bg-blue-700 transition duration-200">
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
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Date</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Depart</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Destination</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Passenger Name</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Passenger Phone</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Fare</th>
                            </tr>
                        </thead>
                        <tbody class="text-gray-700 text-center">
                            <c:forEach var="ride" items="${recentRides}">
                                <tr class="border-b hover:bg-gray-50">
                                    <td class="px-6 py-4"><c:out value="${ride.date}"/></td>
                                    <td class="px-6 py-4"><c:out value="${ride.depart}"/></td>
                                    <td class="px-6 py-4"><c:out value="${ride.destination}"/></td>
                                    <td class="px-6 py-4"><c:out value="${ride.passengerName}"/></td>
                                    <td class="px-6 py-4"><c:out value="${ride.passengerPhone}"/></td>
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
