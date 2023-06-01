package com.example.clientside.Models;

import java.util.ArrayList;

public class HostModeModel extends PlayerModel {
    ArrayList<PlayerModel> game_players;
    BookScrabbleGameModel game;

    //TODO: Host constructor
    // 1. set player name(possible to implement it inside PlayrModel and then use super()
    // 2. init list of players instance and append host player to the list.
    public HostModeModel() {
        this.game_players = new ArrayList<PlayerModel>();

    }

    //TODO: IMPORTANT - how players connect host?
    // 1. Listen func/option- to let new players to connect host -every new player will be insert into game_players list
    public void addPlayersToGame(){
    }

    //TODO: this func will initialize a new game:
    // 1. create game instance
    // 2.
    public void initNewGame() {
        this.game = new BookScrabbleGameModel(game_players);
        game.start();
    }


    //TODO:implement- connect the server-to MyServer
    @Override
    public void connectServer() {

    }
}
