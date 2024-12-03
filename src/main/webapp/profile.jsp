<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<head>
    <title>Profile</title>
    <script>
        function hideAlert() {
            setTimeout(() => {
                const alertBox = document.getElementById('alert-box');
                if (alertBox) {
                    alertBox.classList.add('opacity-0');
                    setTimeout(() => alertBox.remove(), 500);
                }
            }, 3000);
        }
    </script>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

<div class="flex">

    <c:choose>
        <c:when test="${role == 'admin'}">
            <jsp:include page="sidebar.jsp" />
        </c:when>
        <c:otherwise>
            <jsp:include page="userSidebar.jsp" />
        </c:otherwise>
    </c:choose>

    <div class="flex-1 px-4 place-content-center">
        <c:if test="${not empty errorMessage}">
            <div id="alert-box" class="max-w-lg mx-auto bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mt-6">
                <span class="block sm:inline">${errorMessage}</span>
                <span class="absolute top-0 bottom-0 right-0 px-4 py-3">
                    <svg class="fill-current h-6 w-6 text-red-500" role="button" onclick="this.parentElement.parentElement.remove()" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                        <title>Close</title>
                        <path d="M14.348 5.652a1 1 0 0 0-1.414 0L10 8.586 7.066 5.652a1 1 0 1 0-1.414 1.414l2.934 2.934-2.934 2.934a1 1 0 0 0 1.414 1.414L10 11.414l2.934 2.934a1 1 0 1 0 1.414-1.414l-2.934-2.934 2.934-2.934a1 1 0 0 0 0-1.414z"/>
                    </svg>
                </span>
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div id="alert-box" class="max-w-lg mx-auto bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mt-6">
                <span class="block sm:inline">${message}</span>
                <span class="absolute top-0 bottom-0 right-0 px-4 py-3">
                    <svg class="fill-current h-6 w-6 text-green-500" role="button" onclick="this.parentElement.parentElement.remove()" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                        <title>Close</title>
                        <path d="M14.348 5.652a1 1 0 0 0-1.414 0L10 8.586 7.066 5.652a1 1 0 1 0-1.414 1.414l2.934 2.934-2.934 2.934a1 1 0 0 0 1.414 1.414L10 11.414l2.934 2.934a1 1 0 1 0 1.414-1.414l-2.934-2.934 2.934-2.934a1 1 0 0 0 0-1.414z"/>
                    </svg>
                </span>
            </div>
        </c:if>
        
        <div class="max-w-lg mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
            <h1 class="text-2xl font-semibold text-gray-800 mb-6">Update Profile</h1>

            <form action="ProfileServlet" method="post">
                <div class="mb-4">
                    <label for="name" class="block text-gray-700 font-semibold">Name</label>
                    <input type="text" id="name" name="name" value="${user.name}" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500" required>
                </div>

                <div class="mb-4">
                    <label for="email" class="block text-gray-700 font-semibold">Email</label>
                    <input type="email" id="email" name="email" value="${user.email}" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500" required>
                </div>

                <div class="mb-4">
                    <label for="phone_number" class="block text-gray-700 font-semibold">Phone Number</label>
                    <input type="text" id="phone_number" name="phone_number" value="${user.phoneNumber}" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:border-blue-500">
                </div>

                <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700">Update Profile</button>
            </form>
        </div>
    </div>
</div>
</body>
