package org.example.STOP_WAIT;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost",5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter something to Send");

            while(true){
                String val = sc.readLine();
                out.println(val);

                String serverRep = in.readLine();
                if(!serverRep.equals("ACK")) break;
                System.out.println("Server Respose with : " + serverRep);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
