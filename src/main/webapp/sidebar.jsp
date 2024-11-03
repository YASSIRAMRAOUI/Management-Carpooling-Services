<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<!-- Sidebar -->
<nav class="w-64 bg-blue-900 text-white min-h-screen max-h-none p-6">
    <h2 class="text-2xl font-semibold mb-8">Dashboard</h2>
    <ul>
        <li class="mb-4">
            <a href="DriverHomeServlet" class="text-blue-200 hover:text-white flex items-center">
                <i class="fas fa-home mr-2"></i> <!-- Home icon -->
                Dashboard
            </a>
        </li>
        <li class="mb-4">
            <a href="createRide.jsp" class="text-blue-200 hover:text-white flex items-center">
                <i class="fas fa-plus-circle mr-2"></i> <!-- Plus icon for new ride -->
                New Ride
            </a>
        </li>
        <li class="mb-4">
            <a href="rideRequests.jsp" class="text-blue-200 hover:text-white flex items-center">
                <i class="fas fa-tasks mr-2"></i> <!-- Tasks icon for ride requests -->
                Ride Requests
            </a>
        </li>
        <li class="mb-4">
            <a href="ProfileServlet" class="text-blue-200 hover:text-white flex items-center">
                <i class="fas fa-user mr-2"></i> <!-- User icon for profile -->
                Update Profile
            </a>
        </li>
        <li class="mb-4">
            <a href="LogoutServlet" class="text-blue-200 hover:text-white flex items-center">
                <i class="fas fa-sign-out-alt mr-2"></i> <!-- Sign-out icon for logout -->
                Logout
            </a>
        </li>
    </ul>
</nav>
