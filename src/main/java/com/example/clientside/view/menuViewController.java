package com.example.clientside.view;

import com.example.clientside.viewmodel.MenuViewModel;
import javafx.beans.InvalidationListener;

import java.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    public void pressedHost(){ menuVM.pressedHost(); }
    public void pressedGuest(){ menuVM.pressedGuest(); }

    public void gotName(){menuVM.gotName();}

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
