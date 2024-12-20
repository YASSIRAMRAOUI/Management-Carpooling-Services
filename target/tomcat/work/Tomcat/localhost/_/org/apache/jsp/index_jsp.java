/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-12-14 12:42:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Welcome to Carpooling Service</title>\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body class=\"flex flex-col h-screen bg-gray-100 font-sans\">\n");
      out.write("\n");
      out.write("    <!-- Header Section with Logo -->\n");
      out.write("    <header class=\"w-full bg-gradient-to-r from-blue-600 to-green-600 text-white text-center py-4 flex-shrink-0\">\n");
      out.write("        <div class=\"flex items-center justify-center space-x-4\">\n");
      out.write("            <img src=\"assets/Carpooling_Logo.png\" alt=\"Carpooling Service Logo\" class=\"w-14 h-14 animate-spin-slow\">\n");
      out.write("            <h1 class=\"text-4xl font-bold\">Carpooling Service</h1>\n");
      out.write("        </div>\n");
      out.write("        <p class=\"text-gray-100 mt-2\">Your easy way to find rides and connect with fellow travelers.</p>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <!-- Main Content Section with Image -->\n");
      out.write("    <div class=\"flex flex-1 items-center justify-center w-full\">\n");
      out.write("        <div class=\"flex flex-col items-center w-full max-w-3xl px-6 text-center animate-fadeIn\">\n");
      out.write("            <!-- Content Image -->\n");
      out.write("            <img src=\"assets/carpooling_image.jpg\" alt=\"Carpooling Image\" class=\"w-full h-64 object-cover rounded-lg shadow-lg mb-6\">\n");
      out.write("\n");
      out.write("            <h2 class=\"text-3xl font-semibold text-gray-800 mb-4\">Welcome to Our Carpooling Service</h2>\n");
      out.write("            <p class=\"text-gray-600 mb-8 leading-relaxed\">\n");
      out.write("                Join our community of drivers and passengers who share rides, save on travel costs, and contribute to reducing carbon emissions.\n");
      out.write("                Whether you're a driver with extra seats or a passenger looking for a convenient ride, our platform connects you with the right people.\n");
      out.write("            </p>\n");
      out.write("\n");
      out.write("            <!-- Navigation Buttons with Hover Animation -->\n");
      out.write("            <div class=\"space-x-6\">\n");
      out.write("                <a href=\"login.jsp\" class=\"inline-block px-8 py-3 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-500 transform hover:scale-105 transition duration-200\">Login</a>\n");
      out.write("                <a href=\"register.jsp\" class=\"inline-block px-8 py-3 bg-green-600 text-white font-semibold rounded-lg hover:bg-green-500 transform hover:scale-105 transition duration-200\">Register</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <!-- Footer Section -->\n");
      out.write("    <footer class=\"w-full bg-gray-900 text-white text-center py-4 flex-shrink-0\">\n");
      out.write("        <p class=\"text-sm\">&copy; 2024 Carpooling Service - All Rights Reserved</p>\n");
      out.write("    </footer>\n");
      out.write("\n");
      out.write("    <!-- Custom Animations -->\n");
      out.write("    <style>\n");
      out.write("        @keyframes fadeIn {\n");
      out.write("            from { opacity: 0; }\n");
      out.write("            to { opacity: 1; }\n");
      out.write("        }\n");
      out.write("        .animate-fadeIn {\n");
      out.write("            animation: fadeIn 1.5s ease-out forwards;\n");
      out.write("        }\n");
      out.write("        @keyframes spinSlow {\n");
      out.write("            from { transform: rotate(0deg); }\n");
      out.write("            to { transform: rotate(360deg); }\n");
      out.write("        }\n");
      out.write("        .animate-spin-slow {\n");
      out.write("            animation: spinSlow 20s linear infinite;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
