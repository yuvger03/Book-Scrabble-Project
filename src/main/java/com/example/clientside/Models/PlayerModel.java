package com.example.clientside.Models;

import com.example.clientside.Game.Tile;
import com.example.clientside.Game.Word;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class PlayerModel {
    String name = null;
    int score = 0;
    private BoardModel board_game = new BoardModel();
    ArrayList<TileModel> p_tiles;
     int serverPort;
    Scanner inFromServer;
    PrintWriter outToServer;

    //TODO: understand how gameServer connection works
    //Server gameServer; each player has a instance of its gameServer.

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<TileModel> getP_tiles() {
        return p_tiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setP_tiles(ArrayList<TileModel> p_tiles) {
        this.p_tiles = p_tiles;
    }

    //TODO:implement- connect the gameServer-to MyServer
    public void connectServer(){
            new Thread(
                    ()->{
                        try{
                            Socket server=new Socket("localhost", serverPort);
                            this.outToServer=new PrintWriter(server.getOutputStream());
                            this.inFromServer=new Scanner(server.getInputStream());
                            //StartGame();
                        }catch (Exception e){}
                    }).start();
        }

    public  int tryToPlace(String tryToPlace ,Word word){
        String s=word.toString();
        outToServer.println("tryToPlace"+","+s);
        outToServer.flush();
        return Integer.parseInt(inFromServer.next());
    }

    public Tile getTileFromBag(){
        outToServer.println("getTileFromBag,");
        outToServer.flush();
        //TODO- how send tile from server?
        return null;
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
