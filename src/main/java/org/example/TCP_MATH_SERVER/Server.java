package org.example.TCP_MATH_SERVER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try{
           ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server Started at : "+ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Client Connected...");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            while(true){
                String menu = "Select 1 to add , 2 to subtract , 3 to multiply , 4 to exit";
                out.println(menu);
                String option = in.readLine();
                int optionInt = Integer.parseInt(option);
                if(option.equals("4")){
                    System.out.println("User End The Program by option : " + option);
                    out.println("Exit");
                    return;
                }else{
                    int firstOpr = Integer.parseInt(in.readLine());
                    int secOpr = Integer.parseInt(in.readLine());
                    if(optionInt == 1) out.println(firstOpr + secOpr);
                    else if(optionInt == 2) out.println(firstOpr - secOpr);
                    else if(optionInt == 3) out.println(firstOpr * secOpr);
                    else out.println("Invalid Option");
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
