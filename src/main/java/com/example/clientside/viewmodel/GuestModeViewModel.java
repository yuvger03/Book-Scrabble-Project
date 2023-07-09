package com.example.clientside.viewmodel;

import com.example.clientside.Models.GuestModeModel;
import javafx.beans.property.SimpleStringProperty;
import java.util.Observer;
import java.util.Observable;

public class GuestModeViewModel extends Observable implements Observer {
    GuestModeModel guestModel;
    public SimpleStringProperty port;

    public GuestModeViewModel(GuestModeModel guestModel) {
        this.guestModel = guestModel;
        port=new SimpleStringProperty();
    }

    public void joinToGame() {
        guestModel.serverPort= Integer.parseInt(port.get());
        guestModel.enterToGame(  );

    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    public GuestModeModel getGuestModel() {
        return  guestModel;
    }

}
