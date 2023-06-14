package com.example.clientside.Models;

public class GuestModeModel extends PlayerModel{
    int serverPort;
    public GuestModeModel(int serverPort) {
        super();
        this.serverPort=serverPort;
        connectServer(serverPort);

    }

    public void joinToGame(){
        outToServer.println("joinToGame"+","+name);
    }
}
