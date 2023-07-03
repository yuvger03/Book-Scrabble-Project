package com.example.clientside.view;

import com.example.Service;
import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.HostGameViewModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

public class HostGameViewController extends GameScreenViewController {
    @FXML
    Button endGame;
    HostGameViewModel hvm;


    public void setGameVM(HostGameViewModel gvm) {
        super.setGameVM(gvm);
        this.hvm = gvm;
    }

    public void endGame(){
        hvm.endGame();
    }
}