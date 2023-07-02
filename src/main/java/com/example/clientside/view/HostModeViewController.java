package com.example.clientside.view;

import com.example.clientside.HelloApplication;
import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.HostModeViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class HostModeViewController implements Observer {

    HostModeViewModel HVM;
    @FXML
    Button start;
public void setHostViewModel(HostModeViewModel hvm){
    this.HVM=hvm;
}
    public void startGame() throws IOException {
        GameScreenViewModel gvm=new GameScreenViewModel(HVM.getHostModel());
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/HostGameView.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        GameScreenViewController gvc=fxmlLoader.getController();
        gvc.setGameVM(gvm);
        gvm.addObserver(gvc);
        HVM.startGame();

    }


    @Override
    public void update(Observable o, Object arg) {

    }

    public void resumeGame(ActionEvent actionEvent) throws IOException {
        GameScreenViewModel gvm=new GameScreenViewModel(HVM.getHostModel());
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/HostGameView.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        GameScreenViewController gvc=fxmlLoader.getController();
        gvc.setGameVM(gvm);
        gvm.addObserver(gvc);
        HVM.resumeGame();
    }
}
