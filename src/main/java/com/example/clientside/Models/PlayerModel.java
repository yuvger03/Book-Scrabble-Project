package com.example.clientside.Models;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;
import javafx.beans.InvalidationListener;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class PlayerModel extends Observable {
    String name ;
    int totalScore ;
    public String score;
    private Board board_game ;
    public ArrayList<Tile>p_tiles ;
    //int serverPort;
    Scanner inFromServer;
    PrintWriter outToServer;
    Service service;
    private CountDownLatch connectionLatch; // added field

    //TODO: understand how gameServer connection works
    //Server gameServer; each player has a instance of its gameServer.
public PlayerModel(){
    name=null;
    totalScore=0;
    board_game = new Board();
    p_tiles =new ArrayList<>();
    service=new Service();
    connectionLatch = new CountDownLatch(1); // initialize the latch
    //this.serverPort=serverPort;
}
    public String getName() {
        return name;
    }

    //public int getScore() {return score;
    //}

    public ArrayList<Tile> getP_tiles() {
        return p_tiles;
    }

    public void setName(String name) {
        this.name = name;
    }

  //  public void setScore(int score) {
     //   this.score = score;
    //}

    public void setP_tiles(ArrayList<Tile> p_tiles) {
        this.p_tiles = p_tiles;
    }

    public void connectServer(int serverPort){
            new Thread(
                    ()->{
                        try{
                            Socket server=new Socket("localhost", serverPort);
                            this.outToServer=new PrintWriter(server.getOutputStream());
                            this.inFromServer=new Scanner(server.getInputStream());
                            //StartGame();
                            connectionLatch.countDown(); // signal that the connection is established
                        }catch (Exception e){}
                    }).start();
        }

    public void tryToPlace(Word word){
        //String s=word.toString();
        String s=service.WordToString(word);
        boolean valid = service.validateWord(s, p_tiles);
        if(!valid)
            score="not valid word";
        try {
            connectionLatch.await(); // wait for the connection to be established
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outToServer.println("tryToPlace"+","+s);
        outToServer.flush();
        String s1= inFromServer.next();
        if(s1.equals("0")){
            score="not valid";
        }
        else{
            score=s1;
        }
        System.out.println(score);
        setChanged();
        notifyObservers();
    }

    public Tile getTileFromBag(){
        outToServer.println("getTileFromBag,");
        outToServer.flush();
        String s = inFromServer.next();
        return service.stringToTile(s);
    }

    public void close() {
        inFromServer.close();
        outToServer.close();
    }
//TODO:implement the gameServer connection funcs.
//    public abstract void connectServer();
//    public boolean isWordValid(ArrayList<> word)
//    public int isLocationValid(ArrayList<> Location) -list [x,y] or (int x, int y)
//    public int getScoreFromServer(){};- no need for now-getting score from location func.
    // the flow of connection:
    // pre game- all players get from gameServer 7 random tiles from bag
    // a player want to locate word-> sent word to gameServer
    //gameServer returns valid/not valid
    //if valid- player wants so locate word-> player send location
    //gameServer returns valid/not valid for location of the word.
    // if location is valid gameServer returns the score that the player gets after locating this word. if not valid-gameServer return -1.
    // gameServer completes the players tiles to 7 tiles.

}
