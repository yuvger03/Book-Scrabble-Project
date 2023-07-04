package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestHandler implements IClientHandler {
    public HostManager HM;
    Service service=new Service();
    Boolean stop=false;
    public MyHostServer host;

    public GuestHandler(int serverPort) {
        this.HM=new HostManager(serverPort);
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        Scanner in = new Scanner(inFromclient); // remove the letter
         while(in.hasNext()){
             System.out.println("line 28\n");
             if (in.hasNextLine()) {
                 System.out.println("line 29\n");
                 try {
                     System.out.println("line-server\n");//TODO
                     String line = in.nextLine();
                     System.out.println("server " + line);
                     //System.out.println(line);//TODO
                     String[] lineAsList = line.split("-");
                     String playerName = lineAsList[0];
                     String key = lineAsList[1];
                     if (key.equals("joinToGame")) {
                         if (HM.addPlayerToGame(lineAsList[0])) {
                             HM.setPlayerScore(0,playerName);//TODO:ask Shira
                             HM.setPlayerpTiles("",playerName);
                             //TODO:save to DB
                             host.notifyAll(playerName + "-" + "message- joined to game- ");
                             System.out.println("server " + playerName + "-" + "joined to game");
                         } else {
                             host.notifyAll(playerName + "-" + "message-you not joind to game- ");


                         }
                     }
                     if (key.equals("startGame")) {
                         System.out.println("gameStarted\n"); //TODO shira
                         HM.current_player = HM.playersList.get(HM.index);
                         System.out.println("HM.current_player "+ HM.playersList.get(HM.index)); //TODO shira
                         for (int i = 0; i < HM.playersList.size(); i++) {
                             ArrayList<Tile> tiles = HM.initTileArray();
                             String tielsString = "";
                             for (int j = 0; j < tiles.size(); j++) {
                                 //tielsString += service.TileToString(tiles.get(j)) + "/";
                                 tielsString += service.TileToString(tiles.get(j));
                             }
                             host.notifyAll(HM.playersList.get(i) + "-initTiles-" + tielsString + "-null");
                         }
                         host.notifyAll("board-" + HM.getBoardGame());
                     }
                     if (playerName.equals(HM.current_player)) {
                         if (key.equals("tryToPlace")) {
                             /////////////TODO for test view////////////////////////////
                             System.out.println("send word func server \n");//TODO PRINTFORTEST
                             //String wordString = service.getWordString(lineAsList[2]);
                             //int score = 100;
                             
                             //String fillTiles = "";
                             //if (score > 0) {
                               //  int count = wordString.length();
                                // fillTiles = HM.fillTilesArray(count);
                                // fillTiles += "/" + wordString;
//                              HM.setPlayerpTiles(fillTiles,playerName); //TODO:return it
//                                 HM.setPlayerScore(score,playerName);

                             // }
                             ////////////////////////////////////////////////////////////
                             //System.out.println("send word func server \n");//TODO PRINTFORTEST
                             //String wordT = lineAsList[2];
                             String wordString = service.getWordString(lineAsList[2]);
                             Word word = service.stringToWord(lineAsList[2]);
                             int score = HM.tryPlaceWord(word);
                             String fillTiles = "null";
                             if (score > 0) {
                                //int count = wordString.length();
                                fillTiles = HM.fillTilesArray( wordString.length());
                             fillTiles += "/" + wordString;
                             }
                             //System.out.println("send to" + playerName + "- try to place" + "\n");
                             host.notifyAll(playerName + "-tryToPlace-" + String.valueOf(score) + "-" + fillTiles);
                             //if (score > 0) {//TODO: return it
                             //placement the word
                             //int row = word.getRow();
                             //int  col = word.getCol();
                             //for (Tile t : word.getTiles()) {
                                // HM.gameboard.tiles[row][col] = t;
                                 //if (word.isVertical())
                                     //row++;
                                 //else
                                     //col++;
                             //}
                             System.out.println("send to everyone the board");
                             host.notifyAll("board-" + HM.getBoardGame());

                             HM.nextPlayer();
                             host.notifyAll("message-" + "The current turn is of " + HM.current_player);
                         }
                         if (key.equals("getTileFromBag")) {
                             Tile t = HM.getRand();
                             String s = service.TileToString(t);
                             host.notifyAll(playerName + "-getTileFromBag-" + s + "- ");
                         }
                     } else if (!key.equals("startGame") && !key.equals("joinToGame"))
                         host.notifyAll(playerName + "-" + "message-not your turn,please wait to your turn- ");

                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }
        }
    }
public void tryToPlaceForView(){

}
    @Override
  public void close()  {
        //in.close();
        //out.close();
    }
}



