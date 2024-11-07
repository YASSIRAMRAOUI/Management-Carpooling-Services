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
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

    <div class="flex h-screen">
        <jsp:include page="sidebar.jsp" />

        <div class="flex-1 p-4 place-content-center overflow-y-auto">
            <div class="max-w-4xl mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
                <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
                    <i class="fas fa-car mr-2"></i>
                    Ride Requests
                </h2>
                <div class="overflow-x-auto">
                    <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
                        <thead class="bg-gray-200">
                            <tr>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Date</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Destination</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Passenger</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Status</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Fare</th>
                                <th class="px-6 py-3 text-center text-sm font-medium text-gray-700">Actions</th>
                            </tr>
                        </thead>
                        <tbody class="text-gray-700">
                            <c:forEach var="request" items="${rideRequests}">
                                <tr class="border-b hover:bg-gray-50">
                                    <td class="px-6 py-4"><c:out value="${request.date}"/></td>
                                    <td class="px-6 py-4"><c:out value="${request.destination}"/></td>
                                    <td class="px-6 py-4"><c:out value="${request.passengerName}"/></td>
                                    <td class="px-6 py-4">
                                        <c:choose>
                                            <c:when test="${request.status == 'Pending'}">
                                                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-yellow-100 text-yellow-800">
                                                    <i class="fas fa-hourglass-half mr-1"></i>
                                                    Pending
                                                </span>
                                            </c:when>
                                            <c:when test="${request.status == 'Accepted'}">
                                                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-green-100 text-green-800">
                                                    <i class="fas fa-check-circle mr-1"></i>
                                                    Accepted
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-red-100 text-red-800">
                                                    <i class="fas fa-times-circle mr-1"></i>
                                                    Rejected
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="px-6 py-4">$<c:out value="${request.fare}"/></td>
                                    <td class="px-6 py-4 space-x-2">
                                        <c:if test="${request.status == 'Pending'}">
                                            <form action="RideRequestServlet" method="post" style="display:inline;">
                                                <input type="hidden" name="requestId" value="${request.id}"/>
                                                <input type="hidden" name="action" value="accept"/>
                                                <button type="submit" class="bg-green-600 text-white px-3 py-1 rounded">Accept</button>
                                            </form>
                                            <form action="RideRequestServlet" method="post" style="display:inline;">
                                                <input type="hidden" name="requestId" value="${request.id}"/>
                                                <input type="hidden" name="action" value="reject"/>
                                                <button type="submit" class="bg-red-600 text-white px-3 py-1 rounded">Reject</button>
                                            </form>
                                        </c:if>
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
