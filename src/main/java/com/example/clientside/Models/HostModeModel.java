package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyServer;

import java.util.*;

public class HostModeModel extends PlayerModel {
    MyServer hostServer;

    public HostModeModel(int gameServer) {
        Random r = new Random();
        this.serverPort = 6000 + r.nextInt(1000);
        hostServer = new MyServer(serverPort, new GuestHandler(gameServer), 4);
        hostServer.start();
        //System.out.println("server started");
        Scanner s = new Scanner(System.in);
        String input;
        do {
            input = s.next();
        } while (!input.equals("stop"));
        s.close();
        hostServer.close();
        System.out.println("server stopped");
    }

    public void close() {
        inFromServer.close();
        outToServer.close();
        hostServer.close();
    }
}



