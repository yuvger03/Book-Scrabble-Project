package com.example.clientside.viewmodel;

import com.example.clientside.Models.MenuModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import java.util.Observer;

public class MenuViewModel implements Observer, Observable {
    MenuModel mm;
    public StringProperty name;



    public MenuViewModel(){
        this.mm = new MenuModel();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
    public void pressedHost(){
        mm.startHostMode();
    }

}
