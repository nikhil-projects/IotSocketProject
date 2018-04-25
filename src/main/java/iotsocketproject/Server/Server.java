/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsocketproject.Server;

/**
 *
 * @author User
 */
 public class Server{
 
     
     //Johns method!
     public void sendTempToDatabase(String temp , String deviceId ){
         //hårdkodade värden, ändra här.
         temp = "30";
         deviceId = "01";
         dbHandler db = new dbHandler();
         db.sendToDatabase(temp, deviceId);
         
     }
     
     //Adams method
     public void GetFromDatabase(String temp, String deviceId){
         dbHandler db = new dbHandler();
         db.getFromDatabase();
     }
 
 }