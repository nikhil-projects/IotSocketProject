
package iotsocketproject.Server;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Properties;

public class dbHandler {
    
    Date date = new Date();
    String username = "";
    String password = "";
    String connectionString = "";
    
    public void sendToDatabase(String temp, String deviceId){
        String time = date.toString();
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String anrop = String.format("INSERT INTO iotdb.Temperature (temp, deviceId, date) VALUES (%s, %s, %s);", temp, deviceId, time);
         Connection con = DriverManager.getConnection(connectionString, username, password);
         Statement stmt = con.createStatement();
         stmt.executeUpdate(anrop);
         
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
    public List<Integer> getFromDatabase(){
        List<Integer> list = new ArrayList<>(); 
        try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         String anrop =  String.format("SELECT * FROM iotdb.Temperature;");
         Connection con = DriverManager.getConnection(connectionString, username, password);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(anrop);
         while(rs.next()){
             list.add(rs.getInt("temp"));
         }
            System.out.println(list);
    }   catch(Exception e){
        e.printStackTrace();
    }
        return list;
    }
}