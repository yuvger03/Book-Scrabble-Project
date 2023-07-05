package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHostServer {
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
            PrintWriter outToServer;
            for (Socket s : clientSockets) {
                try {
                     outToServer = new PrintWriter(s.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                outToServer.println(message);
                System.out.println("notify all "+message);
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
        guestHandler1.HM.hostPort = port;
        System.out.println("gameStarted\n"); //TODO shira
        System.out.println("HM.index "+guestHandler1.HM.index); //TODO shira
        guestHandler1.HM.current_player =  guestHandler1.HM.playersList.get( guestHandler1.HM.index);
        System.out.println("HM.current_player "+ guestHandler1.HM.playersList.get(guestHandler1.HM.index));
        System.out.println("guestHandler1.HM.playersList.size()- "+guestHandler1.HM.playersList.size());
        for (int i = 0; i <  guestHandler1.HM.playersList.size(); i++) {
            ArrayList<Tile> tiles =  guestHandler1.HM.initTileArray();
            String tielsString = "";
            for (int j = 0; j < tiles.size(); j++) {
                //tielsString += service.TileToString(tiles.get(j)) + "/";
                tielsString += s.TileToString(tiles.get(j));
            }
            guestHandler1.HM.setPlayerTiles(tielsString,guestHandler1.HM.playersList.get(i));
            notifyAll(guestHandler1.HM.playersList.get(i) + "-initTiles-" + tielsString + "-null");
        }
        notifyAll("board-" +  guestHandler1.HM.getBoardGame());
        notifyAll("message- GAME STARTED BY HOST- ");
        notifyAll("turn-"+"TURN OF: "+ guestHandler1.HM.current_player);
    }
    public void resumeGame(int port) throws JsonProcessingException {
        System.out.println(port);
        Service s = new Service();
        DBcom dBcom = new DBcom();
        System.out.println("game resumed\n");
        guestHandler1.HM.current_player =  dBcom.readFromDB(port).getString("current_player");
        //TODO: check that the same players connected to the DB
        guestHandler1.HM.pTilesMap = dBcom.getMapFromJSON(dBcom.readFromDB(port),"pTilesMap");
        guestHandler1.HM.scoreMap = dBcom.getMapFromJSON(dBcom.readFromDB(port),"scoreMap");
        for (int i=0;i<guestHandler1.HM.gameboard.tiles.length;i++)
            for (int j=0;j<guestHandler1.HM.gameboard.tiles.length;j++){
                Character a = dBcom.getBoardFromDocument(dBcom.readFromDB(port)).charAt(i*15+j);
                guestHandler1.HM.gameboard.tiles[i][j] = new Tile(a,s.calculateScore(a));
                System.out.println(guestHandler1.HM.gameboard.tiles[i][j].letter);
            }
//        for (Tile[]a:guestHandler1.HM.gameboard.tiles)
//            for (Tile b:a)
//                if (b !=null)
//                    System.out.print(b.letter);
        guestHandler1.HM.b.quantities = dBcom.getBagFromDocument(dBcom.readFromDB(port));
        for (String player :  guestHandler1.HM.playersList) {
            String tielsString = guestHandler1.HM.pTilesMap.get(player);
            notifyAll(player + "-initTiles-" + tielsString + "-null");
            notifyAll(player + "-initScore-" + guestHandler1.HM.scoreMap.get(player) + "-null");
        }
        notifyAll("board-" +  guestHandler1.HM.getBoardGame());
    }
    public void saveGame(){
        DBcom dBcom = new DBcom();
        dBcom.saveToDB(guestHandler1.HM);
    }
}




