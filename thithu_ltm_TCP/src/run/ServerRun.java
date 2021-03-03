/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import controller.ServerController;

/**
 *
 * @author ADMIN
 */
public class ServerRun {
    public static void main(String[] args) {
        System.out.println("TCP server is running...");
        new ServerController();
    }
}
