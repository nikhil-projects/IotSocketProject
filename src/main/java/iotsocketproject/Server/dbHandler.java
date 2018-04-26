
package iotsocketproject.Server;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class dbHandler {
    String username = "iotdbuser";
    String password = "iotdbuser123";
    String connectionString = "jdbc:mysql://iotdb.clktdo6naxfv.eu-central-1.rds.amazonaws.com:3306/iotdb";
    public void sendToDatabase(String temp, String deviceId){
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String anrop = String.format("INSERT INTO iotdb.Temperature (temp, deviceId) VALUES (%s, %s);", temp, deviceId);
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