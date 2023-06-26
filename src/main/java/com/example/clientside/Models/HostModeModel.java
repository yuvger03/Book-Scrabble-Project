package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyServer;

import java.util.Random;
import java.util.Scanner;

public class HostModeModel extends PlayerModel {
    MyServer hostServer;
    int gameServer;
    //int serverPort;

    public HostModeModel(int gameServer) {
        super();
        this.gameServer=gameServer;
        Random r = new Random();
        //this.serverPort = 6000 + r.nextInt(1000);
        this.serverPort=8081;
        Thread serverThread = new Thread(() -> {
            MyServer hostServer = new MyServer(serverPort, new GuestHandler(gameServer), 4);
            hostServer.start();
            // System.out.println("server started");
            Scanner s = new Scanner(System.in);
            String input;
            do {
                input = s.next();
            } while (!input.equals("stop"));
            s.close();
            hostServer.close();
            System.out.println("server stopped");
        });
        serverThread.start();
        connectServer(); //TODO return back
    }

    public HostModeModel(int gameServer, GuestHandler guestHandler, String name) {
        super();
        this.name = name;
        this.gameServer=gameServer;
        Random r = new Random();
        this.serverPort = 6000 + r.nextInt(1000);
        Thread serverThread = new Thread(() -> {
            MyServer hostServer = new MyServer(serverPort, guestHandler, 4);
            hostServer.start();
            // System.out.println("server started");
            Scanner s = new Scanner(System.in);
            String input;
            do {
                input = s.next();
            } while (!input.equals("stop"));
            s.close();
            hostServer.close();
            System.out.println("server stopped");
        });
        serverThread.start();
        connectServer(); //TODO return back
    }


public void startGame(){
    outToServer.println("startGame"+"-");
    outToServer.flush();
}
    //public void joinToGame() {
        //outToServer.println(this.name + "-" + "joinToGame" + "-");

        //String s = inFromServer.next();
   // }
    public void close() {
        inFromServer.close();
        outToServer.close();
        hostServer.close();
    }


}



