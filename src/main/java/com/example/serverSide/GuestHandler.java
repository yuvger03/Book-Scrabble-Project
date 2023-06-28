package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;
import com.example.clientside.Models.HostModeModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestHandler implements IClientHandler {
    Scanner in;
    PrintWriter out;
    public HostManager HM;
    Service service=new Service();
    public HostModeModel host;

    public GuestHandler(int serverPort) {
        this.HM=new HostManager(serverPort);
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        in = new Scanner(inFromclient); // remove the letter
        out = new PrintWriter(outToClient, true);
        while(in.hasNextLine()) {
            try {
                String line = in.nextLine();
                System.out.println(line);//TODO
                String[] lineAsList = line.split("-");
                String playerName = lineAsList[0];
                String key = lineAsList[1];
                if (key.equals("joinToGame")) {
                    if (HM.addPlayerToGame(lineAsList[0])) {
                        host.notifyAll(playerName + "-" + "message- joined to game- ");
                        System.out.println("server"+playerName + "-" + "joined to game");
//                        out.println(playerName + "-" + "message-you joined to game- " + "\n");
//                          out.flush();
                    } else {
                        host.notifyAll(playerName + "-" + "message-you not joind to game- ");
//                      out.flush();
                    }
                }
                if (key.equals("startGame")) {
                    System.out.println("gameStarted\n"); //TODO shira
                    HM.current_player = HM.playersList.get(HM.index);
                    for (int i = 0; i < HM.playersList.size(); i++) {
                        ArrayList<Tile> tiles = HM.initTileArray();
                        String tielsString = "";
                        for (int j = 0; j < tiles.size(); j++) {
                            tielsString += service.TileToString(tiles.get(j)) + "/";
                        }
                        host.notifyAll(HM.playersList.get(i) + "-initTiles-" + tielsString + "- ");
//                   out.flush();
                    }
                    host.notifyAll("board-" + HM.getBoardGame());
//                    System.out.println("board-" + HM.getBoardGame() );

//               out.flush();
                }
                if (playerName.equals(HM.current_player)) {
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
                        System.out.println("send to" + playerName + "- try to place" + "\n");
                        host.notifyAll(playerName + "-tryToPlace-" + String.valueOf(score) + "-" + fillTiles);
                        if (score > 0) {//TODO: return it
                           System.out.println("send to everyone the board");
                           host.notifyAll("board-" + HM.getBoardGame());
                       }
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
//             out.flush();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close()  {
        in.close();
        out.close();
    }
}



