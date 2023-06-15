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
    private MenuModel mm;
    public StringProperty name;

    public MenuViewModel(){
        this.mm = new MenuModel();
        //this.mm.addObserver(this);
        name = new SimpleStringProperty();
    }
    public MenuViewModel(MenuModel m){
        this.mm=m;
        //this.mm.addObserver(this);
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
        setChanged();
        notifyObservers();
    }



    public void pressedHost() {
        mm.setName(name.get());
        System.out.println(name.get());
//        mm.startHostMode();
    }
    public void pressedGuest(){
        System.out.println(name.get() + "a");
//        mm.startGuestMode();
    }

    public void gotName(){
        mm.getName();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (o==mm){
            name.set(mm.getName());}
    }
}



