package com.example.clientside.viewmodel;

public interface IViewModel {

    public String getName();
    public void setName(String name);
    public HostModeViewModel pressedHost();
    public GuestModeViewModel pressedGuest();
    public void gotName();

    }