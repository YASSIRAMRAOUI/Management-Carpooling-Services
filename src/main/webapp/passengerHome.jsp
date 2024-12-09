<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page isELIgnored="false" %>

<head>
    <title>Passenger Home</title>
</head>

<body class="bg-gray-100 text-gray-900 font-sans">
<div class="flex">
    <jsp:include page="userSidebar.jsp" />

    <!-- Main Content -->
    <div class="flex-1 p-6">
        <!-- Header Section -->
        <h2 class="text-2xl font-semibold mb-4">
            Welcome, <span class="text-indigo-600"><%= session.getAttribute("name") %></span>
        </h2>

        <!-- Available Rides Section -->
        <h1 class="text-3xl font-bold mb-6 text-gray-800">Available Rides</h1>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach var="ride" items="${availableRides}">
                <div class="bg-white shadow-md rounded-lg p-4 relative">
                    <div class="flex items-center space-x-4 mb-4">
                        <div class="flex items-center justify-center bg-indigo-100 text-indigo-600 rounded-full w-12 h-12">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8 7c1.657 0 3-1.343 3-3S9.657 1 8 1 5 2.343 5 4s1.343 3 3 3z" />
                                <path stroke-linecap="round" stroke-linejoin="round" d="M20.8 20.8a6.8 6.8 0 00-13.6 0" />
                            </svg>
                        </div>
                        <div>
                            <h3 class="text-lg font-semibold text-gray-700">${ride.driverName}</h3>
                        </div>
                    </div>
                    <!-- Ride Information -->
                    <div class="flex items-center justify-between mb-2">
                        <div class="flex items-center">
                            <i class="fa-solid fa-plane-departure text-blue-500 text-l mr-2"></i>
                            <span class="font-semibold text-gray-700">From:</span>
                            <span class="text-gray-600 ml-1">${ride.depart}</span>
                        </div>
                        <span class="text-gray-400 mx-4">
                            <i class="fa-solid fa-plane text-blue-500 text-l mr-2"></i>
                        </span>
                        <div class="flex items-center">
                            <i class="fa-solid fa-plane-arrival text-blue-500 text-l mr-2"></i>
                            <span class="font-semibold text-gray-700">To:</span>
                            <span class="text-gray-600 ml-1">${ride.destination}</span>
                        </div>
                    </div>

                    <div class="flex items-center justify-between">
                        <div class="flex items-center text-gray-700">
                            <i class="fas fa-dollar-sign text-yellow-500 text-xl mr-2"></i>
                            <span>Fare:</span>
                            <span class="font-bold text-green-600 ml-1 mr-1">${ride.fare} dh</span>
                        </div>

                        <div class="flex items-center text-gray-700">
                            <i class="fa-solid fa-user-group text-yellow-500 text-xl mr-1"></i>
                            <span>Number of places:</span>
                            <span class="font-bold text-green-600 ml-1 mr-1">${ride.numberOfPlaces}</span>
                        </div>
                    </div>

                        <form action="PassengerHomeServlet" method="post" class="mt-2 flex justify-between">
                        <div class="flex items-center text-gray-700">
                            <i class="fa-solid fa-person text-yellow-500 text-xl mr-1"></i>
                            <span>Places:</span>
                        </div>
                            <input type="hidden" name="rideId" value="${ride.id}">
                            <input type="number" name="place" min="1" max="${ride.numberOfPlaces}" required class="border rounded-md py-2 px-1">
                            <button type="submit" name="action" value="accept"
                                    class="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-6 rounded-md flex items-center focus:outline-none focus:ring-2 focus:ring-green-400">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7" />
                                </svg>
                                Accept
                            </button>
                        </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
