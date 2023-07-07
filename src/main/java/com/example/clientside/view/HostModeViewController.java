package com.example.clientside.view;

import com.example.clientside.HelloApplication;
import com.example.clientside.viewmodel.HostGameViewModel;
import com.example.clientside.viewmodel.HostModeViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class HostModeViewController implements Observer {

    HostModeViewModel HVM;
    @FXML
    Button start;
    @FXML
    Label hostPort;
    @FXML
    ListView<String> myListView;
    @FXML
    TextField resumeGame;
public void setHostViewModel(HostModeViewModel hvm){
    this.HVM=hvm;
    this.HVM.addObserver(this);
    hostPort.textProperty().bind(HVM.hostPort);
    myListView.itemsProperty().bind(HVM.playersList);


}
    public void startGame() throws IOException {
        HostGameViewModel hgvm=new HostGameViewModel(HVM.getHostModel());
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/HostGameView.fxml"));

        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        HostGameViewController gvc=fxmlLoader.getController();
        gvc.setGameVM(hgvm);
        hgvm.addObserver(gvc);
        HVM.startGame();

    }


    @Override
    public void update(Observable o, Object arg) {

    }

    public void resumeGame(ActionEvent actionEvent) throws IOException {
        HostGameViewModel hgvm=new HostGameViewModel(HVM.getHostModel());
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/HostGameView.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        HostGameViewController gvc=fxmlLoader.getController();
        gvc.setGameVM(hgvm);
        hgvm.addObserver(gvc);
        HVM.resumeGame(Integer.parseInt(resumeGame.textProperty().get()));
    }

    public void showPort() {
    HVM.showPort();
    }


    public void checkconnectedPlayers() {
    HVM.checkconnectedPlayers();
    }
}
