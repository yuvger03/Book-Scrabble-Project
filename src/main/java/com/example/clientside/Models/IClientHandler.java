package com.example.clientside.Models;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientHandler {
    void handleClient(InputStream inFromclient, OutputStream outToClient);

    void close();
}
