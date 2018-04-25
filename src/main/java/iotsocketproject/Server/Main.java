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
public class Main {
    public static dbHandler db = new dbHandler();
    
    public static void main(String [] args){
        System.out.println("Hola mundo!");
        db.sendToDatabase();
    }
    
}
