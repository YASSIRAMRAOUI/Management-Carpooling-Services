/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-10-31 23:46:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"UTF-8\" />\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n");
      out.write("    <title>Login Form</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"Styles/index.css\">\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <form action=\"LoginServlet\" method=\"POST\">\n");
      out.write("      <h2>Login Form</h2>\n");
      out.write("      \n");
      out.write("      ");

        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
      
      out.write("\n");
      out.write("        <div class=\"error-message\" style=\"color: red;\">");
      out.print( errorMessage );
      out.write("</div>\n");
      out.write("      ");
 } 
      out.write("\n");
      out.write("      \n");
      out.write("      <div>\n");
      out.write("        <label for=\"email\">Email:</label>\n");
      out.write("        <input type=\"text\" id=\"email\" name=\"email\" required />\n");
      out.write("      </div>\n");
      out.write("      \n");
      out.write("      <div>\n");
      out.write("        <label for=\"password\">Password:</label>\n");
      out.write("        <input type=\"password\" id=\"password\" name=\"password\" required />\n");
      out.write("      </div>\n");
      out.write("      \n");
      out.write("      <div>\n");
      out.write("        <button type=\"submit\">Login</button>\n");
      out.write("      </div>\n");
      out.write("      \n");
      out.write("      <p>Don't have an account? <a href=\"register.jsp\">Register here</a></p>\n");
      out.write("    </form>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("<Style>\n");
      out.write("body {\n");
      out.write("    display: flex;\n");
      out.write("    justify-content: center;\n");
      out.write("    align-items: center;\n");
      out.write("    height: 100vh;\n");
      out.write("    background-color: #f0f2f5;\n");
      out.write("    font-family: Arial, sans-serif;\n");
      out.write("    margin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("form {\n");
      out.write("    background-color: #ffffff;\n");
      out.write("    width: 300px;\n");
      out.write("    padding: 20px;\n");
      out.write("    border-radius: 8px;\n");
      out.write("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n");
      out.write("    text-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h2 {\n");
      out.write("    color: #333;\n");
      out.write("    margin-bottom: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("label {\n");
      out.write("    font-weight: bold;\n");
      out.write("    color: #555;\n");
      out.write("    display: block;\n");
      out.write("    margin: 10px 0 5px;\n");
      out.write("    text-align: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write("input[type=\"text\"],\n");
      out.write("input[type=\"password\"] {\n");
      out.write("    width: 100%;\n");
      out.write("    padding: 8px;\n");
      out.write("    margin: 5px 0 15px;\n");
      out.write("    border: 1px solid #ccc;\n");
      out.write("    border-radius: 4px;\n");
      out.write("    box-sizing: border-box;\n");
      out.write("}\n");
      out.write("\n");
      out.write("button[type=\"submit\"] {\n");
      out.write("    width: 100%;\n");
      out.write("    padding: 10px;\n");
      out.write("    background-color: #4CAF50;\n");
      out.write("    color: white;\n");
      out.write("    border: none;\n");
      out.write("    border-radius: 4px;\n");
      out.write("    cursor: pointer;\n");
      out.write("    font-size: 16px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("button[type=\"submit\"]:hover {\n");
      out.write("    background-color: #45a049;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".error-message {\n");
      out.write("    color: red;\n");
      out.write("    margin-bottom: 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("p {\n");
      out.write("    margin-top: 15px;\n");
      out.write("    font-size: 14px;\n");
      out.write("    color: #555;\n");
      out.write("}\n");
      out.write("\n");
      out.write("p a {\n");
      out.write("    color: #4CAF50;\n");
      out.write("    text-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("p a:hover {\n");
      out.write("    text-decoration: underline;\n");
      out.write("}\n");
      out.write("\n");
      out.write("</Style>");
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