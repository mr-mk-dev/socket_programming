package org.example.FILE_SERVER;

import java.net.*;
import java.io.*;

public class UDPFileClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter file name: ");
            String fileName = userInput.readLine();

            byte[] sendData = fileName.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");

            DatagramPacket request =
                    new DatagramPacket(sendData, sendData.length, serverAddress, 5000);

            socket.send(request);

            // Prepare to receive
            FileWriter fw = new FileWriter("received_" + fileName);

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket response = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(response);

                String msg = new String(response.getData(), 0, response.getLength());

                if (msg.equals("FILE_NOT_FOUND")) {
                    System.out.println("❌ File not found on server");
                    break;
                }

                if (msg.equals("END")) {
                    System.out.println("✅ File received successfully");
                    break;
                }

                fw.write(msg + "\n");
            }

            fw.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}