/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RMIInterface;
import model.SV;

/**
 *
 * @author ADMIN
 */
public class ClientController {
    private Registry registry;
    private RMIInterface rmi;
    public ClientController() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 3232);
            rmi = (RMIInterface)registry.lookup("rmiServer");
        } catch (RemoteException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<SV> getDSSV(String ten) throws RemoteException
    {
        return rmi.getDSSV(ten);
    }
    
    public void updateSV(SV sv) throws RemoteException
    {
        rmi.updateSV(sv);
    }
}
