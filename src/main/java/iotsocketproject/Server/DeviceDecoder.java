/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsocketproject.Server;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;

/**
 *
 * @author Xeno
 */
public class DeviceDecoder implements Decoder.Text<Device>{

    @Override
    public Device decode(String s) throws DecodeException {
        Gson gson = new Gson();
        Device message = gson.fromJson(s, Device.class);
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    
}
