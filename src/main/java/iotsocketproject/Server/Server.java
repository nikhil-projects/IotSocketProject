/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsocketproject.Server;

import java.util.List;

/**
 *
 * @author User
 */
 public class Server{
 
     
     //Johns method!
     public void sendTempToDatabase(String temp , String deviceId ){
         //Change this .
         temp = "30";
         deviceId = "01";
         dbHandler db = new dbHandler();
         db.sendToDatabase(temp, deviceId);
         
     }
     
     //Adams method
     public List <Integer> GetFromDatabase(){
         dbHandler db = new dbHandler();
         List <Integer> l = db.getFromDatabase();
         return l;
     }
 
 }