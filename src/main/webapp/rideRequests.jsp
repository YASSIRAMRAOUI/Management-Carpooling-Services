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

    <div class="flex">
        <jsp:include page="sidebar.jsp" />

        <div class="flex-1 p-2 place-content-center">
            <div class="max-w-4xl mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
                <h1 class="text-2xl font-semibold text-gray-800 mb-6">Ride Requests</h1>

                <div class="overflow-x-auto">
                    <table class="min-w-full bg-white border rounded-lg shadow-lg">
                        <thead class="bg-blue-600 text-white">
                            <tr>
                                <th class="px-4 py-3">Date</th>
                                <th class="px-4 py-3">Destination</th>
                                <th class="px-4 py-3">Passenger</th>
                                <th class="px-4 py-3">Status</th>
                                <th class="px-4 py-3">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="request" items="${rideRequests}">
                                <tr class="text-center border-t">
                                    <td class="px-4 py-3"><c:out value="${request.date}"/></td>
                                    <td class="px-4 py-3"><c:out value="${request.destination}"/></td>
                                    <td class="px-4 py-3"><c:out value="${request.passengerName}"/></td>
                                    <td class="px-4 py-3">
                                        <c:choose>
                                            <c:when test="${request.status == 'Pending'}">
                                                <span class="text-yellow-600">Pending</span>
                                            </c:when>
                                            <c:when test="${request.status == 'Accepted'}">
                                                <span class="text-green-600">Accepted</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-red-600">Rejected</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="px-4 py-3 space-x-2">
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
