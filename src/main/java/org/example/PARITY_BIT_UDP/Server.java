package org.example.PARITY_BIT_UDP;
import  java.net.*;

public class Server {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(5000);
            byte[] recByte = new byte[1024];
            DatagramPacket recPacket = new DatagramPacket(recByte,recByte.length);
            socket.receive(recPacket);

            String str = new String(recPacket.getData(),0,recPacket.getLength());

            int counter = 0;
            for(int i = 0 ; i<str.length(); i++){
                char c = str.charAt(i);
                if(c == '1') counter++;
            }
            if(counter%2 == 0) System.out.println("Correct Data Received...");
            else System.err.println("Invalid Data Received...");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
