package com.example.clientside.view;

import com.example.clientside.Models.Service;
import com.example.clientside.viewmodel.GameScreenViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Observable;

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
    Label scoreResult;
    @FXML
    Label totalScore;
    @FXML
    Label message;
    @FXML
    Label currentPlayer;
    GameScreenViewModel GVM;
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
        totalScore.textProperty().bind(GVM.totalScore);
        message.textProperty().bind(GVM.message);
        currentPlayer.textProperty().bind(GVM.currentPlayer);
    }
    public void addTileToBoard(String Tile,boolean vertical){
        boardView.newTile(boardView.w,boardView.h,Integer.parseInt(row.getText()),Integer.parseInt(col.getText()),vertical,Tile);
    }

    public void sendWord(){
        GVM.SendWord();
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o == GVM) && (arg instanceof String)) {
            if(arg.equals("closeGame")){
                Stage stage = (Stage) message.getScene().getWindow();
                stage.close();
            }
            String[] lineAsList = ((String) arg).split(",");
            if (lineAsList[0].equals("board")) {
                String[][] board = s.stringToMatrixS(lineAsList[1]);
                boardView.reDrawTilesBoard(board);
            }
            if (lineAsList[0].equals("tiles")) {
                String[] tiles1 = lineAsList[1].split("");
                tilesView.reDraw(tiles1);
            }
        }
    }

    public void addTile() {
        GVM.addTile();
    }
}