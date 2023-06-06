package com.example.clientside.viewmodel;

import com.example.clientside.Models.MenuModel;
import com.example.clientside.Models.PlayerModel;
import javafx.beans.InvalidationListener;
import java.util.Observable;

import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import java.util.Observer;

public class MenuViewModel extends Observable implements Observer {
    MenuModel mm;
    public StringProperty name;

    public MenuViewModel(){
        this.mm = new MenuModel();
        name = new SimpleStringProperty();
    }
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }



    public void pressedHost(){
        System.out.println(name);
//        mm.startHostMode();
    }
    public void pressedGuest(){
        System.out.println(name.toString() + "a");
//        mm.startGuestMode();
    }



    public void gotName(){
        mm.getName(name.get());
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (o==mm){

            //set mode
        }
    }



}
