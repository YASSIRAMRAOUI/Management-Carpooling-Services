<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<head>
    <title>My Ride History</title>
</head>
<body class="bg-gray-100 text-gray-900 font-sans">
    <div class="flex">
        <!-- Include Sidebar -->
        <jsp:include page="userSidebar.jsp" />

        <!-- Main Content -->
        <div class="flex-1 p-6">
            <h1 class="text-3xl font-bold mb-6 text-gray-800">My Ride History</h1>

            <!-- Check if there are any rides in the history -->
            <c:choose>
                <c:when test="${not empty rideHistory}">
                    <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
                        <thead class="bg-indigo-600 text-white">
                            <tr>
                                <th class="py-3 px-6 text-left">Driver Name</th>
                                <th class="py-3 px-6 text-left">Departure</th>
                                <th class="py-3 px-6 text-left">Destination</th>
                                <th class="py-3 px-6 text-left">Fare</th>
                                <th class="py-3 px-6 text-left">Status</th>
                                <th class="py-3 px-6 text-left">Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ride" items="${rideHistory}">
                                <tr class="border-b hover:bg-gray-50">
                                    <td class="py-3 px-6"><span class="font-medium text-gray-700">${ride.driverName}</span></td>
                                    <td class="py-3 px-6">${ride.depart}</td>
                                    <td class="py-3 px-6">${ride.destination}</td>
                                    <td class="py-3 px-6">$${ride.fare}</td>
                                    <td class="py-3 px-6">
                                        <c:choose>
                                            <c:when test="${ride.status == 'Completed'}">
                                                <span class="text-green-600 font-bold">Completed</span>
                                            </c:when>
                                            <c:when test="${ride.status == 'Cancelled'}">
                                                <span class="text-red-600 font-bold">Cancelled</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-yellow-600 font-bold">${ride.status}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="py-3 px-6">${ride.date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-gray-600 text-lg">You have no ride history to display.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
