package org.example.TCP_ECHO_SERVER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(
        Socket socket  = new Socket("localhost",5000);
        ){
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            System.out.print("Enter something : ");
            String val = scanner.readLine();
            out.println(val);
            String receivedData = in.readLine();
            System.out.println("Received Message : "+receivedData);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
