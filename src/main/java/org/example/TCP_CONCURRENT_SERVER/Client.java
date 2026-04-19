package org.example.TCP_CONCURRENT_SERVER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost", 6000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            System.out.println("System time is : " + in.readLine());
            System.out.println("My Port is : " + in.readLine());





        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
