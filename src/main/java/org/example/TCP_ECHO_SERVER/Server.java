package org.example.TCP_ECHO_SERVER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


   public static  void main(String[] args){
       try(ServerSocket ss = new ServerSocket(5000)
       ){
           System.out.println("Server Started at : "+ss.getLocalPort());
           Socket socket = ss.accept();
           System.out.println("Client Connected...");
           BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

           String str = in.readLine();
           System.out.println("Message Received From Client : " + str);
           out.println(str.toUpperCase());


       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }
}
