package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.*;
import java.util.ArrayList;

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
           String namePlayer=lineAsList[0];
           String key = lineAsList[1];
           if(key.equals("joinToGame")){
             if(HM.addPlayerToGame(lineAsList[0]))
              out.println("you joind to game"); //TODO
               else{
                 out.println("you not joind to game");
             }
           }
            if (key.equals("tryToPlace")){
                if(HM.getPlayerTurn().equals(lineAsList[0])) {
                    Word word = service.stringToWord(lineAsList[2]);
                    int score = HM.tryPlaceWord(word);
                    out.println(lineAsList[0]+"-"+String.valueOf(score));
                    out.flush();
                    if(score>0)
                        out.println("board-"+ HM.getBoardGame());
                }
                else
                    out.println("not your turn");
            }
            if (key.equals("getTileFromBag")) {
                Tile t  = HM.getRand();
                String s = service.TileToString(t);
                out.println(lineAsList[0]+"-"+s);
           }
            if(key.equals("initTiles")){
                ArrayList<Tile>tiels=HM.initTileArray();
                String tielsString="";
                for(int i=0;i<tiels.size();i++){
                    tielsString+=service.TileToString(tiels.get(i))+"-";
                }
                out.println(lineAsList[0]+"-"+tielsString);
            }
           if (key.equals("startGame")) {
               out.println("start");
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



