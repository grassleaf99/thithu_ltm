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
import java.io.ObjectOutput;
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
public class ServerController {
    private DatagramSocket server;
    private DAO dao;
    
    public ServerController() {
        try {
            server = new DatagramSocket(5555);
        } catch (SocketException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = new DAO();
        while(true)
        {
            listening();
        }
    }
    
    private void listening()
    {
        try {
            byte[] rbyte = new byte[1024];
            DatagramPacket  rDatagramPacket = new DatagramPacket(rbyte, rbyte.length);
            server.receive(rDatagramPacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(rbyte);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = null;
            try {
                o = ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(o instanceof String)
            {
                String ten = (String)o;
                ArrayList<SV> result = dao.getDSSV(ten);
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(result);
                oos.flush();
                
                InetAddress address = rDatagramPacket.getAddress();
                int clientport = rDatagramPacket.getPort();
                byte[] sbyte  = baos.toByteArray();
                DatagramPacket sDatagramPacket = new DatagramPacket(sbyte, sbyte.length, address, clientport);
                server.send(sDatagramPacket);
            }
            else
            {
                if(o instanceof SV)
                {
                    SV sv = (SV)o;
                    dao.updateSV(sv);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
