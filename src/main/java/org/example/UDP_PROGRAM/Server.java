package org.example.UDP_PROGRAM;

import  java.net.*;

public class Server {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(5000);
            byte[] arr = new byte[1024];
            DatagramPacket packet = new DatagramPacket(arr,arr.length);

            while (true) {
                socket.receive(packet);
                String receivedData = new String(packet.getData(), 0, packet.getLength());
                String modifiedDat = receivedData.toUpperCase();
                byte[] sendData = modifiedDat.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length,
                        packet.getAddress(), packet.getPort());
                socket.send(sendPacket);
                System.out.println("Data send successfully");
            }
        }catch(Exception ignored){

        }
    }
}
