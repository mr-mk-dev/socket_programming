package org.example.BYTE_STUFF_TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",5000);
            System.out.println("Client Started...");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter data here : ");
            String data = sc.readLine();
            StringBuilder sb = new StringBuilder();
            sb.append('F');
            for(int i = 0; i<data.length(); i++){
                char c = data.charAt(i);
                if(c =='E' || c == 'F'){
                    sb.append('E');
                    sb.append(c);
                }else
                    sb.append(c);
            }
            sb.append('F');
            System.out.println("Original data : "+data);
            System.out.println("Stuffed Data : "+ sb);
            out.println(sb);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
