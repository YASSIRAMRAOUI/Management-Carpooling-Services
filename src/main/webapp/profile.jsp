<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">
<div class="flex">

    <jsp:include page="sidebar.jsp" />

    <div class="flex-1 px-4 place-content-center">
        <div class="max-w-lg mx-auto bg-white rounded-lg shadow-lg p-8 mt-10">
            <h1 class="text-2xl font-semibold text-gray-800 mb-6">Profile</h1>

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
</html>
