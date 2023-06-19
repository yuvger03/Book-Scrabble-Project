package com.example.clientside.view;

import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameScreenViewController {
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

    public void setGameVM(GameScreenViewModel gvm){
        this.GVM = gvm;
        GVM.word.bind(word.textProperty());
        GVM.row.bind(row.textProperty());
        GVM.col.bind(col.textProperty());
        GVM.vertical.bind(vertical.selectedProperty());
        scoreResult.textProperty().bind(gvm.scoreResult);
    }

}
