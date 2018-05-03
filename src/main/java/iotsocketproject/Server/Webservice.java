package iotsocketproject.Server;

import static iotsocketproject.Server.DeviceWebSocket.currentDevice;
import static iotsocketproject.Server.Main.data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static sun.audio.AudioDevice.device;


public class Webservice extends HttpServlet{
 private String value;
 public static String temp;
 public static String deviceID;
 
 public String lista;
 
    public String test(List<String> list) { 
        for (int i = 0; i < list.size(); i++){
           lista += list.get(i);
           lista += "\n";
       }   
       return lista;
   }
 
   public void init() throws ServletException{}

 @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
      List<String> list = data.getFromDatabase();
      temp = currentDevice.getTemp();
      deviceID = currentDevice.getDeviceID();
      value = request.getParameter("value");
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();  
      System.out.println("temp from debugg"+ temp);
      out.println("<!DOCTYPE html><HTML lang=\"en\"><HEAD><TITLE>Temperature Data</TITLE><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css\" integrity=\"sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy\" crossorigin=\"anonymous\"><meta charset=\"UTF-8\"></HEAD><BODY><div class=\"jumbotron\">");
      out.println("<h1>Device Id: "+ deviceID + "</h1>\n" +
"       \n" +
"        <form action = \"WebApp\" method = \"POST\">");
      if ("1".equals(value)) {
           out.println("<textarea readonly name=\"output\" rows=\"10\" cols=\"50\" placeholder=\"Your data will be displayed here...\">" +
""+                    "This is your current temp:"+temp+"</textarea>");}
      if ("2".equals(value)) {
           out.println("<textarea readonly name=\"message\" rows=\"10\" cols=\"50\" placeholder=\"Your data will be displayed here...\">" +
""+                    list+"</textarea>");}
      list.removeAll(list);
      
      
      out.println("<br>\n" +
"            <select name=\"value\">\n" +
"                <option value=\"1\">Current</option>\n" +
"                <option value=\"2\">Historical</option>\n" +
"            </select>\n" +
"            \n" +
"            <input type = \"submit\" class=\"btn btn-primary btn-sm\" value = \"Submit\">\n" + 
"        </form>");
      out.println("<a href=\"javascript:history.back()\">Go Back</a></div></BODY></HTML>");
   } 
   
   public void doPost(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, 
      IOException{ 
    
       doGet(request, response);
       
   } 
}