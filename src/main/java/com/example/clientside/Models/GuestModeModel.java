package com.example.clientside.Models;

public class GuestModeModel extends PlayerModel{
    public GuestModeModel(){
        super();

    }
    public void joinToGame(){
        connectServer();
        outToServer.println(this.name + "-" + "joinToGame" + "-");
        String s = inFromServer.next();
        }
    }


