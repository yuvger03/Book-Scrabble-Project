package com.example.clientside.view;

import com.example.clientside.viewmodel.HostGameViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HostGameViewController extends GameScreenViewController {
    @FXML
    Button endGame;
    @FXML
    Button saveGame;
    HostGameViewModel hvm;


    public void setGameVM(HostGameViewModel gvm) {
        super.setGameVM(gvm);
        this.hvm = gvm;
    }
    public void saveGame(){
        hvm.saveGame();

    }
    public void endGame(){
        hvm.endGame();
        Stage stage = (Stage) endGame.getScene().getWindow();
        stage.close();
    }

}