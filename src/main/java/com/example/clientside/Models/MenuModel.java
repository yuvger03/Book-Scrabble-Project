package com.example.clientside.Models;

import javafx.beans.InvalidationListener;
//import javafx.beans.Observable;
import java.util.Observer;
import java.util.Observable;

public class MenuModel extends Observable {
    PlayerModel player;

    public MenuModel(){

    }
    public void startHostMode(){
        this.player = new HostModeModel(); //
        ((HostModeModel) player).initNewGame();
        System.out.println("A");

    }
    public void startGuestMode(){
        this.player = new GuestModeModel();
        player.connectServer(); //this func make the connection to host server

    }





}
//start new game-> what's your name-> score etc