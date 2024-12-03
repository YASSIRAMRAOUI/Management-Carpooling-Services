/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-12-02 19:45:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Register</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css\">\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body class=\"flex items-center justify-center min-h-screen bg-gradient-to-r from-blue-500 to-purple-600\">\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    function togglePassword() {\n");
      out.write("        const passwordInput = document.getElementById(\"password\");\n");
      out.write("        const toggleIcon = document.getElementById(\"togglePasswordIcon\");\n");
      out.write("\n");
      out.write("        if (passwordInput.type === \"password\") {\n");
      out.write("            passwordInput.type = \"text\";\n");
      out.write("            toggleIcon.classList.remove(\"fa-eye-slash\");\n");
      out.write("            toggleIcon.classList.add(\"fa-eye\");\n");
      out.write("        } else {\n");
      out.write("            passwordInput.type = \"password\";\n");
      out.write("            toggleIcon.classList.remove(\"fa-eye\");\n");
      out.write("            toggleIcon.classList.add(\"fa-eye-slash\");\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<div class=\"w-full max-w-md px-6 py-8 bg-white rounded-lg shadow-lg\">\n");
      out.write("    <h2 class=\"text-3xl font-semibold text-center text-gray-700 mb-4\">Create an Account</h2>\n");
      out.write("    <p class=\"text-center text-gray-500 mb-8\">Join our community today</p>\n");
      out.write("    \n");
      out.write("    ");
 String errorMessage = (String) request.getAttribute("errorMessage"); if (errorMessage != null) { 
      out.write("\n");
      out.write("    <div class=\"bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4\" role=\"alert\">\n");
      out.write("        <span class=\"block sm:inline\">");
      out.print( errorMessage );
      out.write("</span>\n");
      out.write("    </div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("    \n");
      out.write("    <form action=\"RegisterServlet\" method=\"POST\">\n");
      out.write("        <div class=\"mb-4\">\n");
      out.write("            <label for=\"name\" class=\"block text-sm font-medium text-gray-600\">Name</label>\n");
      out.write("            <input type=\"text\" id=\"name\" name=\"name\" required\n");
      out.write("                   class=\"w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500\">\n");
      out.write("        </div>\n");
      out.write("        <div class=\"mb-4\">\n");
      out.write("            <label for=\"email\" class=\"block text-sm font-medium text-gray-600\">Email</label>\n");
      out.write("            <input type=\"email\" id=\"email\" name=\"email\" required\n");
      out.write("                   class=\"w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500\">\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"mb-4\">\n");
      out.write("            <label for=\"password\" class=\"block text-sm font-medium text-gray-600\">Password</label>\n");
      out.write("            <div class=\"flex items-center mt-1 bg-gray-50 border rounded-lg\">\n");
      out.write("                <input type=\"password\" id=\"password\" name=\"password\" required\n");
      out.write("                    class=\"w-full px-4 py-2 text-gray-700 bg-gray-50 border-none rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500\">\n");
      out.write("                <!-- Eye icon to toggle password visibility -->\n");
      out.write("                <span class=\"px-3 flex items-center cursor-pointer\" onclick=\"togglePassword()\">\n");
      out.write("                    <i id=\"togglePasswordIcon\" class=\"fas fa-eye-slash text-gray-500\"></i>\n");
      out.write("                </span>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"mb-4\">\n");
      out.write("            <label for=\"phone_number\" class=\"block text-sm font-medium text-gray-600\">Phone Number</label>\n");
      out.write("            <input type=\"text\" id=\"phone_number\" name=\"phone_number\"\n");
      out.write("                   class=\"w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500\">\n");
      out.write("        </div>\n");
      out.write("        <div class=\"mb-6\">\n");
      out.write("            <label for=\"role\" class=\"block text-sm font-medium text-gray-600\">Role</label>\n");
      out.write("            <select id=\"role\" name=\"role\" required\n");
      out.write("                    class=\"w-full px-4 py-2 mt-1 text-gray-700 bg-gray-50 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500\">\n");
      out.write("                <option value=\"\">Select a role</option>\n");
      out.write("                <option value=\"Driver\">Driver</option>\n");
      out.write("                <option value=\"Passenger\">Passenger</option>\n");
      out.write("            </select>\n");
      out.write("        </div>\n");
      out.write("        <button type=\"submit\" class=\"w-full px-4 py-2 font-semibold text-white bg-gradient-to-r from-blue-600 to-green-600 rounded-lg hover:bg-gradient-to-r hover:from-blue-500 hover:to-purple-600 focus:outline-none focus:ring-2 focus:ring-blue-500\">\n");
      out.write("            Register\n");
      out.write("        </button>\n");
      out.write("    </form>\n");
      out.write("    \n");
      out.write("    <p class=\"mt-6 text-sm text-center text-gray-600\">\n");
      out.write("        Already have an account? <a href=\"login.jsp\" class=\"text-blue-500 hover:underline\">Log in here</a>\n");
      out.write("    </p>\n");
      out.write("</div>\n");
      out.write("\n");
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
