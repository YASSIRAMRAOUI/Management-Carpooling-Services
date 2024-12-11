<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<head>
    <title>Ride Requests</title>
    <script>
        function confirmAction(message, formId) {
            if (confirm(message)) {
                document.getElementById(formId).submit();
            }
        }
    </script>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

    <div class="flex h-screen">
        <jsp:include page="sidebar.jsp" />

        <div class="flex-1 p-4 overflow-y-auto">
            <div class="max-w-5xl mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
                <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
                    <i class="fas fa-car mr-2 text-blue-600"></i>
                    Ride Requests
                </h2>
                <div>
                    <c:forEach var="request" items="${rideRequests}">
                        <div class="bg-white border border-gray-200 rounded-lg p-6 mb-4 shadow-sm">
                            <h3 class="text-lg font-semibold text-gray-700 flex items-center">
                                <i class="fas fa-calendar-alt mr-2 text-blue-500"></i>
                                Ride on <c:out value="${request.date}" />
                            </h3>
                            <div class="mt-4 grid grid-cols-2 gap-4 text-gray-600">
                                <div><strong>Depart:</strong> <c:out value="${request.depart}" /></div>
                                <div><strong>Destination:</strong> <c:out value="${request.destination}" /></div>
                                <div><strong>Places:</strong> <c:out value="${request.numberOfPlaces}" /></div>
                                <div><strong>Fare:</strong> $<c:out value="${request.fare}" /></div>
                                <div>
                                    <strong>Status:</strong>
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
                                </div>
                            </div>
                            <div class="mt-4 flex gap-4">
                                <form id="completeForm${request.id}" action="DriverRidesServlet" method="post">
                                    <input type="hidden" name="rideId" value="${request.id}" />
                                    <input type="hidden" name="action" value="updateStatus" />
                                    <input type="hidden" name="status" value="Completed" />
                                    <button type="button"
                                            class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                                            onclick="confirmAction('Are you sure you want to mark this ride as completed?', 'completeForm${request.id}')">
                                        <i class="fas fa-check-circle"></i> Complete
                                    </button>
                                </form>
                                <form id="cancelForm${request.id}" action="DriverRidesServlet" method="post">
                                    <input type="hidden" name="rideId" value="${request.id}" />
                                    <input type="hidden" name="action" value="updateStatus" />
                                    <input type="hidden" name="status" value="Cancelled" />
                                    <button type="button"
                                            class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                                            onclick="confirmAction('Are you sure you want to cancel this ride?', 'cancelForm${request.id}')">
                                        <i class="fas fa-times-circle"></i> Cancel
                                    </button>
                                </form>
                                <form id="deleteForm${request.id}" action="DriverRidesServlet" method="post">
                                    <input type="hidden" name="rideId" value="${request.id}" />
                                    <input type="hidden" name="action" value="delete" />
                                    <button type="button"
                                            class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
                                            onclick="confirmAction('Are you sure you want to delete this ride?', 'deleteForm${request.id}')">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
