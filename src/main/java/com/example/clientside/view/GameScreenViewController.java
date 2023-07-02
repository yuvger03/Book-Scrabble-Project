package com.example.clientside.view;

import com.example.Service;
import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

public class GameScreenViewController extends BoardViewController {
    @FXML
    TextField word;
    @FXML
    TextField row;
    @FXML
    TextField col;
    @FXML
    CheckBox vertical;
    @FXML
    Button sendWord;
    @FXML
    Label scoreResult;
    GameScreenViewModel GVM;
    BoardViewController BVC;
    Service s = new Service();

//TODO: add button end game &add button save game (only for host)

    public void setGameVM(GameScreenViewModel gvm){
        this.GVM = gvm;
        this.GVM.addObserver(this);
        GVM.row.bind(row.textProperty());
        GVM.col.bind(col.textProperty());
        GVM.word.bind(word.textProperty());
        GVM.vertical.bind(vertical.selectedProperty());
        scoreResult.textProperty().bind(GVM.scoreResult);
//        boardViewProperty.bind(GVM.gameBoard);
    }
    public void addTileToBoard(String Tile,boolean vertical){
        boardView.newTile(boardView.w,boardView.h,Integer.parseInt(row.getText()),Integer.parseInt(col.getText()),vertical,Tile);
    }
//    public void gotWord(){
//        GVM.gotWord();
//    }
//    public void gotRow(){ GVM.gotRow(); }
//
//    public void gotCol(){GVM.gotCol();}
//    public void checkboxPressed(){GVM.checkboxPressed();}
//    public void pressedSend(){GVM.pressedSend();}

    public void sendWord(){
        GVM.SendWord();
        //addTileToBoard(word.getText(),vertical.isSelected());
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o == GVM) && (arg instanceof String)) {
            String[] lineAsList = ((String) arg).split(",");
            System.out.println("theboard is "+lineAsList[0]+" the tiles is- "+lineAsList[1] );
            String[][] board = s.stringToMatrixS(lineAsList[0]);
            String[] tiles1 = lineAsList[1].split("");
            boardView.reDrawTilesBoard(board);
            tilesView.reDraw(tiles1);
        }
    }

    public void addTile() {
        GVM.addTile();
    }
}