package org.example.UDP_PROGRAM;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            String s1=("USER:"+System.getProperty("user.name")+" OS: "+System.getProperty("os.name")).toLowerCase();
            System.out.println("Data Sending : "+ s1);
            byte[] sendData = s1.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(sendData,sendData.length,address,5000);

            socket.send(packet);

            byte[] arr = new byte[1024];
            DatagramPacket recPac = new DatagramPacket(arr,arr.length);
            socket.receive(recPac);
            String recData = new String(recPac.getData(),0,recPac.getLength());
            System.out.println("Received Data : "+recData);


        } catch (Exception ignored) {}
    }
}
