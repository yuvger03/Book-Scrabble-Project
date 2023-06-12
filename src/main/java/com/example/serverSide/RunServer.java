package com.example.serverSide;

import java.util.Scanner;

public class RunServer {

    public static void main(String[] args) {
        //System.out.println("SERVER SIDE");
        //HostModeModel h = new HostModeModel();
        MyServer server=new MyServer(8080,new BookScrabbleHandler(),3);
        server.start();
        System.out.println("server started");
        Scanner s=new Scanner(System.in);
        String input;
        do {
            input = s.next();
        } while(!input.equals("stop"));
            s.close();
            server.close();
            System.out.println("server stopped");
        }

    }



