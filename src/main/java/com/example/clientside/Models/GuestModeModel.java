package com.example.clientside.Models;

public class GuestModeModel extends PlayerModel {
    public GuestModeModel() {
        super();

    }

    public void enterToGame() {
        this.serverPort=8081;//for test
        connectServer();
    }
}


