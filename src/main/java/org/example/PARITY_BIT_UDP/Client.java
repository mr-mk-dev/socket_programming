package org.example.PARITY_BIT_UDP;

import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket  socket = new DatagramSocket();
            InetAddress add = InetAddress.getByName("localhost");
            int port = 5000;

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Data in binary : ");
            String data = sc.nextLine();

            System.out.print("Do you want to send given data Enter 0 or parity data enter 1 : ");
            int option = sc.nextInt();

            if(option == 1){
                byte[] arr = getValueWithParityAdded(data);
                DatagramPacket sendPacket = new DatagramPacket(arr,arr.length,add,port);
                socket.send(sendPacket);
            }else{
                byte[] arr = data.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(arr,arr.length,add,port);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static  byte[] getValueWithParityAdded (String data){
        StringBuilder sb = new StringBuilder(data);
        int counter =0;
        for(int i = 0 ; i<data.length(); i++){
            if(data.charAt(i) == '1') counter++;
        }
        sb.append(counter%2 == 0 ? '0' : '1' );
        return  sb.toString().getBytes();
    }
}
