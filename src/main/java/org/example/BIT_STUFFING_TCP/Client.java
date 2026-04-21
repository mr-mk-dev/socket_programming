package org.example.BIT_STUFFING_TCP;

import  java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost",5000);
            System.out.println("Client Connected...");
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter Data : ");
            String data = sc.readLine();
            StringBuilder dataStuff = getStuffData(data);
            out.println(dataStuff);
        }catch(Exception e){

        }
    }

    private static StringBuilder getStuffData(String data) {
        String stuff = "01111110";
        StringBuilder dataStuff = new StringBuilder();
        dataStuff.append(stuff);
        int counter = 0;
        for(int i = 0; i < data.length(); i++){
            char c = data.charAt(i);
            if(counter == 5){
                dataStuff.append('0');
                dataStuff.append(c);
                counter=0;
            }else if(c == '1'){
                counter+=1;
                dataStuff.append(c);
            }else {
                dataStuff.append(c);
                counter = 0;
            }
        }
        return dataStuff.append(stuff);
    }
}
