package ies.lab2.e1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})

public class MyFirstServlet extends HttpServlet {
    private static final long serialVersionUID = -1915463532411657451L;
 
  @Override
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException 
  {
    PrintWriter out = response.getWriter();
    String user_name = request.getParameter("name");

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Request Parameters Example</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h3>Basic Servlet</h3>");
    
    if (user_name!= null) {
        out.println("Name:");
        out.println(" = " + user_name + "<br>");
    } else {
        out.println("No Parameters, Please enter some");
    }
    out.println("</body>");
    out.println("</html>");

  }
   
  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
