package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;

import java.util.Observable;

public class MenuModel extends Observable implements IModel{
    PlayerModel player;
    String name;
    int gamePort;
    public MenuModel(int gamePort){
        this.gamePort=gamePort;
    }
    public MenuModel(){

    }
    public HostModeModel startHostMode(String name){
        GuestHandler guestHandler = new GuestHandler(gamePort);
        this.player = new HostModeModel(gamePort, guestHandler,name); //enter game port
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