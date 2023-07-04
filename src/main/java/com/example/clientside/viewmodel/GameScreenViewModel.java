package com.example.clientside.viewmodel;

import com.example.Game.Word;
import com.example.Service;
import com.example.clientside.Models.HostModeModel;
import com.example.clientside.Models.MenuModel;
import com.example.clientside.Models.PlayerModel;
import com.example.clientside.view.BoardView;
import javafx.application.Platform;
import javafx.beans.property.*;

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
        if (vertical.get()==true) {
            w = word.get() + "," + row.get() + "," + col.get() + ",T";
            System.out.println(w);
        } else
            w = word.get() + "," + row.get() + "," + col.get() + ",F";
        player.tryToPlace(w);
        // Word wd = s.stringToWord(w);
        //player.tryToPlace(wd);
    }

    public void addTile() {
        player.getTileFromBag();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (o == player) {
            gameBoard = (player.gameBoard);
            //System.out.println("board vm  "+gameBoard);
            tilesArray = s.TilessArrayToSTring(player.p_tiles);
            //System.out.println("gameBoard "+gameBoard.length());
            String sendNotify = gameBoard + "," + tilesArray;
            //String sendNotify=gameBoard +","+"ABCGKCD";

            Platform.runLater(() -> {
                setChanged();
                notifyObservers(sendNotify);
                scoreResult.set(player.score);
                totalScore.set(player.totalScore);
                message.set(player.message);
                currentPlayer.set(player.currentPlayer);

            });
        }
    }


}//end class



