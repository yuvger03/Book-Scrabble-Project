package com.example.clientside.Models;

import java.io.*;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class HostModeModel extends PlayerModel {
    ArrayList<PlayerModel> game_players;
    BookScrabbleGameModel game;
    //private MyServer server; //TODO: where to locate myServer
    private int serverPort;
    private List<GuestHandler> guestHandlers;
    private Timer timeoutTimer;
//    private Map<String, GuestHandler> guestHandlers;


    public static class HostClientHandler implements IClientHandler {
        PrintWriter out;
        Scanner in;

        @Override
        public void handleClient(InputStream inFromclient, OutputStream outToClient) {
            out = new PrintWriter(outToClient);
            in = new Scanner(inFromclient);
            String text = in.next();
            out.println(new StringBuilder(text).reverse().toString());
            out.flush();
        }

        @Override
        public void close() {
            in.close();
            out.close();
        }
    }

    //TODO: Host constructor
    // 1. set player name(possible to implement it inside PlayrModel and then use super()
    // 2. init list of players instance and append host player to the list.
    public HostModeModel() {
        this.game_players = new ArrayList<PlayerModel>();
        this.guestHandlers = new ArrayList<GuestHandler>();
       // int port = 6000 + r.nextInt(1000);
       // this.serverPort = port;
        //this.server = new MyServer(port, new HostClientHandler(), 3); // TODO: here or in startHost?

    }

    //TODO: IMPORTANT - how players connect host?
    // 1. Listen func/option- to let new players to connect host -every new player will be insert into game_players list
    public void addPlayersToGame() {
    }

    //TODO: this func will initialize a new game:
    // 1. create game instance
    // 2.
    public void initNewGame() {
        this.game = new BookScrabbleGameModel(game_players);
        game.start();
    }


    //TODO:implement- connect the server-to MyServer
    @Override
    public void connectServer() {

    }


    public void startHost() {
        //server = new MyServer(serverPort, new ClientHandler(), 10); // Adjust the thread pool size as needed
        //server.start();
        startGuestListener();
//        startTimeoutTimer();
    }

    private void startGuestListener() {
//        new Thread(() -> {
//            try {
//                ServerSocket guestServerSocket = new ServerSocket(serverPort + 10); // another random port- host port
//                while (true) {
//                    Socket guestSocket = guestServerSocket.accept();
//                    GuestHandler guestHandler = new GuestHandler(guestSocket);
//                    guestHandlers.add(guestHandler);
//                    guestHandler.start();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    private void startTimeoutTimer() {
        timeoutTimer = new Timer();
        timeoutTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                stop();
            }
        }, 2 * 60 * 1000); // 2 minutes
    }

    public void stop() {
////        timeoutTimer.cancel();
//        server.close();
//        closeGuestHandlers();
    }

    private void closeGuestHandlers() {
//        for (GuestHandler guestHandler : guestHandlers) {
//            guestHandler.close();
//        }
//        guestHandlers.clear();
    }

    private class GuestHandler extends Thread {
        private Socket guestSocket;
        private ObjectInputStream input;
        private ObjectOutputStream output;


    }
}