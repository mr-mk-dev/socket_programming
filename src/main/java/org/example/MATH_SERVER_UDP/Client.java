package org.example.MATH_SERVER_UDP;

import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            final int PORT = 5000;
            String val = "Hello";
            byte[] sendFirst = val.getBytes();
            DatagramPacket sendFirstPacket = new DatagramPacket(sendFirst,
                    sendFirst.length,
                    address,
                    PORT);
            socket.send(sendFirstPacket);

            byte[] recMenu  = new byte[1024];
            DatagramPacket  packet = new DatagramPacket(recMenu,recMenu.length);
            socket.receive(packet);
            String str = new String(packet.getData(),0,packet.getLength());
            System.out.println(str);

            System.out.print("Enter Option along with 2 operand with space separated : ");
            Scanner sc = new Scanner(System.in);
            String operationChoice = sc.nextLine();
            byte[] optionByte = operationChoice.getBytes();
            DatagramPacket optionPacket = new DatagramPacket(optionByte,
                    optionByte.length,
                    address,
                    PORT
            );
            socket.send(optionPacket);
            System.out.println("Values Send Successfully : "+operationChoice);

            byte[] arr = new byte[1024];
            DatagramPacket finalPacket = new DatagramPacket(arr,arr.length);
            socket.receive(finalPacket);
            System.out.println(new String(finalPacket.getData(),0,finalPacket.getLength()));





        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
