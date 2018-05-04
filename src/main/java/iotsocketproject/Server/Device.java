/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsocketproject.Server;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author JS
 * Device class for trasfering data to DB and to Front end
 */
public class Device {

    private String deviceId;
    private String temp;
    private String datetime;

        public Device(){}

        public void setDeviceID(String deviceID) {
            this.deviceId = deviceID;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getDeviceID() {
            return deviceId;
        }

        public String getTemp() {
            return temp;
        }

        public String getDatetime() {
            return datetime;
        }


    @Override
    public String toString() {
        return super.toString();
    }

    

    
}
