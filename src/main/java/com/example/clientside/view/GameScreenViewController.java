package com.example.clientside.view;

import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Observer;

public class GameScreenViewController implements Observer {
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
    BoardViewController board;
    GameScreenViewModel GVM;

    public void setGameVM(GameScreenViewModel gvm){
        this.GVM = gvm;
        this.GVM.addObserver(this);
        GVM.word.bind(word.textProperty());
        GVM.row.bind(row.textProperty());
        GVM.col.bind(col.textProperty());
        GVM.vertical.bind(vertical.selectedProperty());
        scoreResult.textProperty().bind(GVM.scoreResult);
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
    }
        @Override
        public void update(java.util.Observable o, Object arg) {

        }
}
