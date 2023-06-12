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
        this.player = new HostModeModel(8080); //enter game port
        //((HostModeModel) player).initNewGame();
        notifyObservers();

    }
    public void startGuestMode(){
        this.player = new GuestModeModel();
        //player.connectServer(); //this func make the connection to host gameServer
        notifyObservers();
    }
    public String getName(){
        return name;

    }
    public void setName(String name){
        this.name=name;
    }




}
//start new game-> what's your name-> score etc