
package iotsocketproject.Server;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import java.util.Properties;

public class dbHandler {
    Calendar rightNow = Calendar.getInstance();
    public static String username = "";
    public static String password = "";
    public String connectionString = "";
    public Boolean propRead = false;
  
    SimpleDateFormat dateFormatter = new SimpleDateFormat("y-M-d h:m:s");
    
    //Reader for api keys file
    Properties prop = new Properties();
    InputStream input = null;
    
    
    //Method for storing API keys from file to variables in code
    public void fileIn(){
    
        if ( propRead == false ){
            try {
                
		input = getClass().getClassLoader().getResourceAsStream("config.properties");
               
		// load a properties file
		prop.load(input);

		// get the property value and print it out
                this.connectionString=prop.getProperty("database");
		this.username=prop.getProperty("dbuser");
		this.password=prop.getProperty("dbpassword");
                //Dont read API from file after first time
                propRead=true;

            } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("ERROR:" +ex);
            } finally {
                    if (input != null) {
                            try {
                                    input.close();
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                	}
                }
        }
    }

    

    
    public void sendToDatabase(String temp, String deviceId){

        try{
            fileIn();
            String time = time();
            Date now = new Date();
            String time2 = dateFormatter.format(now);
            System.out.println("TIME:" +time2);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String anrop = String.format("INSERT INTO iotdb.Temperature (temp, deviceId, date) VALUES (%s, %s, '%s');", temp, deviceId, time2);
            Connection con = DriverManager.getConnection(connectionString, username, password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(anrop);
            stmt.close();
         
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
    public List<String> getFromDatabase(){
        List<Integer> list = new ArrayList<>(); 
        List<String> dateList = new ArrayList<>();
        try{
         
            fileIn();       
            Class.forName("com.mysql.cj.jdbc.Driver");
            String anrop =  String.format("SELECT * FROM iotdb.Temperature order by id desc limit 10;");
            Connection con = DriverManager.getConnection(connectionString, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(anrop);
         
         
        if(rs.next()){
            while(rs.next()){
               // list.add(rs.getInt("temp"));
                dateList.add(String.format("Temp: [%d] - Date: [%s] \n",rs.getInt("temp"), rs.getString("date")));
                System.out.println("DATUM: "+ rs.getString("date"));
                }
            
            stmt.close();
            }else{
                dateList.add("No Connection");

            }
            
            
          
        }   catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
            }
        return dateList;
    }
    
    public String time(){
        String time = rightNow.get(Calendar.YEAR) + "-" + rightNow.get(Calendar.MONTH) + "-" + rightNow.get(Calendar.DAY_OF_MONTH) +  " " +  rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE) + ":" + rightNow.get(Calendar.SECOND);
        return time;
    }
}