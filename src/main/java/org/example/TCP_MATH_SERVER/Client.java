package org.example.TCP_MATH_SERVER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader scn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while(true){
                System.out.println(in.readLine());
                System.out.print("Enter Option : " );
                String option = scn.readLine();
                out.println(option);
                if(option.equals("4")){
                    System.out.println(in.readLine());
                    return;
                }
                System.out.println("Enter first operand : ");
                String operand1 = scn.readLine();
                out.println(operand1);
                System.out.println("Enter second operand : ");
                String operand2 = scn.readLine();
                out.println(operand2);
                System.out.println("Result : " + in.readLine());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
