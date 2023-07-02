package com.example.serverSide;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Service;
import org.bson.Document;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public void resumeGame(){
//        Service s=new Service();
        DBcom dBcom = new DBcom();
        System.out.println("game resumed\n");
        guestHandler1.HM.current_player =  dBcom.readFromDB(guestHandler1.HM.serverPort).getString("current_player");
        //TODO: check that the same players connected to the DB
        guestHandler1.HM.pTilesMap = dBcom.readFromDB(guestHandler1.HM.serverPort).get("pTilesMap", guestHandler1.HM.pTilesMap);
        guestHandler1.HM.scoreMap = dBcom.readFromDB(guestHandler1.HM.serverPort).get("scoreMap", guestHandler1.HM.scoreMap);
        guestHandler1.HM.gameboard = dBcom.readFromDB(guestHandler1.HM.serverPort).get("gameboard",guestHandler1.HM.gameboard);
        guestHandler1.HM.b = dBcom.readFromDB(guestHandler1.HM.serverPort).get("bag",guestHandler1.HM.b);
        for (String player :  guestHandler1.HM.playersList) {
            String tielsString = guestHandler1.HM.pTilesMap.get(player);
            notifyAll(player + "-initTiles-" + tielsString + "-null");
            notifyAll(player + "-initScore-" + guestHandler1.HM.scoreMap.get(player) + "-null");
        }
        notifyAll("board-" +  guestHandler1.HM.getBoardGame());
    }
    public void saveGame(HostManager hm){
        DBcom dBcom = new DBcom();
        dBcom.saveToDB(hm);
            //TODO: save HM to DB
    }
}




