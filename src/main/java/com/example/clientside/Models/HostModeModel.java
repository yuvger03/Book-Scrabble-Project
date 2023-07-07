package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyHostServer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class HostModeModel extends PlayerModel {
    MyHostServer hostServer;
    int gameServer;
    public String hostPort;
    ArrayList<Socket> sockets;

    public GuestHandler guestHandler;

    public HostModeModel(int gameServer, GuestHandler guestHandler1,String name) {
        super();
        this.name = name;
        this.gameServer = gameServer;
        this.sockets = new ArrayList<Socket>();
        Random r = new Random();
        this.serverPort = 6000 + r.nextInt(1000);
        System.out.println("serverPort "+serverPort);
        this.guestHandler = guestHandler1;
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
    public void resumeGame(int port) throws JsonProcessingException { //TODO
        this.hostServer.resumeGame(port);
        //outToServer.println(this.name+ "-startGame-");
        //hostServer.notifyAll(this.name + "-startGame" + "-");
        //System.out.println("start game " + outToServer);
    }

    public void saveGame() {
        this.hostServer.saveGame();
        //close();
    }
    public void endGame() {
        this.hostServer.endGame();
        //close();
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

    public void showPort() {
        this.hostPort=String.valueOf(this.serverPort);
        setChanged();
        notifyObservers("showPort");
    }

    public void checkconnectedPlayers() {
        setChanged();
        notifyObservers("checkconnectedPlayers");
    }

}





