package com.example.clientside.view;

import com.example.clientside.HelloApplication;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.beans.InvalidationListener;

import java.io.IOException;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Application;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class menuViewController implements Observer {
    @FXML
    TextField name;
    @FXML
    Button HostButton,GuestButton;
    MenuViewModel menuVM;

    public void setMenuVM(MenuViewModel menuVM){
        this.menuVM = menuVM;
        menuVM.name.bind(name.textProperty());
    }
    public void pressedHost() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/HostModeView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
        menuVM.pressedHost(); }
    public void pressedGuest() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/GuestModeView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
        menuVM.pressedGuest(); }

    public void gotName(){menuVM.gotName();}

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}