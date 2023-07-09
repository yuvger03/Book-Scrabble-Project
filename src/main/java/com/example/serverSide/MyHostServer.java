package com.example.serverSide;

import com.example.Game.Tile;
import com.example.clientside.Models.Service;
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
        public GuestHandler guestHandler1;
        private int maxThreads;
        public ServerSocket serverSocket;
        public ExecutorService executorService;

        protected volatile boolean stop;
        public List<Socket> clientSockets;
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
        public void startServer() {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started on port " + port);
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
        public void handleClient(Socket clientSocket) {
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
    public synchronized void close() {
        stop = true;
        try {
            executorService.shutdown();
            for (Socket socket : clientSockets) {
                socket.close();
            }
            clientSockets.clear();
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

        guestHandler1.HM.current_player =  guestHandler1.HM.playersList.get( guestHandler1.HM.index);
        for (int i = 0; i <  guestHandler1.HM.playersList.size(); i++) {
            ArrayList<Tile> tiles =  guestHandler1.HM.initTileArray();
            String tielsString = "";
            for (Tile tile : tiles)
                tielsString += s.TileToString(tile);
            guestHandler1.HM.setPlayerTiles(tielsString,guestHandler1.HM.playersList.get(i));
            notifyAll(guestHandler1.HM.playersList.get(i) + "-initTiles-" + tielsString + "-null");
        }
        notifyAll("board-" +  guestHandler1.HM.getBoardGame());
        notifyAll("message- GAME STARTED BY HOST- ");
        notifyAll("turn-"+"TURN OF: "+ guestHandler1.HM.current_player);
    }
    public void resumeGame(int port) throws JsonProcessingException {
        Service s = new Service();
        DBcom dBcom = new DBcom();
        System.out.println("game resumed\n");
        guestHandler1.HM.current_player =  dBcom.readFromDB(port).getString("current_player");
        guestHandler1.HM.index = guestHandler1.HM.playersList.indexOf(guestHandler1.HM.current_player);

        guestHandler1.HM.pTilesMap = dBcom.getMapFromJSON(dBcom.readFromDB(port),"pTilesMap");
        guestHandler1.HM.scoreMap = dBcom.getMapFromJSON(dBcom.readFromDB(port),"scoreMap");
        for (int i=0;i<guestHandler1.HM.gameboard.tiles.length;i++)
            for (int j=0;j<guestHandler1.HM.gameboard.tiles.length;j++){
                char a = dBcom.getBoardFromDocument(dBcom.readFromDB(port)).charAt(i*15+j);
                if(a!='n')
                    guestHandler1.HM.gameboard.tiles[i][j] = new Tile(a,s.calculateScore(a));
            }
        guestHandler1.HM.gameboard.isEmpty = false;
        guestHandler1.HM.b.quantities = dBcom.getBagFromDocument(dBcom.readFromDB(port));
        for (String player :  guestHandler1.HM.playersList) {
            String tielsString = guestHandler1.HM.pTilesMap.get(player);
            notifyAll(player + "-initTiles-" + tielsString + "-null");
            notifyAll(player + "-totalScore-" + guestHandler1.HM.scoreMap.get(player) + "-null");
        }
        notifyAll("board-" +  guestHandler1.HM.getBoardGame());
        notifyAll("message- GAME RESUME BY HOST- ");
        notifyAll("turn-"+"TURN OF: "+ guestHandler1.HM.current_player);
        guestHandler1.HM.hostPort = port;
    }
    public void saveGame()  {
        DBcom dBcom = new DBcom();
        dBcom.saveToDB(guestHandler1.HM);


    }
    public void endGame(){
        notifyAll("message-GAME OVER BY THE HOST - BYE BYE");
        //stop=true;
        try {
            Thread.sleep(1000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll("closeGame-");
        System.exit(0);
    }
    public void gameOver(){
        try {
            Thread.sleep(1000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll("closeGame-");
        System.exit(0);
    }
}




