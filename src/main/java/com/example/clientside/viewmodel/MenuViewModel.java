package com.example.clientside.viewmodel;

import com.example.clientside.Models.GuestModeModel;
import com.example.clientside.Models.HostModeModel;
import com.example.clientside.Models.MenuModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;


public class MenuViewModel extends Observable implements Observer {
    private MenuModel mm;
    public StringProperty name;

//    public MenuViewModel(){
//        this.mm = new MenuModel();
//        name = new SimpleStringProperty();
//    }
    public MenuViewModel(MenuModel m){
        this.mm=m;
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


    public HostModeViewModel pressedHost() {
        HostModeModel hm = mm.startHostMode(name.get());
        HostModeViewModel hvm = new HostModeViewModel(hm);
        return hvm;
    }

    public GuestModeViewModel pressedGuest(){
        GuestModeModel gm=mm.startGuestMode();
        gm.setName(name.get());
        GuestModeViewModel guestvm=new GuestModeViewModel(gm);
        gm.addObserver(guestvm);
        return guestvm;
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



