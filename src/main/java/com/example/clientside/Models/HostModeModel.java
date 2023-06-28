package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.IClientHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HostModeModel extends PlayerModel {
    MyHostServer hostServer;
    int gameServer;
    ArrayList<Socket> sockets;
    GuestHandler guestHandler;
    PrintWriter outToServer;

    public HostModeModel(int gameServer) {
        super();
        this.gameServer = gameServer;}
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

    public HostModeModel(int gameServer, GuestHandler guestHandler1, String name) {
        super();
        this.name = name;
        this.gameServer = gameServer;
        this.sockets = new ArrayList<Socket>();
//        Random r = new Random();
//        this.serverPort = 6000 + r.nextInt(1000);
        this.serverPort = 8081;
        this.guestHandler = guestHandler1;
        this.guestHandler.HM.playersList.add("host");
        this.guestHandler.host = this;
        System.out.println("im here");
    }

    public void startServer() {
        //    HostModeModel h =new HostModeModel(gameServer,guestHandler1,name);
        MyHostServer hostServer = new MyHostServer(this.serverPort, this.guestHandler, 4);
        hostServer.start();
        System.out.println("server started");
    }

    public void startGame() {
        notifyAll(this.name + "-startGame" + "-");
        System.out.println("start game " + outToServer);
    }

    public void notifyAll(String update){
        for (Socket s : sockets) {
            try {
                outToServer = new PrintWriter(s.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            outToServer.println(update);
            outToServer.flush();
        }
    }

    public void close() {
        inFromServer.close();
        outToServer.close();
        hostServer.close();
    }


    public class MyHostServer {
        public int port;
        IClientHandler clientHandler;
        private int maxThreads;
        protected ServerSocket serverSocket;
        private ExecutorService executorService;
        protected volatile boolean stop;

        public MyHostServer(int port, IClientHandler clientHandler, int maxThreads) {
            this.port = port;
            this.clientHandler = clientHandler;
            this.maxThreads = maxThreads;
            this.executorService = Executors.newFixedThreadPool(maxThreads);
        }

        public void start() {
            stop = false;
            executorService.execute(() -> startServer());
        }

        protected void startServer() {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started on port " + port);
                serverSocket.setSoTimeout(1000);
                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        if(clientSocket!=null)
                            sockets.add(clientSocket);
                        try {
                            clientHandler.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                                clientHandler.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SocketTimeoutException e) {
                    }
                }
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            stop = true;
            try {
                executorService.shutdown();
                if (serverSocket.isClosed())
                    serverSocket.close();
                for (Socket s : sockets) {
                    if (!s.isClosed()) {
                        try {
                            s.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




