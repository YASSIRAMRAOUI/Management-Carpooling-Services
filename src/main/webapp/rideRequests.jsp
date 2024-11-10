<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ride Requests</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

    <div class="flex h-screen">
        <jsp:include page="sidebar.jsp" />

        <div class="flex-1 p-4 place-content-center overflow-y-auto">
            <div class="max-w-5xl mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
                <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
                    <i class="fas fa-car mr-2 text-blue-600"></i>
                    Ride Requests
                </h2>
                <div class="overflow-x-auto">
                    <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
                        <thead class="bg-blue-600 text-white">
                            <tr>
                                <th class="px-6 py-3 text-center text-sm font-medium">Date</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Depart</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Destination</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Number of Places</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Fare</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Status</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Complete</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Cancel</th>
                                <th class="px-6 py-3 text-center text-sm font-medium">Delete</th>
                            </tr>
                        </thead>
                        <tbody class="text-gray-700">
                            <c:forEach var="request" items="${rideRequests}">
                                <tr class="border-b hover:bg-gray-50">
                                    <td class="px-6 py-4 text-center"><c:out value="${request.date}"/></td>
                                    <td class="px-6 py-4 text-center"><c:out value="${request.depart}"/></td>
                                    <td class="px-6 py-4 text-center"><c:out value="${request.destination}"/></td>
                                    <td class="px-6 py-4 text-center"><c:out value="${request.numberOfPlaces}"/></td>
                                    <td class="px-6 py-4 text-center">$<c:out value="${request.fare}"/></td>
                                    <td class="px-6 py-4 text-center">
                                        <c:choose>
                                            <c:when test="${request.status == 'Completed'}">
                                                <span class="text-green-600 font-bold"><i class="fas fa-check-circle"></i> Completed</span>
                                            </c:when>
                                            <c:when test="${request.status == 'Cancelled'}">
                                                <span class="text-red-600 font-bold"><i class="fas fa-times-circle"></i> Cancelled</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-yellow-600 font-bold"><i class="fas fa-hourglass-half"></i> In Progress</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <!-- Complete Action -->
                                    <td class="px-6 py-4 text-center">
                                        <form action="DriverRidesServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="rideId" value="${request.id}" />
                                            <input type="hidden" name="action" value="updateStatus" />
                                            <input type="hidden" name="status" value="Completed" />
                                            <button type="submit" class="text-blue-600 hover:text-blue-800" title="Complete">
                                                <i class="fas fa-check-circle"></i>
                                            </button>
                                        </form>
                                    </td>
                                    <!-- Cancel Action -->
                                    <td class="px-6 py-4 text-center">
                                        <form action="DriverRidesServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="rideId" value="${request.id}" />
                                            <input type="hidden" name="action" value="updateStatus" />
                                            <input type="hidden" name="status" value="Cancelled" />
                                            <button type="submit" class="text-red-600 hover:text-red-800" title="Cancel">
                                                <i class="fas fa-times-circle"></i>
                                            </button>
                                        </form>
                                    </td>
                                    <!-- Delete Action -->
                                    <td class="px-6 py-4 text-center">
                                        <form action="DriverRidesServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="rideId" value="${request.id}" />
                                            <input type="hidden" name="action" value="delete" />
                                            <button type="submit" class="text-gray-600 hover:text-gray-800" title="Delete">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
