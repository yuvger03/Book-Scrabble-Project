package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.*;
import java.util.ArrayList;

public class GuestHandler implements IClientHandler {
    BufferedReader in;
    PrintWriter out;
    public HostManager HM;
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
           System.out.println(line+"\n");//TODO
           String[] lineAsList = line.split("-");
           String playerName=lineAsList[0];
           String key = lineAsList[1];
           if(key.equals("joinToGame")){
             if(HM.addPlayerToGame(lineAsList[0])){
               out.println(playerName+"-"+"message-you joind to game- "+"\n");
//               out.flush();
//               System.out.println(playerName+"-"+"message-you joind to game");
             }
             else{
                 out.println(playerName+"-"+"message-you not joind to game- "+"\n");
//                 out.flush();
//                 System.out.println(playerName+"-"+"message-you not joind to game");
             }
           }
           if(key.equals("startGame")) {
               System.out.println("gameStarted\n"); //TODO shira
               HM.current_player=HM.playersList.get(HM.index);
               for(int i=0;i<HM.playersList.size();i++){
                   ArrayList<Tile>tiles=HM.initTileArray();
                   String tielsString="";
                   for(int j=0;j<tiles.size();j++){
                       tielsString+=service.TileToString(tiles.get(j))+"/";
                   }
                   out.println(HM.playersList.get(i)+"-initTiles-" + tielsString+"- "+"\n");
//                   out.flush();
               }
               out.println("board-"+ HM.getBoardGame()+"\n");
//               out.flush();
           }
           if(playerName.equals(HM.current_player)){
               if (key.equals("tryToPlace")) {
                   System.out.println("send word func server \n");//TODO PRINTFORTEST
                   String wordString = lineAsList[2];
                   Word word = service.stringToWord(wordString);
                   int score = HM.tryPlaceWord(word);
                   String fillTiles = "";
                   if (score > 0) {
                       int count = wordString.length();
                       fillTiles = HM.fillTilesArray(count);
                       fillTiles += "/" + wordString;
                   }
                   System.out.println("send to" +playerName+"- try to place"+"\n");
                   out.println(playerName + "-tryToPlace-" + String.valueOf(score) + "-" + fillTiles+ "\n");
//                   out.flush();

//                   if (score > 0){//TODO: return it
                   System.out.println("send to everyone the board");
                   out.println("board-" + HM.getBoardGame()+"\n");
//                   out.flush();
                  // }
                   HM.nextPlayer();
                   out.println("message-"+"The current turn is of "+HM.current_player+"\n");
//                   out.flush();

               }
               if (key.equals("getTileFromBag")) {
                   Tile t  = HM.getRand();
                   String s = service.TileToString(t);
                   out.println(playerName+"-getTileFromBag-"+s+"- "+"\n");
//                   out.flush();
               }
           }
         else if(!key.equals("startGame")&&!key.equals("joinToGame"))
             out.println(playerName+"-"+"message-not your turn,please wait to your turn- "+"\n");
//             out.flush();

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



