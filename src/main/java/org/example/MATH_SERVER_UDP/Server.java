package org.example.MATH_SERVER_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server {
    public static void main(String[] args) {
       try{
           DatagramSocket socket = new DatagramSocket(5000);
           System.out.println("UDP SERVER STARTED...");
           byte[] rec = new byte[1024];
           DatagramPacket recPacket1 = new DatagramPacket(rec,rec.length);
           socket.receive(recPacket1);

           String menu = "1 for add , 2 for sub , 3 for sub , 4 for exit";
           byte[] menuByte = menu.getBytes();
           DatagramPacket sendMenu = new DatagramPacket(menuByte,menuByte.length,recPacket1.getAddress(),recPacket1.getPort());
           socket.send(sendMenu);

           byte[] arr = new byte[1024];
           DatagramPacket receivingOprPacket = new DatagramPacket(arr,arr.length);
           socket.receive(receivingOprPacket);
           System.out.println("Received Value : " + new String(receivingOprPacket.getData(),0,receivingOprPacket.getLength()));

           byte[] sendVal = getBytes(receivingOprPacket);
           DatagramPacket finalPacket = new DatagramPacket(sendVal,sendVal.length, recPacket1.getAddress(),recPacket1.getPort());
           socket.send(finalPacket);


       } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getBytes(DatagramPacket receivingOprPacket) {
        String str = new String(receivingOprPacket.getData(),0, receivingOprPacket.getLength());
        String[] strArr = str.split(" ");
        int operator = Integer.parseInt(strArr[0]);
        int opr1 = Integer.parseInt(strArr[1]);
        int opr2 = Integer.parseInt(strArr[2]);
        int finalVal = 0;
        if(operator == 1) finalVal = opr1+opr2;
        else if(operator == 2 ) finalVal = opr1 - opr2;
        else if(operator == 3 ) finalVal = opr1 * opr2;
        else operator = Integer.MAX_VALUE;

        byte[] sendVal = (String.valueOf(finalVal)).getBytes();
        return sendVal;
    }
}
