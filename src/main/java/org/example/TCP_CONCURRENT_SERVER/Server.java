package org.example.TCP_CONCURRENT_SERVER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Server is listening on port 6000...");
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected...");

                Thread thread = new Thread(() ->{
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(LocalDateTime.now());
                        out.println(socket.getPort());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
                thread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
