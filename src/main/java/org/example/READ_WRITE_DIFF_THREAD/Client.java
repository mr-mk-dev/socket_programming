package org.example.READ_WRITE_DIFF_THREAD;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            new Thread(()->{
                while(true){
                    try {
                        Scanner scanner = new Scanner(System.in);
                        String string = scanner.nextLine();
                        out.println(string);
                    } catch (Exception ignored) {}
                }
            }).start();

            new Thread(()->{
                try {
                    Thread.sleep(10000);
                    String response = in.readLine();
                    System.out.println(response);
                    socket.close();
                }catch (Exception ignored){}

            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}