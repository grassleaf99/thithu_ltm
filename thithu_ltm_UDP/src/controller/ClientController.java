/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SV;

/**
 *
 * @author ADMIN
 */
public class ClientController {
    private DatagramSocket client;

    public ClientController() {
        try {
            client = new DatagramSocket(6666);
        } catch (SocketException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(Object o)
    {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            
            InetAddress address = InetAddress.getByName("localhost");
            byte[] sbyte = baos.toByteArray();
            DatagramPacket sDatagramPacket = new DatagramPacket(sbyte, sbyte.length, address, 5555);
            client.send(sDatagramPacket);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<SV> readData()
    {
        try {
            byte[] rbyte = new byte[1024];
            DatagramPacket rDatagramPacket = new DatagramPacket(rbyte, rbyte.length);
            client.receive(rDatagramPacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(rbyte);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = null;
            try {
                o = ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(o instanceof ArrayList)
                return (ArrayList<SV>)o;
           
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    public void closeConnect()
    {
        client.close();
    }
}
