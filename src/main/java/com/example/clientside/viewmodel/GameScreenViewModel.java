package com.example.clientside.viewmodel;

import com.example.Service;
import com.example.clientside.Models.PlayerModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class GameScreenViewModel extends Observable implements Observer {
    PlayerModel player;
    public StringProperty scoreResult;
    public StringProperty totalScore;
    public StringProperty message;
    public StringProperty currentPlayer;
    public StringProperty word;
    public String gameBoard;
    public String tilesArray;
    public StringProperty row;
    public StringProperty col;
    public BooleanProperty vertical;

    Service s;

    public GameScreenViewModel(PlayerModel pm) {
        this.player = pm;
        this.player.addObserver(this);
        word = new SimpleStringProperty();
        row = new SimpleStringProperty();
        col = new SimpleStringProperty();
        vertical = new SimpleBooleanProperty();
        scoreResult = new SimpleStringProperty();
        totalScore = new SimpleStringProperty();
        message=new SimpleStringProperty();
        currentPlayer=new SimpleStringProperty();
        gameBoard = "";
        s = new Service();
    }

    public void SendWord() {//make obj WORD to string- "word,row,col,vertical"
        System.out.println("send word func vm \n");//TODO PRINTFORTEST
        String w;
        System.out.println("vertical send word- "+vertical);
        if (vertical.get()) {
            w = word.get() + "," + row.get() + "," + col.get() + ",T";
            System.out.println(w);
        } else
            w = word.get() + "," + row.get() + "," + col.get() + ",F";
        player.tryToPlace(w);
    }

    public void addTile() {
        player.getTileFromBag();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (o == player) {

            Platform.runLater(() -> {
                if(arg.equals("board")) {
                    gameBoard = (player.gameBoard);
                    String sendNotify = "board,"  + gameBoard;
                    setChanged();
                    notifyObservers(sendNotify);
                }
                if(arg.equals("tiles"))
                {
                    tilesArray = s.TilessArrayToSTring(player.p_tiles);
                    String sendNotify = "tiles" + "," + tilesArray;
                    setChanged();
                    notifyObservers(sendNotify);
                }
                if(arg.equals("scoreResult"))
                    scoreResult.set(player.score);
                if(arg.equals("totalScore"))
                    totalScore.set(player.totalScore);
                if(arg.equals("message"))
                    message.set(player.message);
                if(arg.equals("currentPlayer"))
                    currentPlayer.set(player.currentPlayer);
                if(arg.equals("closeGame")){
                    setChanged();
                    notifyObservers("closeGame");
                }

            });
        }
    }


}


