package com.example.clientside.viewmodel;

import com.example.clientside.Models.HostModeModel;

public class HostGameViewModel extends GameScreenViewModel {
    HostModeModel player;


    public HostGameViewModel(HostModeModel pm) {
        super(pm);
        this.player = pm;
    }

    public void saveGame(){
        player.saveGame();}
    public void endGame(){
        player.endGame();}
}

