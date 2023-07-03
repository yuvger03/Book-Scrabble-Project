package com.example.clientside.viewmodel;
import com.example.clientside.Models.HostModeModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Observer;
import java.util.Observable;

public class HostModeViewModel extends Observable implements Observer {
   HostModeModel hostModel;
   public HostModeViewModel(HostModeModel hm){
      this.hostModel=hm;
   }
   public void startGame(){
       hostModel.startGame();
   }
    public void resumeGame() throws JsonProcessingException {
        hostModel.resumeGame();
    }
   public HostModeModel getHostModel(){return hostModel;}
    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}