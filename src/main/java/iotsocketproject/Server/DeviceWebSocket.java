/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotsocketproject.Server;


import static iotsocketproject.Server.Main.data;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Xeno
 */
@ServerEndpoint(value = "/endpoint", decoders = {DeviceDecoder.class})
public class DeviceWebSocket {
    public static Device currentDevice = new Device();

private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    /*
    @OnMessage
    public String onMessage(String message) {
        System.out.println(message);
        return null;
    }
*/
    @OnMessage
    public void broadcastFigure(Device device, Session session) throws IOException, EncodeException {
        currentDevice.setDeviceID(device.getDeviceID());
        currentDevice.setTemp(device.getTemp());
        System.out.println("received device msg_id: " + device.getDeviceID());
        System.out.println("received device msg_temp: " + device.getTemp());
        data.sendToDatabase(device.getTemp(), device.getDeviceID()); 
        
        //for (Session peer : peers) {
        //    if (!peer.equals(session))  { 
        //        peer.getBasicRemote().sendObject(device);
        //    }
        }
    
    
    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println(t);
    }
    
}
