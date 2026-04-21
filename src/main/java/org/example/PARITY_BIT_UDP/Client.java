package org.example.PARITY_BIT_UDP;

import java.net.*;

public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket  socket = new DatagramSocket();
            InetAddress add = InetAddress.getByName("localhost");



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
