package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.*;
import java.util.Scanner;

public class GuestHandler implements IClientHandler {
    BufferedReader in;
    PrintWriter out;
    HostManager HM;
    Service service=new Service();

    public GuestHandler(int serverPort) {
        this.HM=new HostManager(serverPort);
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
       try {
           in = new BufferedReader(new InputStreamReader(inFromclient)); // remove the letter
           out = new PrintWriter(outToClient, true);
           String line = in.readLine();
           String[] lineAsList = line.split("-");
           String key = lineAsList[0];
           if(key.equals("joinToGame")){
              HM.addPlayerToGame(lineAsList[2]);
              out.println("you joind to game"); //TODO
           }
            if (key.equals("tryToPlace")){
                Word word = service.stringToWord(lineAsList[1]);
                int score = HM.tryPlaceWord(word);
                out.println(String.valueOf(score));
            }
            if (key.equals("getTileFromBag")) {
                Tile t  = HM.getRand();
                String s = service.TileToString(t);
                out.println(s);
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



