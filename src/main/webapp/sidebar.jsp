<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<script>
    document.addEventListener("DOMContentLoaded", function() {
        if (localStorage.getItem("theme") === "dark") {
            document.documentElement.classList.add("dark");
        }

        document.getElementById("toggle-dark-mode").addEventListener("click", function() {
            document.documentElement.classList.toggle("dark");
            localStorage.setItem("theme", document.documentElement.classList.contains("dark") ? "dark" : "light");
        });
    });
</script>

<!-- Dark Mode Toggle Button -->
<button onclick="toggleDarkMode()" class="absolute top-4 right-4 p-2 rounded-full bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-gray-200">
    <i class="fas fa-moon dark:hidden"></i> <!-- Moon icon for light mode -->
    <i class="fas fa-sun hidden dark:inline"></i> <!-- Sun icon for dark mode -->
</button>

<!-- Sidebar -->
<nav class="w-64 bg-blue-900 dark:bg-gray-800 text-white dark:text-gray-200 min-h-screen p-6 shadow-lg">
    <div class="flex items-center justify-center mb-10 space-x-4">
        <img src="./assets/Carpooling_Logo.png" alt="Carpooling Logo" class="w-16 h-16">
        <h2 class="text-2xl font-extrabold text-center text-gray-800 dark:text-gray-100">Carpooling</h2>
    </div>

    <ul class="space-y-6">
        <li>
            <a href="DriverHomeServlet" class="text-blue-200 dark:text-gray-400 hover:text-white dark:hover:text-gray-100 flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-home"></i>
                <span class="font-medium">Dashboard</span>
            </a>
        </li>
        <li>
            <a href="createRide.jsp" class="text-blue-200 dark:text-gray-400 hover:text-white dark:hover:text-gray-100 flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-plus-circle"></i>
                <span class="font-medium">New Ride</span>
            </a>
        </li>
        <li>
            <a href="rideRequests.jsp" class="text-blue-200 dark:text-gray-400 hover:text-white dark:hover:text-gray-100 flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-tasks"></i>
                <span class="font-medium">Ride Requests</span>
            </a>
        </li>
        <li>
            <a href="ProfileServlet" class="text-blue-200 dark:text-gray-400 hover:text-white dark:hover:text-gray-100 flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-user"></i>
                <span class="font-medium">Update Profile</span>
            </a>
        </li>
        <li>
            <a href="LogoutServlet" class="text-blue-200 dark:text-gray-400 hover:text-white dark:hover:text-gray-100 flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-sign-out-alt"></i>
                <span class="font-medium">Logout</span>
            </a>
        </li>
    </ul>
</nav>
