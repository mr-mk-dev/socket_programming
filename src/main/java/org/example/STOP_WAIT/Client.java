package org.example.STOP_WAIT;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost",5000);
            s.setSoTimeout(3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.print("Enter something to Send : ");
                String val = sc.readLine();
                out.println(val);

                String serverRep = in.readLine();
                if (serverRep == null || !serverRep.equals("ACK")) {
                    System.out.println("Invalid ACK, stopping...");
                    break;
                }
                System.out.println("Server Response with : " + serverRep);
            }
        }
        catch (SocketTimeoutException e){
            System.err.println("Time Out Error Happens...");
        }
        catch (Exception ignored) { }
    }
}
