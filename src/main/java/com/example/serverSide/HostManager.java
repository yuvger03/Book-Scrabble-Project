package com.example.serverSide;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.clientside.Models.Service;

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
    public int hostPort;
    public ArrayList<String> playersList;
    public String current_player;
    public int index; //for check the current turn;
    public Map<String,String> pTilesMap;
    public Map<String,String> scoreMap;

    Service s;
    private CountDownLatch connectionLatch; // added field

    public HostManager(int serverPort) {
        this.gameboard = new Board();
        this.b = new Tile.Bag();
        playersList = new ArrayList<>();
        pTilesMap = new HashMap<>();
        scoreMap = new HashMap<>();
        this.index = 0;
        this.serverPort = serverPort;
        connectionLatch = new CountDownLatch(1); // initialize the latch
        this.s = new Service();
    }


    public boolean dictionaryLegal(String word) {
        String result="";
        try {
            Socket serverSocket = new Socket("localhost", serverPort);
            PrintWriter outToServer = new PrintWriter(serverSocket.getOutputStream());
            Scanner inFromServer = new Scanner(serverSocket.getInputStream());
            outToServer.println("C,Harray Potter.txt,alice_in_wonderland.txt,test.txt," + s.getWordString(word));
            outToServer.flush();
            result = inFromServer.next();
            inFromServer.close();
            outToServer.close();
            serverSocket.close();

        } catch (Exception e) {}
        if(result.equals("true"))
            return true;
        else
            return false;
    }

    public boolean dictionaryLegalView(String word){
        return true;
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
                if (dictionaryLegal(s.WordToString(nw))) {
                    sum += gameboard.getScore(nw);
                }
                else {
                    return 0;
                }
            }
        }
        else {
             return 0;
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

    public String getPlayerTurn() {

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


    public String fillTilesArray(String word) {
        String s = "";
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i)=='_'){
                continue;
            }
            String c = String.valueOf(getRand().letter);
            s += c;
        }
        return s;
    }

    public void setPlayerScore(int score,String name) {
        if (this.scoreMap.get(name)==null){
            this.scoreMap.put(name, "0");
        }
        else {
            int n_s = Integer.parseInt(scoreMap.get(name)) + score;
            this.scoreMap.put(name, String.valueOf(n_s));
        }
    }

    public void setPlayerTiles(String tiles,String name) {
        String modifiedString ="";
        if(tiles.contains("/")){
            String start = pTilesMap.get(name);
            String[] Tiles = tiles.split("/");
            Tile[] word = s.StringToTilesArray(Tiles[1]);
            for (Tile tile : word) {
                int j = 0;
                while (start.charAt(j) != (tile.letter)) {
                    j++;
                }
                start = start.substring(0, j) + start.substring(j + 1);
            }
            modifiedString = start + Tiles[0];
        }
        else {
            modifiedString = tiles;
        }
        this.pTilesMap.put(name,modifiedString);
    }
}
