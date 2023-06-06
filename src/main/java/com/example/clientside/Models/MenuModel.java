package com.example.clientside.Models;

import javafx.beans.InvalidationListener;
//import javafx.beans.Observable;
import java.util.Observer;
import java.util.Observable;

public class MenuModel extends Observable {
    PlayerModel player;
    String name;
    public MenuModel(){

    }
    public void startHostMode(){
        this.player = new HostModeModel(); //
        ((HostModeModel) player).initNewGame();
        notifyObservers();

    }
    public void startGuestMode(){
        this.player = new GuestModeModel();
        player.connectServer(); //this func make the connection to host server
        notifyObservers();

    }
    public String getName(String name){
        return name;
    }




}
//start new game-> what's your name-> score etc