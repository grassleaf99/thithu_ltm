/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface RMIInterface extends Remote{
    public ArrayList<SV> getDSSV(String ten) throws RemoteException;
    public void updateSV(SV sv) throws RemoteException;
}
