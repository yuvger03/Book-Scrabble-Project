package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHostServer {
    PrintWriter outToServer;
    public int port;
        GuestHandler guestHandler1;
        private int maxThreads;
        protected ServerSocket serverSocket;
        private ExecutorService executorService;
        protected volatile boolean stop;
        private List<Socket> clientSockets;
        public MyHostServer(int port, GuestHandler guestHandler1, int maxThreads) {
            this.port = port;
            this.guestHandler1 = guestHandler1;
            this.guestHandler1.host=this;
            this.maxThreads = maxThreads;
            this.executorService = Executors.newFixedThreadPool(maxThreads);
            this.clientSockets = new ArrayList<>();
        }

        public void start() {
            stop = false;
            executorService.execute(this::startServer);
        }
        protected void startServer() {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started on port " + port);
                //serverSocket.setSoTimeout(1000);
                while (!stop) {
                    Socket clientSocket = serverSocket.accept();
                    clientSockets.add(clientSocket);

                    executorService.execute(() -> handleClient(clientSocket));

                    // Remove disconnected clients from the list
                    clientSockets.removeIf(socket -> socket.isClosed());
                }

                // Close all remaining client sockets
                for (Socket socket : clientSockets) {
                    socket.close();
                }

                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void handleClient(Socket clientSocket) {
            try {
                guestHandler1.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void notifyAll(String message) {
            for (Socket s : clientSockets) {
                try {
                    outToServer = new PrintWriter(s.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                outToServer.println(message);
                outToServer.flush();
            }
        }
        public void close() {
            stop = true;
            try {
                executorService.shutdown();
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void startGame() {

        Service s=new Service();
        System.out.println("gameStarted\n"); //TODO shira
        guestHandler1.HM.current_player =  guestHandler1.HM.playersList.get( guestHandler1.HM.index);
        for (int i = 0; i <  guestHandler1.HM.playersList.size(); i++) {
            ArrayList<Tile> tiles =  guestHandler1.HM.initTileArray();
            String tielsString = "";
            for (int j = 0; j < tiles.size(); j++) {
                //tielsString += service.TileToString(tiles.get(j)) + "/";
                tielsString += s.TileToString(tiles.get(j));
            }
            notifyAll(guestHandler1.HM.playersList.get(i) + "-initTiles-" + tielsString + "-null");
        }
        notifyAll("board-" +  guestHandler1.HM.getBoardGame());
    }
    //TODO: implement resumeGame that reads from DB all the fields of the game
}




