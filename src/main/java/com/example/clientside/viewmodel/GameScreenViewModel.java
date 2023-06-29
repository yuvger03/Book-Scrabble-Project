package com.example.clientside.viewmodel;

import com.example.Game.Word;
import com.example.Service;
import com.example.clientside.Models.HostModeModel;
import com.example.clientside.Models.MenuModel;
import com.example.clientside.Models.PlayerModel;
import com.example.clientside.view.BoardView;
import javafx.beans.property.*;

import java.util.Observable;

import java.util.Observer;

public class GameScreenViewModel extends Observable implements Observer {
    PlayerModel player;
    public StringProperty scoreResult;
    public StringProperty word;
    //    public StringProperty gameBoard;
    public String gameBoard;
    public String tilesArray;
    public StringProperty row;
    public StringProperty col;
    public BooleanProperty vertical;
    //    public StringProperty boardView;
//    public String boardview;
    Service s;

    public GameScreenViewModel(PlayerModel pm){
        this.player =pm;
        this.player.addObserver(this);
        word = new SimpleStringProperty();
        row=new SimpleStringProperty();
        col=new SimpleStringProperty();
        vertical=new SimpleBooleanProperty();
        scoreResult= new SimpleStringProperty();
        gameBoard="";
        //tilesArray=new SimpleStringProperty();
        s=new Service();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
//        if (o==player) {
        scoreResult.set(player.score);
        gameBoard =(player.gameBoard);
        System.out.println("board vm  "+gameBoard);
        tilesArray=s.TilessArrayToSTring(player.p_tiles);
        //System.out.println("gameBoard "+gameBoard.length());
        String sendNotify=gameBoard+","+tilesArray;
        //String sendNotify=gameBoard +","+"ABCGKCD";

        setChanged();
        notifyObservers(sendNotify);
//        }
    }

    public void SendWord() {//make obj WORD to string- "word,row,col,vertical"
        System.out.println("send word func vm \n");//TODO PRINTFORTEST
        String w;
        if (vertical.equals(true)) {
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
}

