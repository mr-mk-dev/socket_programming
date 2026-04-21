package org.example.BIT_STUFFING_TCP;

import java.net.*;
import  java.io.*;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server Started...");
            Socket s = ss.accept();
            System.out.println("Client connected...");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);

            String data = in.readLine();
            StringBuilder sb = new StringBuilder();
            System.out.println("Data Received From Client : "+ data);
            int counter  = 0;
            for(int i = 8; i <data.length()-8; i++){
                char c = data.charAt(i);
                if(counter == 6) {
                    counter = 0; i++;
                }
                else if(c == '1') {
                    sb.append(c);
                    counter++;
                }else counter=0;
            }
            System.out.println("Original Data : " + sb);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
