<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<!-- Sidebar -->
<nav class="w-64 bg-gradient-to-b from-green-700 to-blue-900 text-white min-h-screen p-6 shadow-lg">
    <div class="flex items-center justify-center mb-8">
        <img src="./assets/Carpooling_Logo.png" alt="Carpooling Logo" class="w-16 h-16">
        <h2 class="text-3xl font-extrabold text-center">Carpooling</h2>
    </div>

    <ul class="space-y-6">
        <li>
            <a href="PassengerHomeServlet" class="text-green-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-home"></i> <!-- Home icon -->
                <span class="font-medium">Dashboard</span>
            </a>
        </li>
        <li>
            <a href="MyRideServlet" class="text-green-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-car"></i> <!-- Car icon for rides -->
                <span class="font-medium">My Rides</span>
            </a>
        </li>
        <li>
            <a href="ProfileServlet" class="text-green-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-user"></i> <!-- User icon for profile -->
                <span class="font-medium">Update Profile</span>
            </a>
        </li>
        <li>
            <!-- Logout link -->
            <a href="#" onclick="openLogoutModal()" class="text-green-200 hover:text-white flex items-center space-x-3 transition-colors duration-200 ease-in-out">
                <i class="fas fa-sign-out-alt"></i> <!-- Sign-out icon for logout -->
                <span class="font-medium">Logout</span>
            </a>

            <!-- Modal Background -->
            <div id="logoutModal" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center hidden z-50">
                <!-- Modal Content -->
                <div class="bg-white rounded-lg shadow-lg p-6 max-w-sm w-full">
                    <h2 class="text-xl font-semibold text-gray-800 mb-4">Confirm Logout</h2>
                    <p class="text-gray-600 mb-6">Are you sure you want to log out?</p>
                    <div class="flex justify-end space-x-3">
                        <!-- Cancel Button -->
                        <button onclick="closeLogoutModal()" class="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400 transition">
                            Cancel
                        </button>
                        <!-- Confirm Logout Button -->
                        <a href="LogoutServlet" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition">
                            Logout
                        </a>
                    </div>
                </div>
            </div>

            <script>
                // Function to open the modal
                function openLogoutModal() {
                    document.getElementById('logoutModal').classList.remove('hidden');
                }

                // Function to close the modal
                function closeLogoutModal() {
                    document.getElementById('logoutModal').classList.add('hidden');
                }
            </script>
        </li>
    </ul>
</nav>
