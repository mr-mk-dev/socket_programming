package org.example.CHAT_SERVER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            new Thread(() -> {
                try{
                        System.err.println("Server Returned "+in.readLine());

                }catch (Exception e){
                }
            }).start();


            new Thread(() -> {
                try{
                    while (true) {
                        Scanner sc = new Scanner(System.in);
                        String val = sc.nextLine();
                        out.println(val);
                    }
                } catch (Exception e) {

                }
            }).start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
