package iotsocketproject.Server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Webservice extends HttpServlet{

 private String value;
 private String temp = "15";
 private String[] historical;
 
    public void init() throws ServletException 
    {              
           
    }

   public void doGet(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, 
      IOException{ 
       value = request.getParameter("value");
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();  
      out.println("<!DOCTYPE html><HTML lang=\"en\"><HEAD><TITLE>Temperature Data</TITLE><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css\" integrity=\"sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy\" crossorigin=\"anonymous\"><meta charset=\"UTF-8\"></HEAD><BODY>");
      out.println("<h1>Select what data you want to view:</h1>\n" +
"       \n" +
"        <form action = \"WebApp\" method = \"POST\">");
      if ("1".equals(value)) out.println("<textarea readonly name=\"output\" rows=\"30\" cols=\"50\" placeholder=\"Your data will be displayed here...\">" +
""+                                          "This is your current temp:"+temp+"</textarea>");
      if ("2".equals(value)) out.println("<textarea readonly name=\"message\" rows=\"30\" cols=\"50\" placeholder=\"Your data will be displayed here...\">" +
""+                                          "This is your historical temp</textarea>");
      out.println("<br>\n" +
"            <select name=\"value\">\n" +
"                <option value=\"1\">Current</option>\n" +
"                <option value=\"2\">Historical</option>\n" +
"            </select>\n" +
"            \n" +
"            <input type = \"submit\" value = \"Submit\">\n" +
"        </form>");
      out.println("<a href=\"javascript:history.back()\">Go Back</a></BODY></HTML>");
   } 
   
   public void doPost(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, 
      IOException{ 
       doGet(request, response);
   } 
}