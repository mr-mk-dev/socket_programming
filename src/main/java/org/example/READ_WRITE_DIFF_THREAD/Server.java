package org.example.READ_WRITE_DIFF_THREAD;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server started at port " + ss.getLocalPort());

            while (true) {
                Socket socket = ss.accept();
                System.out.println("Client Connected...");
                System.out.println("Client Port: " + socket.getPort());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true
                );

                // 🧵 READ THREAD
                new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = in.readLine()) != null) {
                            System.out.println("Client says: " + msg);
                        }
                    } catch (Exception e) {
                        System.out.println("Read thread ended");
                    }
                }).start();

                // 🧵 WRITE THREAD
                new Thread(() -> {
                    try {
                        out.println("Server Time: " + LocalDateTime.now());
                    } catch (Exception e) {
                        System.out.println("Write thread ended");
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}