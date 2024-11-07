<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<!-- Sidebar -->
<nav class="w-64 bg-blue-900 text-white min-h-screen p-6 shadow-lg">
    <div class="flex items-center justify-center mb-8">
        <img src="./assets/Carpooling_Logo.png" alt="Carpooling Logo" class="w-16 h-16">
        <h2 class="text-3xl font-extrabold text-center">Carpooling</h2>
    </div>

    <ul class="space-y-6">
        <li>
            <a href="DriverHomeServlet" class="text-blue-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-home"></i> <!-- Home icon -->
                <span class="font-medium">Dashboard</span>
            </a>
        </li>
        <li>
            <a href="createRide.jsp" class="text-blue-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-plus-circle"></i> <!-- Plus icon for new ride -->
                <span class="font-medium">New Ride</span>
            </a>
        </li>
        <li>
            <a href="rideRequests.jsp" class="text-blue-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-tasks"></i> <!-- Tasks icon for ride requests -->
                <span class="font-medium">Ride Requests</span>
            </a>
        </li>
        <li>
            <a href="ProfileServlet" class="text-blue-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-user"></i> <!-- User icon for profile -->
                <span class="font-medium">Update Profile</span>
            </a>
        </li>
        <li>
            <a href="LogoutServlet" class="text-blue-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-sign-out-alt"></i> <!-- Sign-out icon for logout -->
                <span class="font-medium">Logout</span>
            </a>
        </li>
    </ul>
</nav>
