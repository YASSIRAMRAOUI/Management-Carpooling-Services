<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Carpooling Service</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex flex-col h-screen bg-gray-100 font-sans">

    <!-- Header Section with Logo -->
    <header class="w-full bg-gradient-to-r from-blue-600 to-green-600 text-white text-center py-4 flex-shrink-0">
        <div class="flex items-center justify-center space-x-4">
            <img src="assets/Carpooling_Logo.png" alt="Carpooling Service Logo" class="w-14 h-14 animate-spin-slow">
            <h1 class="text-4xl font-bold">Carpooling Service</h1>
        </div>
        <p class="text-gray-100 mt-2">Your easy way to find rides and connect with fellow travelers.</p>
    </header>

    <!-- Main Content Section with Image -->
    <div class="flex flex-1 items-center justify-center w-full">
        <div class="flex flex-col items-center w-full max-w-3xl px-6 text-center animate-fadeIn">
            <!-- Content Image -->
            <img src="assets/carpooling_image.jpg" alt="Carpooling Image" class="w-full h-64 object-cover rounded-lg shadow-lg mb-6">

            <h2 class="text-3xl font-semibold text-gray-800 mb-4">Welcome to Our Carpooling Service</h2>
            <p class="text-gray-600 mb-8 leading-relaxed">
                Join our community of drivers and passengers who share rides, save on travel costs, and contribute to reducing carbon emissions.
                Whether you're a driver with extra seats or a passenger looking for a convenient ride, our platform connects you with the right people.
            </p>

            <!-- Navigation Buttons with Hover Animation -->
            <div class="space-x-6">
                <a href="login.jsp" class="inline-block px-8 py-3 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-500 transform hover:scale-105 transition duration-200">Login</a>
                <a href="register.jsp" class="inline-block px-8 py-3 bg-green-600 text-white font-semibold rounded-lg hover:bg-green-500 transform hover:scale-105 transition duration-200">Register</a>
            </div>
        </div>
    </div>

    <!-- Footer Section -->
    <footer class="w-full bg-gray-900 text-white text-center py-4 flex-shrink-0">
        <p class="text-sm">&copy; 2024 Carpooling Service - All Rights Reserved</p>
    </footer>

    <!-- Custom Animations -->
    <style>
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        .animate-fadeIn {
            animation: fadeIn 1.5s ease-out forwards;
        }
        @keyframes spinSlow {
            from { transform: rotate(0deg); }
            to { transform: rotate(360deg); }
        }
        .animate-spin-slow {
            animation: spinSlow 20s linear infinite;
        }
    </style>
</body>
</html>
