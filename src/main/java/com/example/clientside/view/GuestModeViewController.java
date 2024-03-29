package com.example.clientside.view;

import com.example.clientside.StartHost;
import com.example.clientside.viewmodel.GameScreenViewModel;
import com.example.clientside.viewmodel.GuestModeViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GuestModeViewController implements Observer {
    GuestModeViewModel guestVm;
    @FXML
    TextField serverPort;

    @FXML
    Button start;
    public void setGuestViewModel(GuestModeViewModel guestvm) {
        this.guestVm=guestvm;
        guestVm.port.bind(serverPort.textProperty());
    }
    public void joinToGame() throws IOException {
        GameScreenViewModel gvm = new GameScreenViewModel(guestVm.getGuestModel());
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartHost.class.getResource("Views/GameScreenView.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        GameScreenViewController gvc = fxmlLoader.getController();
        gvc.setGameVM(gvm);
        gvm.addObserver(gvc);
        guestVm.joinToGame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}