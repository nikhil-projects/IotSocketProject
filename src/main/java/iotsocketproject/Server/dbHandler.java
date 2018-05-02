
package iotsocketproject.Server;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Properties;

public class dbHandler {
    Calendar rightNow = Calendar.getInstance();
    
    String username = "";
    String password = "";
    String connectionString = "";
    
    public void sendToDatabase(String temp, String deviceId){
        String time = time();
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String anrop = String.format("INSERT INTO iotdb.Temperature (temp, deviceId, date) VALUES (%s, %s, '%s');", temp, deviceId, time);
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
         String anrop =  String.format("SELECT * FROM iotdb.Temperature order by id desc limit 10;");
         Connection con = DriverManager.getConnection(connectionString, username, password);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(anrop);
         
         if(rs.next()){
         while(rs.next()){
             list.add(rs.getInt("temp"));
         }
         }else{
                list.add(0);
         }
            System.out.println(list);
    }   catch(Exception e){
        e.printStackTrace();
            System.out.println(e);
    }
        return list;
    }
    
    public String time(){
        String time = rightNow.get(Calendar.YEAR) + "-" + rightNow.get(Calendar.MONTH) + "-" + rightNow.get(Calendar.DAY_OF_MONTH) +  " " +  rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE) + ":" + rightNow.get(Calendar.SECOND);
        return time;
    }
}