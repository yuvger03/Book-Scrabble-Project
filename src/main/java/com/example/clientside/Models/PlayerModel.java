package com.example.clientside.Models;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class PlayerModel extends Observable {
    static final int  countTiles = 7;
    public int ipServer;
    String name;
    int totalScore;
    public String score; //TODO : bind to RESULT IN GAMEsCREEM
    public String gameBoard;
    private Board board_game; //YUVAL
    public ArrayList<Tile> p_tiles;
    //public String tilesStringArray;
    public int serverPort;
    int turn;
    String currentTurn;
    String message;
    Scanner inFromServer;
    PrintWriter outToServer;
    Service service;
    boolean itsTurn = false;
    CountDownLatch connectionLatch; // added field
    boolean stop= false;

    //TODO: understand how gameServer connection works
    //Server gameServer; each player has a instance of its gameServer.
    public PlayerModel() {
        name = null;
        totalScore = 0;
        board_game = new Board();
        p_tiles = new ArrayList<>();
        service = new Service();
        connectionLatch = new CountDownLatch(1); // initialize the latch
        gameBoard="";
        for(int i=0;i<225;i++){
            gameBoard+="n";
        }
        System.out.println(gameBoard);
    }

    public String getName() {
        return name;
    }
    public ArrayList<Tile> getP_tiles() {
        return p_tiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setP_tiles(ArrayList<Tile> p_tiles) {
        this.p_tiles = p_tiles;
    }
    public void connectServer() {
        try {
            Socket server = new Socket("localhost", this.serverPort);
            this.outToServer = new PrintWriter(server.getOutputStream(),false);
            this.inFromServer = new Scanner(server.getInputStream());
            connectionLatch.countDown(); // signal that the connection is established
            joinToGame(); // Invoke joinToGame() after the connection is established
            Thread t= new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop) {
                        //if (inFromServer.hasNextLine()) {
                            String message = inFromServer.nextLine();
                            //System.out.println(message);
                            if(!message.equals(""))
                                processMessage(message);
                        //}
                    }
                }
            });
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void joinToGame() {
        outToServer.println(this.name + "-" + "joinToGame" + "-");
        outToServer.flush();
    }
    private void processMessage(String message) {
        String[] lineAsList = message.split("-");
//        if(lineAsList[1].equals("startGame")) {
//            outToServer.println(this.name + "-" + "startGame" + "-");
//            outToServer.flush();
//            System.out.println(this.name+ "outToServer startGame-"+outToServer);//TODO PRINTFORTEST
//        }
        if (lineAsList[0].equals("board")) {
           gameBoard=lineAsList[1];
           setChanged();
           notifyObservers();
            //this.board_game.tiles = service.stringToMatrix(lineAsList[1]);
        } else if(lineAsList[0].equals("message")) {
            this.message=lineAsList[1];
        }
    else if (lineAsList[0].equals(name)) {
            System.out.println(message);
            //getFunc(lineAsList[1],lineAsList[2]);//TODO: check if it possible in try to place
            getFunc(lineAsList[1],lineAsList[2],lineAsList[3]);//TODO: check if it possible in try to place
        }

        }
    private void getFunc(String... args) {
        String func=args[0];
        String inputString=args[1];
        System.out.println("inputString of player "+name+" "+inputString);
        System.out.println("func of player "+name+" "+func);

        if (func.equals("tryToPlace")) {
            if (inputString.equals("0")) {
                score = "not valid";
            } else {
                score = inputString;
                System.out.println(args[2]);
                String[] Tiles=args[2].split("/");
                Tile[]missingTilesArray=service.StringToTilesArray(Tiles[0]);
                Tile[] word=service.StringToTilesArray(Tiles[1]);;
                //service.stringToWord();
                for(int i=0;i< word.length;i++){
                    int j=0;
                  while(p_tiles.get(j).letter!=(word[i].letter)) {
                     j++;
                  }
                  p_tiles.remove(j);
                  p_tiles.add(missingTilesArray[i]);
                }

            }
            setChanged();
            notifyObservers();
        }
        if (func.equals("initTiles")) {
            initTiles(inputString);
        }
        if(func.equals("getTileFromBag")){
            p_tiles.add(service.stringToTile(inputString));
        }
        if(func.equals("message")){
          this.message=inputString;
        }

    }
    public void initTiles(String tilesString) {
            //tilesStringArray=tilesString;
            String[] TileAsList = tilesString.split("");
            for (int i = 0; i < TileAsList.length; i++)
                p_tiles.add(service.stringToTile(TileAsList[i]));
            setChanged();
            notifyObservers();
        }


    public void tryToPlace(String word) {
        //String s=word.toString();
        System.out.println(this.name+"send word func model \n");//TODO PRINTFORTEST
       // String s = service.WordToString(word);

        //boolean valid = service.validateWord(s, p_tiles);TODO RETURN
        //if (!valid) {TODO RETURN
        //    score = "not_valid";TODO RETURN
        //    return;TODO RETURN
       // }TODO RETURN
        try {
            connectionLatch.await(); // wait for the connection to be established
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TRY SEND SERVER \n");//TODO PRINTFORTEST
        //System.out.println(word);
        //System.out.println(this.name+ "-tryToPlace" + "-" + word);
        outToServer.println(this.name+ "-tryToPlace" + "-" + word);
        outToServer.flush();
        System.out.println("outToServer try to place-"+outToServer);//TODO PRINTFORTEST
        System.out.println("TRY SEND SERVER777 \n");//TODO PRINTFORTEST
    }

    public void getTileFromBag() {
        if(p_tiles.size()<countTiles) {
            outToServer.println(this.name + "-" + "getTileFromBag-");
            outToServer.flush();
        }
    }
        public void close(){
            inFromServer.close();
            outToServer.close();
        }
//TODO:implement the gameServer connection funcs.
//    public abstract void connectServer();
//    public boolean isWordValid(ArrayList<> word)
//    public int isLocationValid(ArrayList<> Location) -list [x,y] or (int x, int y)
//    public int getScoreFromServer(){};- no need for now-getting score from location func.
        // the flow of connection:
        // pre game- all players get from gameServer 7 random tiles from bag
        // a player want to locate word-> sent word to gameServer
        //gameServer returns valid/not valid
        //if valid- player wants so locate word-> player send location
        //gameServer returns valid/not valid for location of the word.
        // if location is valid gameServer returns the score that the player gets after locating this word. if not valid-gameServer return -1.
        // gameServer completes the players tiles to 7 tiles.

    }

