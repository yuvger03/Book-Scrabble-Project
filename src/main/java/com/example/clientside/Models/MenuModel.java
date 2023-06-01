package com.example.clientside.Models;

public class MenuModel {
    PlayerModel player;

    public void startHostMode(){
        this.player = new HostModeModel(); //
        ((HostModeModel) player).initNewGame();
    }
    public void startGuestMode(){}


}
//start new game-> what's your name-> score etc