package com.example.clientside.viewmodel;

import com.example.Game.Word;
import com.example.Service;
import com.example.clientside.Models.HostModeModel;
import com.example.clientside.Models.MenuModel;
import com.example.clientside.Models.PlayerModel;
import javafx.beans.property.*;

import java.util.Observable;

import java.util.Observer;

public class GameScreenViewModel extends Observable implements Observer {
    PlayerModel player;
    public StringProperty scoreResult;
    public StringProperty word;
    public StringProperty row;
    public StringProperty col;
    public BooleanProperty vertical;
    Service s;
    public StringProperty gameBoard;
    public GameScreenViewModel(PlayerModel pm){
        this.player =pm;
        this.player.addObserver(this);
        word = new SimpleStringProperty();
        row=new SimpleStringProperty();
        col=new SimpleStringProperty();
        vertical=new SimpleBooleanProperty();
        scoreResult= new SimpleStringProperty();
        s=new Service();
        gameBoard=new SimpleStringProperty();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (o==player) {
            scoreResult.set(player.score);
            gameBoard.set(player.gameBoard);
        }
    }

    public void SendWord() {//make obj WORD to string- "word,row,col,vertical"
        System.out.println("send word func vm \n");//TODO PRINTFORTEST
        String w;
        if (vertical.equals(true)) {
            w = word.get() + "," + row.get() + "," + col.get() + ",T";
        } else
            w = word.get() + "," + row.get() + "," + col.get() + ",F";
        Word wd = s.stringToWord(w);
        player.tryToPlace(wd);
    }
    public void addTiles(){
        player.getTileFromBag();
    }
}


