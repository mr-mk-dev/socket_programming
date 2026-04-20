package org.example.BYTE_STUFF_TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server Started...");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Client Connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String deStuffData = in.readLine();
                System.out.println(deStuffData);
                StringBuilder sb = new StringBuilder();

                for (int i = 1; i < deStuffData.length() - 1; i++) {
                    char c = deStuffData.charAt(i);
                    if (c == 'E') {
                        sb.append(deStuffData.charAt(i + 1));
                        i++;
                    } else
                        sb.append(c);
                }
                System.out.println("Original Data : " + sb);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
