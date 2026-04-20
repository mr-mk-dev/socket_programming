package org.example.CHAT_SERVER;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(5000);
            System.out.println("Server Started...");
            while (true){
                Socket s = socket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(),true);
                System.out.println("Client connected at : " + s.getPort());

                new Thread(()->{
                    try{
                        String str;
                        while((str = in.readLine()) != null){
                            System.err.println("Client Message : "+ str);
                        }
                    } catch (IOException ignored) {}
                }).start();

                new Thread(() -> {
                    try{
                        Thread.sleep(5000);
                        System.out.println("Sending to Client");
                        out.println("Hello");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
