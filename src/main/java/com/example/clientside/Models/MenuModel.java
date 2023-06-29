package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import javafx.beans.InvalidationListener;
//import javafx.beans.Observable;
import java.util.Observer;
import java.util.Observable;

public class MenuModel extends Observable {
    PlayerModel player;
    String name;
    public MenuModel(){

    }
    public HostModeModel startHostMode(String name){
        GuestHandler guestHandler = new GuestHandler(8080);
        this.player = new HostModeModel(8080, guestHandler,name); //enter game port
        return (HostModeModel)player;
        //((HostModeModel) player).initNewGame();
       // notifyObservers();
    }
    public GuestModeModel startGuestMode(){
        this.player = new GuestModeModel();//TODO change
        return  (GuestModeModel)player;
        //player.connectServer(); //this func make the connection to host gameServer
       // notifyObservers();
    }
    public String getName(){
        return name;

    }
    public void setName(String name){
        this.player.name=name;
    }




}
//start new game-> what's your name-> score etc