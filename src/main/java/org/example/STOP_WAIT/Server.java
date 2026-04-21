package org.example.STOP_WAIT;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server Started...");
            while(true) {
                Socket s = ss.accept();
                System.out.println("Client Connected at port : " + s.getPort());

                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                while (true) {
                    String clientData = in.readLine();
                    if (clientData == null) break;
                    System.out.println("Received Data from client : " + clientData);
                    Thread.sleep(500);
                    out.println("ACK");
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
