package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.clientside.Models.Service;

import java.io.InputStream;
import java.io.OutputStream;
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
             if (in.hasNextLine()) {
                 try {
                     String line = in.nextLine();
                     System.out.println("server " + line);
                     String[] lineAsList = line.split("-");
                     String playerName = lineAsList[0];
                     String key = lineAsList[1];
                     if (key.equals("joinToGame")) {
                         if (HM.addPlayerToGame(lineAsList[0])) {
                             HM.setPlayerScore(0,playerName);
                             HM.setPlayerTiles("",playerName);
                             host.notifyAll(playerName + "-" + "message- YOU JOIN GAME:) PLEASE WAIT THE GAME STARTED BY HOST- ");
                             System.out.println("server " + playerName + "-" + "joined to game");
                         } else {
                         }
                     }
                     if (playerName.equals(HM.current_player)) {
                         if (key.equals("tryToPlace")) {
                             String wordString = service.getWordString(lineAsList[2]);
                             Word word = service.stringToWord(lineAsList[2]);
                             int score = HM.tryPlaceWord(word);
                             HM.setPlayerScore(score,playerName);
                             String fillTiles = "null";
                             if (score > 0) {
                                 String updatedString = wordString.replace("_", "");
                                 fillTiles = HM.fillTilesArray(updatedString);
                                 fillTiles += "/" + updatedString;
                                 HM.setPlayerTiles(fillTiles,playerName);
                             }
                             host.notifyAll(playerName + "-tryToPlace-" + String.valueOf(score) + "-" + fillTiles);
                             host.notifyAll(playerName + "-totalScore-"+ HM.scoreMap.get(playerName)+"-null");
                             host.notifyAll("board-" + HM.getBoardGame());
                             if(Integer.parseInt(HM.scoreMap.get(playerName))>=100) {
                                 host.notifyAll("message-GAME OVER ! THE WINNER OF GAME IS " + playerName);
                                 try {
                                     Thread.sleep(5000); // Sleep for 2 seconds
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                                 host.gameOver();
                             }
                             HM.nextPlayer();
                             host.notifyAll("turn-" + "TURN OF:"+HM.current_player);
                         }
                         if (key.equals("getTileFromBag")) {
                             Tile t = HM.getRand();
                             String s = service.TileToString(t);
                             host.notifyAll(playerName + "-getTileFromBag-" + s + "- ");
                             HM.nextPlayer();
                             host.notifyAll("turn-" + "TURN OF:"+HM.current_player);
                         }
                     } else if (!key.equals("startGame") && !key.equals("joinToGame"))
                         host.notifyAll(playerName + "-" + "message-NOT YOUR TURN, PLEASE WAIT TO YOUR TURN- ");

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
    }
}



