package com.example.clientside.viewmodel;
import com.example.clientside.Models.HostModeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import java.util.Observer;
import java.util.Observable;

public class HostModeViewModel extends Observable implements Observer {
   HostModeModel hostModel;
    public StringProperty hostPort;
    public ListProperty<String> playersList;
   public HostModeViewModel(HostModeModel hm){
       this.hostModel=hm;
       this.hostModel.addObserver(this);
       this.hostPort=new SimpleStringProperty();
       playersList = new SimpleListProperty<>(FXCollections.observableArrayList());
   }
   public void startGame(){
       hostModel.startGame();
   }
    public void resumeGame() throws JsonProcessingException {
        hostModel.resumeGame();
    }
   public HostModeModel getHostModel(){return hostModel;}


    public void showPort() {
       hostModel.showPort();
    }

    public void checkconnectedPlayers() {
        hostModel.checkconnectedPlayers();
    }
    @Override
    public void update(java.util.Observable o, Object arg) {
        //if (o == hostModel) {
        if (o instanceof HostModeModel) {
            Platform.runLater(() -> {
                if(arg.equals("showPort"))
                    hostPort.set("LISTENING ON PORT : "+hostModel.hostPort);
                if(arg.equals("checkconnectedPlayers"))
                    playersList.setAll(hostModel.guestHandler.HM.playersList);

            });
        }
        System.out.println("serverPort in vm");
        //setChanged();
        //notifyObservers();
        // }
    }
}