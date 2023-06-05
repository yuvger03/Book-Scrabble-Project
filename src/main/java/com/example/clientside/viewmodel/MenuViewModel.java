package com.example.clientside.viewmodel;

import com.example.clientside.Models.MenuModel;
import javafx.beans.InvalidationListener;
import java.util.Observable;

import java.util.Observer;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import java.util.Observer;

public class MenuViewModel extends Observable implements Observer {
    MenuModel mm;
    public StringProperty name;



    public MenuViewModel(){
        this.mm = new MenuModel();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }


    public void pressedHost(){
        mm.startHostMode();
    }
    public void pressedGuest(){
        mm.startGuestMode();
    }
}
