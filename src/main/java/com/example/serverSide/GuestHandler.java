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
           String playerName=lineAsList[0];
           String key = lineAsList[1];
           if(key.equals("joinToGame")){
             if(HM.addPlayerToGame(lineAsList[0]))
               out.println(playerName+"-"+"message-you joind to game"); //TODO
               else{
                 out.println(playerName+"-"+"message-you not joind to game");
             }
           }
           if (key.equals("startGame")) {
               HM.current_player=HM.playersList.get(HM.index);
               for(int i=0;i<HM.playersList.size();i++){
                   ArrayList<Tile>tiels=HM.initTileArray();
                   String tielsString="";
                   for(int j=0;j<tiels.size();j++){
                       tielsString+=service.TileToString(tiels.get(j))+"/";
                   }
                   out.println(HM.playersList.get(i)+"-initTiles-" + tielsString+"- ");
                   out.flush();
               }
               out.println("board-"+ HM.getBoardGame());
           }
           if(playerName.equals(HM.current_player)){
               if (key.equals("tryToPlace")) {
                   String wordString = lineAsList[2];
                   Word word = service.stringToWord(wordString);
                   int score = HM.tryPlaceWord(word);
                   String fillTiles = "";
                   if (score > 0) {
                       int count = wordString.length();
                       fillTiles = HM.fillTilesArray(count);
                       fillTiles += "/" + wordString;
                   }
                   out.println(playerName + "-tryToPlace-" + String.valueOf(score) + "-" + fillTiles);
                   out.flush();

                   if (score > 0){
                       out.println("board-" + HM.getBoardGame());
                       out.flush();
                   }
                   HM.nextPlayer();
                   //out next player turn

               }
               if (key.equals("getTileFromBag")) {
                   Tile t  = HM.getRand();
                   String s = service.TileToString(t);
                   out.println(playerName+"-getTileFromBag-"+s+"- ");
               }
           }
         else
             out.println(playerName+"-"+"message-not your turn,please wait to your turn- ");

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



