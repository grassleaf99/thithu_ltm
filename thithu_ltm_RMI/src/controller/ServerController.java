/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RMIInterface;
import model.SV;

/**
 *
 * @author ADMIN
 */
public class ServerController extends UnicastRemoteObject implements RMIInterface{
    private DAO dao;
    private Registry registry;
    public ServerController() throws RemoteException {
        try {
            dao = new DAO();
            registry = LocateRegistry.createRegistry(3232);
            registry.rebind("rmiServer", this);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<SV> getDSSV(String ten) throws RemoteException
    {
        return dao.getDSSV(ten);
    }
    
    public void updateSV(SV sv) throws RemoteException
    {
        dao.updateSV(sv);
    }
}
