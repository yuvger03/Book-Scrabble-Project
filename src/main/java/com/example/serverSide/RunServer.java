package com.example.serverSide;

public class RunServer {

    public static void main(String[] args) {
        MyServer server = new MyServer(8080, new BookScrabbleHandler(), 3);
        server.start();
        System.out.println("server started");
    }
}



