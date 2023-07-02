package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyHostServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HostModeModel extends PlayerModel {
    MyHostServer hostServer;
    int gameServer;
    ArrayList<Socket> sockets;
    GuestHandler guestHandler;

  //  public HostModeModel(int gameServer) {
    //    super();
   ///     this.gameServer = gameServer;
  //  }
//    public HostModeModel(int gameServer) {
//        super();
//        this.gameServer = gameServer;
//        Random r = new Random();
//        //this.serverPort = 6000 + r.nextInt(1000);
//        this.serverPort = 8081;
//        this.sockets = new ArrayList<Socket>();
//
//        Thread serverThread = new Thread(() -> {
//            MyServer hostServer = new MyServer(serverPort, new GuestHandler(gameServer), 4);
//            hostServer.start();
//            // System.out.println("server started");
//            Scanner s = new Scanner(System.in);
//            String input;
//            do {
//                input = s.next();
//            } while (!input.equals("stop"));
//            s.close();
//            hostServer.close();
//            System.out.println("server stopped");
//        });
//        serverThread.start();
//        connectServer(); //TODO return back
//    }

    public HostModeModel(int gameServer, GuestHandler guestHandler1,String name) {
        super();
        this.name = name;
        this.gameServer = gameServer;
        this.sockets = new ArrayList<Socket>();
//        Random r = new Random();
//        this.serverPort = 6000 + r.nextInt(1000);
        this.serverPort = 8081;
        this.guestHandler = guestHandler1;
        //this.guestHandler.HM.playersList.add("host");
        //this.guestHandler.host = this;
        System.out.println("im here");
        startServer();
        connectServer();
    }

    public void startServer() {
        //    HostModeModel h =new HostModeModel(gameServer,guestHandler1,name);
        this.hostServer = new MyHostServer(this.serverPort, this.guestHandler, 4);
        this.hostServer.start();
        System.out.println("server started");
    }

    public void startGame() {
        this.hostServer.startGame();
        //outToServer.println(this.name+ "-startGame-");
        //hostServer.notifyAll(this.name + "-startGame" + "-");
        //System.out.println("start game " + outToServer);
    }
    public void resumeGame() { //TODO
//        this.hostServer.resumeGame();
        //outToServer.println(this.name+ "-startGame-");
        //hostServer.notifyAll(this.name + "-startGame" + "-");
        //System.out.println("start game " + outToServer);
    }
//    public void notifyAll(String update) {
//        for (Socket s : sockets) {
//            try {
//                outToServer = new PrintWriter(s.getOutputStream());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            outToServer.println(update);
//            //outToServer.flush();
//        }
//    }

    public void close() {
        inFromServer.close();
        outToServer.close();
        hostServer.close();
    }
}





