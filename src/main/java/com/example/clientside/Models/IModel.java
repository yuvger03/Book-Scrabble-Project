package com.example.clientside.Models;

public interface IModel  {
    public HostModeModel startHostMode(String name);
    public GuestModeModel startGuestMode();
    public String getName();
    public void setName(String name);
}