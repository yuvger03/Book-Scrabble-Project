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
//    public int getScoreFromServer(){};
//
//    public List getLocationFromServer(){};
//    public boolean isWordValid(ArrayList<> word)


}
