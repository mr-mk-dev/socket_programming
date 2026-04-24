package org.example.FILE_SERVER;

import java.net.*;
import java.io.*;

public class UDPFileServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("Server Started...");

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket request = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(request);

                String fileName = new String(request.getData(), 0, request.getLength());
                System.out.println("Client requested: " + fileName);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                File file = new File(fileName);

                if (!file.exists()) {
                    String msg = "FILE_NOT_FOUND";
                    byte[] sendData = msg.getBytes();

                    DatagramPacket response =
                            new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                    socket.send(response);
                } else {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = br.readLine()) != null) {
                        byte[] sendData = line.getBytes();

                        DatagramPacket response =
                                new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                        socket.send(response);

                        Thread.sleep(100); // small delay (important in UDP)
                    }

                    // END signal
                    byte[] endMsg = "END".getBytes();
                    socket.send(new DatagramPacket(endMsg, endMsg.length, clientAddress, clientPort));

                    br.close();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}