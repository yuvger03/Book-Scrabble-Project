package com.example.RunClasses;

import com.example.serverSide.BookScrabbleHandler;
import com.example.serverSide.MyServer;

public class RunServer {

    public static void main(String[] args) {
        MyServer server = new MyServer(8080, new BookScrabbleHandler(), 3);
        server.start();
        System.out.println("server started");
    }
}



