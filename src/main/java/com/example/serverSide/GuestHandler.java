package com.example.serverSide;

import java.io.*;
import java.util.Scanner;

public class GuestHandler implements IClientHandler {
    BufferedReader in;
    PrintWriter out;
    HostManager HM;

    public GuestHandler(int serverPort) {
        this.HM=new HostManager(serverPort);
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) { //TODO implemnts the logic connection
       try {
           in = new BufferedReader(new InputStreamReader(inFromclient)); // remove the letter
           out = new PrintWriter(outToClient, true);
           String line = in.readLine();
           String[] lineAsList = line.split(",");
           String key = lineAsList[0];
           if(key.equals("joinToGame")){
              HM.addPlayerToGame(lineAsList[2]);
              out.println("you joind to game"); //TODO
           }
            if (key.equals("tryToPlace")){

           }
            if (key.equals("getTileFromBag")) {

           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

    @Override
    public void close()  {
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.close();
    }
}



