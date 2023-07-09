package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;

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
    }
    public GuestModeModel startGuestMode(){
        this.player = new GuestModeModel();//TODO change
        return  (GuestModeModel)player;
    }
    public String getName(){
        return name;

    }
    public void setName(String name){
        this.player.name=name;
    }




}
//start new game-> what's your name-> score etc