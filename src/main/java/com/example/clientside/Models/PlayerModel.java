package com.example.clientside.Models;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerModel {
    String name = null;
    int score = 0;
    ArrayList<TileModel> p_tiles;
    //TODO: understand how server connection works
    //Server server; each player has a instance of its server.

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

    public abstract void connectServer();


//TODO:implement the server connection funcs.
//    public abstract void connectServer();
//    public boolean isWordValid(ArrayList<> word)
//    public int isLocationValid(ArrayList<> Location) -list [x,y] or (int x, int y)
//    public int getScoreFromServer(){};- no need for now-getting score from location func.
    // the flow of connection:
    // pre game- all players get from server 7 random tiles from bag
    // a player want to locate word-> sent word to server
    //server returns valid/not valid
    //if valid- player wants so locate word-> player send location
    //server returns valid/not valid for location of the word.
    // if location is valid server returns the score that the player gets after locating this word. if not valid-server return -1.
    // server completes the players tiles to 7 tiles.

}
