package com.example.clientside.view;

import com.example.clientside.HelloApplication;
import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.HostModeViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class HostModeViewController implements Observer {

    HostModeViewModel HVM;

public void setHostViewModel(HostModeViewModel hvm){
    this.HVM=hvm;
}
    public void startGame() throws IOException {
        GameScreenViewModel gvm=new GameScreenViewModel(HVM.getHostModel());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/GameScreenView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
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
}
