package com.example.clientside.Models;

public class MenuModel {
    PlayerModel player;

    public void startHostMode(){
        this.player = new HostModeModel(); //
        ((HostModeModel) player).initNewGame();
    }
    public void startGuestMode(){
        this.player = new GuestModeModel();
        player.connectServer(); //this func make the connection to host server
    }


}
//start new game-> what's your name-> score etc