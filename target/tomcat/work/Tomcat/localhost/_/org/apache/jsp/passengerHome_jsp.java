/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-12-09 16:31:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.HttpSession;

public final class passengerHome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Passenger Home</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"bg-gray-100 text-gray-900 font-sans\">\r\n");
      out.write("<div class=\"flex\">\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "userSidebar.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Main Content -->\r\n");
      out.write("    <div class=\"flex-1 p-6\">\r\n");
      out.write("        <!-- Header Section -->\r\n");
      out.write("        <h2 class=\"text-2xl font-semibold mb-4\">\r\n");
      out.write("            Welcome, <span class=\"text-indigo-600\">");
      out.print( session.getAttribute("name") );
      out.write("</span>\r\n");
      out.write("        </h2>\r\n");
      out.write("\r\n");
      out.write("        <!-- Available Rides Section -->\r\n");
      out.write("        <h1 class=\"text-3xl font-bold mb-6 text-gray-800\">Available Rides</h1>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6\">\r\n");
      out.write("            ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /passengerHome.jsp(24,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("ride");
    // /passengerHome.jsp(24,12) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/passengerHome.jsp(24,12) '${availableRides}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${availableRides}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                <div class=\"bg-white shadow-md rounded-lg p-4 relative\">\r\n");
          out.write("                    <div class=\"flex items-center space-x-4 mb-4\">\r\n");
          out.write("                        <div class=\"flex items-center justify-center bg-indigo-100 text-indigo-600 rounded-full w-12 h-12\">\r\n");
          out.write("                            <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"h-6 w-6\" fill=\"none\" viewBox=\"0 0 24 24\" stroke=\"currentColor\" stroke-width=\"2\">\r\n");
          out.write("                                <path stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M8 7c1.657 0 3-1.343 3-3S9.657 1 8 1 5 2.343 5 4s1.343 3 3 3z\" />\r\n");
          out.write("                                <path stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M20.8 20.8a6.8 6.8 0 00-13.6 0\" />\r\n");
          out.write("                            </svg>\r\n");
          out.write("                        </div>\r\n");
          out.write("                        <div>\r\n");
          out.write("                            <h3 class=\"text-lg font-semibold text-gray-700\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.driverName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</h3>\r\n");
          out.write("                        </div>\r\n");
          out.write("                    </div>\r\n");
          out.write("                    <!-- Ride Information -->\r\n");
          out.write("                    <div class=\"flex items-center justify-between mb-2\">\r\n");
          out.write("                        <div class=\"flex items-center\">\r\n");
          out.write("                            <i class=\"fa-solid fa-plane-departure text-blue-500 text-l mr-2\"></i>\r\n");
          out.write("                            <span class=\"font-semibold text-gray-700\">From:</span>\r\n");
          out.write("                            <span class=\"text-gray-600 ml-1\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.depart}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</span>\r\n");
          out.write("                        </div>\r\n");
          out.write("                        <span class=\"text-gray-400 mx-4\">\r\n");
          out.write("                            <i class=\"fa-solid fa-plane text-blue-500 text-l mr-2\"></i>\r\n");
          out.write("                        </span>\r\n");
          out.write("                        <div class=\"flex items-center\">\r\n");
          out.write("                            <i class=\"fa-solid fa-plane-arrival text-blue-500 text-l mr-2\"></i>\r\n");
          out.write("                            <span class=\"font-semibold text-gray-700\">To:</span>\r\n");
          out.write("                            <span class=\"text-gray-600 ml-1\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.destination}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</span>\r\n");
          out.write("                        </div>\r\n");
          out.write("                    </div>\r\n");
          out.write("\r\n");
          out.write("                    <div class=\"flex items-center justify-between\">\r\n");
          out.write("                        <div class=\"flex items-center text-gray-700\">\r\n");
          out.write("                            <i class=\"fas fa-dollar-sign text-yellow-500 text-xl mr-2\"></i>\r\n");
          out.write("                            <span>Fare:</span>\r\n");
          out.write("                            <span class=\"font-bold text-green-600 ml-1 mr-1\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.fare}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(" dh</span>\r\n");
          out.write("                        </div>\r\n");
          out.write("\r\n");
          out.write("                        <div class=\"flex items-center text-gray-700\">\r\n");
          out.write("                            <i class=\"fa-solid fa-user-group text-yellow-500 text-xl mr-1\"></i>\r\n");
          out.write("                            <span>Number of places:</span>\r\n");
          out.write("                            <span class=\"font-bold text-green-600 ml-1 mr-1\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.numberOfPlaces}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</span>\r\n");
          out.write("                        </div>\r\n");
          out.write("                    </div>\r\n");
          out.write("\r\n");
          out.write("                        <form action=\"PassengerHomeServlet\" method=\"post\" class=\"mt-2 flex justify-between\">\r\n");
          out.write("                        <div class=\"flex items-center text-gray-700\">\r\n");
          out.write("                            <i class=\"fa-solid fa-person text-yellow-500 text-xl mr-1\"></i>\r\n");
          out.write("                            <span>Places:</span>\r\n");
          out.write("                        </div>\r\n");
          out.write("                            <input type=\"hidden\" name=\"rideId\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\">\r\n");
          out.write("                            <input type=\"number\" name=\"place\" min=\"1\" max=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ride.numberOfPlaces}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" required class=\"border rounded-md py-2 px-1\">\r\n");
          out.write("                            <button type=\"submit\" name=\"action\" value=\"accept\"\r\n");
          out.write("                                    class=\"bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-6 rounded-md flex items-center focus:outline-none focus:ring-2 focus:ring-green-400\">\r\n");
          out.write("                                <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"h-5 w-5 mr-2\" fill=\"none\" viewBox=\"0 0 24 24\" stroke=\"currentColor\" stroke-width=\"2\">\r\n");
          out.write("                                    <path stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M5 13l4 4L19 7\" />\r\n");
          out.write("                                </svg>\r\n");
          out.write("                                Accept\r\n");
          out.write("                            </button>\r\n");
          out.write("                        </form>\r\n");
          out.write("                </div>\r\n");
          out.write("            ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
