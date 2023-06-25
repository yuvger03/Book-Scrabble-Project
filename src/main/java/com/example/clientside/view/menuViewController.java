package com.example.clientside.view;

import com.example.clientside.HelloApplication;
import com.example.clientside.Models.GuestModeModel;
import com.example.clientside.Models.HostModeModel;
import com.example.clientside.viewmodel.GuestModeViewModel;
import com.example.clientside.viewmodel.HostModeViewModel;
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
    //@FXML
    //Button HostButton,GuestButton;
    MenuViewModel menuVM;

    public void setMenuVM(MenuViewModel menuVM){
        this.menuVM = menuVM;
        System.out.println(menuVM.name + "A");
        System.out.println(name.textProperty());
        menuVM.name.bind(name.textProperty());

    }
    public void pressedHost() throws IOException {
        HostModeViewModel hvm=menuVM.pressedHost();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/HostModeView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        HostModeViewController hvc=fxmlLoader.getController();
        hvc.setHostViewModel(hvm);
        hvm.addObserver(hvc);
    }
    public void pressedGuest() throws IOException {
        GuestModeViewModel guestvm = menuVM.pressedGuest();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/GuestModeView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        GuestModeViewController gvc = fxmlLoader.getController();
        gvc.setGuestViewModel(guestvm);
        guestvm.addObserver(gvc);
    }

    public void gotName(){menuVM.gotName();}

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}