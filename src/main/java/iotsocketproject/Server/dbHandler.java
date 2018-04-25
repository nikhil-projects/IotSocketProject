/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsocketproject.Server;
//import com.google.gson.GsonBuilder;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
import java.sql.Connection;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class dbHandler {
    String username = "iotdbuser";
    String password = "iotdbuser123";
    String connectionString = "jdbc:mysql://iotdb.clktdo6naxfv.eu-central-1.rds.amazonaws.com:3306/iotdb";
    public void sendToDatabase(){
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        // String anrop = String.format("INSERT INTO Temperature(%s,Temp) VALUES(%s, Temp);", deviceId, Temp);
        String anrop = String.format("INSERT INTO iotdb.Temperature (temp, deviceId) VALUES (34, 77);");
         Connection con = DriverManager.getConnection(connectionString, username, password);
         Statement stmt = con.createStatement();
         stmt.executeUpdate(anrop);
         
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    public void getFromDatabase(){
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
         
         // Senaste 7 dagarna - för senare anvädning
//        System.out.println(list);
//        for (int i = list.size()-1; i > list.size()-7; i--) {
//            System.out.println(list.get(i));
//            System.out.println(i);
//        }

            
    }   catch(Exception e){
        e.printStackTrace();
    }
    
    }
    
    
    
//     public List <Devices> connectAndQueryDatabase(String choice){
//         List<Devices> listan = new ArrayList<>();   
//         try{
//             
//             String username = "root";
//             String password = "fuckoff22";
//         
//         Class.forName("com.mysql.jdbc.Driver");
//         String anrop =  String.format("SELECT %s FROM devicetable", choice);
//         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/climatestation", username, password);
//         Statement stmt = con.createStatement();
//         ResultSet rs = stmt.executeQuery(anrop);
//         
//         while(rs.next()){
//             Devices d1 = new Devices();  
//             if(choice == "*"){
//             d1.setTemp(rs.getDouble("Temp"));
//             d1.setLuft(rs.getInt("Humidity"));
//             d1.setBelysning(rs.getInt("Light"));
//             d1.setWatt(rs.getInt("watt"));
//             d1.setTimeStamp(rs.getString("timestamp"));
//             listan.add(d1);
//             }
//             else if (choice == "watt"){
//                 d1.setWatt(rs.getInt("watt"));
//                 listan.add(d1);
//             }
//             else if(choice == "Temp"){
//                 d1.setTemp(rs.getDouble("Temp"));
//                 listan.add(d1);
//             }
//            
//           }
//         } catch(Exception e){
//             e.printStackTrace();
//         }
//         return listan;
//     }
    
}
