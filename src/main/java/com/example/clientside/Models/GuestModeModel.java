package com.example.clientside.Models;

public class GuestModeModel extends PlayerModel {
    public GuestModeModel() {
        super();

    }

    public GuestModeModel(int port, String name) {
        super();
        this.serverPort = port;
        this.name = name;
        connectServer();
    }

    public void enterToGame() {
        System.out.println("serverPort- "+serverPort);
        connectServer();
    }
}


