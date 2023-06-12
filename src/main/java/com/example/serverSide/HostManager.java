package com.example.serverSide;

import com.example.clientside.Game.Board;
import com.example.clientside.Game.Tile;
import com.example.clientside.Game.Word;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class HostManager {
    Tile.Bag b;
    Board gameboard;
    int serverPort ; //of GameServer
    ArrayList<String> playersList;
   public int i; //for check the current turn;
    Scanner inFromServer;
    PrintWriter outToServer;
    Socket serverSocket;
    public HostManager(int serverPort){
        this.gameboard= new Board();
        this.b=new Tile.Bag();
        playersList=new ArrayList<>();
        this.i=0;
        this.serverPort=serverPort;
       connectToGameServer();
    }

    private void connectToGameServer() {
        new Thread(
                () -> {
                    try {
                       this.serverSocket = new Socket("localhost", serverPort);
                        this.outToServer = new PrintWriter(serverSocket.getOutputStream());
                        this.inFromServer = new Scanner(serverSocket.getInputStream());
                    } catch (Exception e) {
                    }
                }).start();
    }
    public boolean dictionaryLegal(String word){//TODO : how implement this func
        outToServer.println(word);
        outToServer.flush();
        String result=inFromServer.next();
        if(result.equals("true"))
            return  true;
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

    public Tile[][] getTiles(){ //return the updateBoard; //TODO :implemintion this func .
    return null;
}

    public Tile getRand(){
        return b.getRand();
    }

    public String sendPlayerTurn(){ // TODO: implementation this func
        return playersList.get(i);
    }

public void addPlayerToGame(String name){
        playersList.add(name);
    }
}
