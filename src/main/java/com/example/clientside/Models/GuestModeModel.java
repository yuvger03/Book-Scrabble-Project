package com.example.clientside.Models;

public class GuestModeModel extends PlayerModel{
    public void joinToGame(){
        outToServer.println("joinToGame"+","+name);
    }
}
