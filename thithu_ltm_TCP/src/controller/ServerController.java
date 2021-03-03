/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SV;

/**
 *
 * @author ADMIN
 */
public class ServerController {
    private DAO dao;
    private ServerSocket serverSocket;

    public ServerController() {
        dao = new DAO();
        try {
            serverSocket = new ServerSocket(3333);
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true)
        {
            listening();
        }
    }
    
    public void listening()
    {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            Socket socket = serverSocket.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
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
                oos.writeObject(result);
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
