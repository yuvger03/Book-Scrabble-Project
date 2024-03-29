package com.example.clientside.view;

import com.example.clientside.StartHost;
import com.example.clientside.viewmodel.GuestModeViewModel;
import com.example.clientside.viewmodel.HostModeViewModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observer;

public class menuViewController implements Observer {
    @FXML
    TextField name;
    MenuViewModel menuVM;

    public void setMenuVM(MenuViewModel menuVM){
        this.menuVM = menuVM;
        System.out.println(menuVM.name + "A");
        System.out.println(name.textProperty());
        menuVM.name.bind(name.textProperty());

    }
    public void pressedHost() throws IOException {
        HostModeViewModel hvm=menuVM.pressedHost();
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartHost.class.getResource("Views/HostModeView.fxml"));
        stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        HostModeViewController hvc=fxmlLoader.getController();
        hvc.setHostViewModel(hvm);
        hvm.addObserver(hvc);
    }
    public void pressedGuest() throws IOException {
        GuestModeViewModel guestvm = menuVM.pressedGuest();
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartHost.class.getResource("Views/GuestModeView.fxml"));
        stage = new Stage();
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