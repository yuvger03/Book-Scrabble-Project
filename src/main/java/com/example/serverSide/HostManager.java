package com.example.serverSide;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;

public class HostManager {

    public Tile.Bag b;
    public Board gameboard;
    int serverPort; //of GameServer
    public ArrayList<String> playersList;
    public String current_player;
    public int index; //for check the current turn;
    public Map<String,String> pTilesMap;
    public Map<String,String> scoreMap;

    Scanner inFromServer;
    PrintWriter outToServer;
    Socket serverSocket;
    Service s;
    private CountDownLatch connectionLatch; // added field

    public HostManager(int serverPort) {
        this.gameboard = new Board();
        this.b = new Tile.Bag();
        playersList = new ArrayList<>();
        pTilesMap = new HashMap<>();
        scoreMap = new HashMap<>();
        this.index = 1;
        this.serverPort = serverPort;
        connectionLatch = new CountDownLatch(1); // initialize the latch
        this.s = new Service();
        connectToGameServer();
    }

    private void connectToGameServer() {
        new Thread(
                () -> {
                    try {
                        this.serverSocket = new Socket("localhost", serverPort);
                        this.outToServer = new PrintWriter(serverSocket.getOutputStream());
                        this.inFromServer = new Scanner(serverSocket.getInputStream());
                        connectionLatch.countDown(); // signal that the connection is established
                    } catch (Exception e) {
                    }
                }).start();
    }

    public boolean dictionaryLegal(String word) {//TODO : how implement this func
        try {
            connectionLatch.await(); // wait for the connection to be established
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outToServer.println(word);
        outToServer.flush();
        String result = inFromServer.next();
        if (result.equals("true"))
            return true;
        else
            return false;
    }

    public int tryPlaceWord(Word w) {
        Tile[] ts = w.getTiles();
        int row = w.getRow();
        int col = w.getCol();
        for (int i = 0; i < ts.length; i++) {
            if (ts[i] == null)
                ts[i] = gameboard.tiles[row][col];
            if (w.isVertical())
                row++;
            else
                col++;
        }

        Word test = new Word(ts, w.getRow(), w.getCol(), w.isVertical());

        int sum = 0;
        if (gameboard.boardLegal(test)) {
            ArrayList<Word> newWords = gameboard.getWords(test);
            for (Word nw : newWords) {
                if (dictionaryLegal(nw.toString()))
                    sum += gameboard.getScore(nw);
                else
                    return 0;
            }
        }

        // the placement
        row = w.getRow();
        col = w.getCol();
        for (Tile t : w.getTiles()) {
            gameboard.tiles[row][col] = t;
            if (w.isVertical())
                row++;
            else
                col++;
        }

        if (gameboard.isEmpty) {
            gameboard.isEmpty = false;
            gameboard.bonus[7][7] = 0;
        }
        return sum;
    }

    public String getBoardGame() {
        return (s.matrixToString(gameboard.tiles));
    }

    public Tile getRand() {
        return b.getRand();
    }

    public String getPlayerTurn() { // TODO: implementation this func

        return playersList.get(index);
    }

    public boolean addPlayerToGame(String name) {
        if (playersList.size() < 4) {
            playersList.add(name);
            return true;
        } else
            return false;
    }

    public void nextPlayer() {
        if (index >=playersList.size()-1){
            index = 0;

        } else
            index++;
        current_player=playersList.get(index);
    }

    public ArrayList<Tile> initTileArray() {
        ArrayList<Tile> array = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            array.add(getRand());
        }
        return array;
    }

    public Board getGameboard() {
        return gameboard;
    }

    public void startGame() {
    }

    public String fillTilesArray(int count) {
        String s = "";
        for (int i = 0; i < count; i++) {
            String c = String.valueOf(getRand().letter);
            s += c;
        }
        return s;
    }

    public void setPlayerScore(int score,String name) {
        if (score == 0){
            this.scoreMap.put(name, "0");
        }
        else {
            int n_s = Integer.parseInt(scoreMap.get(name)) + score;
            this.scoreMap.put(name, String.valueOf(n_s));
        }
    }

    public void setPlayerpTiles(String tiles,String name) {
        String modifiedString ="";
        if(tiles != "" && tiles.contains("/")){
            String[] Tiles=tiles.split("/"); //abc/dad r - s ddaabbabc -> abbabc
            String charactersToDrop = Tiles[1];
            modifiedString = pTilesMap.get(name).replaceAll(charactersToDrop, "");
            modifiedString += Tiles[0];
        }
        else if(!tiles.contains("/"))
            modifiedString = tiles;
        this.pTilesMap.put(name,modifiedString);
    }
}
