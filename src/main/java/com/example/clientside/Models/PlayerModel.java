package com.example.clientside.Models;

import com.example.clientside.Game.Board;
import com.example.clientside.Game.Tile;
import com.example.clientside.Game.Word;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;



public class PlayerModel {
    String name = null;
    int score = 0;
    private BoardModel board_game = new BoardModel();
    ArrayList<Tile> p_tiles;
     int serverPort;
    Scanner inFromServer;
    PrintWriter outToServer;

    //TODO: understand how gameServer connection works
    //Server gameServer; each player has a instance of its gameServer.

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Tile> getP_tiles() {
        return p_tiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setP_tiles(ArrayList<Tile> p_tiles) {
        this.p_tiles = p_tiles;
    }

    //TODO:implement- connect the gameServer-to MyServer
    public void connectServer(){
            new Thread(
                    ()->{
                        try{
                            Socket server=new Socket("localhost", serverPort);
                            this.outToServer=new PrintWriter(server.getOutputStream());
                            this.inFromServer=new Scanner(server.getInputStream());
                            //StartGame();
                        }catch (Exception e){}
                    }).start();
        }

    public  int tryToPlace(String tryToPlace ,Word word){
        //String s=word.toString();
        String s=WordToString(word);
        outToServer.println("tryToPlace"+","+s);
        outToServer.flush();
        return Integer.parseInt(inFromServer.next());
    }

    public Tile getTileFromBag(){
        outToServer.println("getTileFromBag,");
        outToServer.flush();
        //TODO- how send tile from server?
        return null;
    }

    //TODO: think of good location
    public String WordToString(Word word) { //make obj WORD to string- "row,col,vertical,word"
        int row=word.getRow();
        int col= word.getCol();
        String direction;
        if(word.isVertical()){
            direction="T";
        }
        else{
            direction="F";
        }
        int word_len = word.getTiles().length;
        String txt[]=new String[word_len];
        Tile tile;
        for(int i=0;i<txt.length;i++) {
            tile = word.getTiles()[i];
            txt[i] = ""+ tile.letter;
        }
        StringBuilder sb = new StringBuilder();
        for (String text : txt) {
            sb.append(text);
        }
        sb.append(row).append(",").append(col);
        sb.append(col).append(",").append(col);
        sb.append(",").append(direction);
        return sb.toString();
    }

    public Word stringToWord(String wordString) {
        int length = wordString.length();
        int col = Character.getNumericValue(wordString.charAt(length - 3));
        int row = Character.getNumericValue(wordString.charAt(length - 4));
        boolean vertical = wordString.charAt(length - 1) != 'F';
        int score;

        String wordText = wordString.substring(0, length - 4);
        Tile[] tiles = new Tile[wordText.length()];
        for (int i = 0; i < wordText.length(); i++) {
            score = calculateScore(wordText.charAt(i));
            tiles[i] = new Tile(wordText.charAt(i), score);
        }

        Word word = new Word(tiles,row, col, vertical);

        return word;
    }

    public String matrixToString(Board board) {
        StringBuilder sb = new StringBuilder();
        Tile [][] t = board.getTiles();
        for (int row = 0; row < t.length; row++) {
            for (int col = 0; col < t[row].length; col++) {
                sb.append(t[row][col].letter);
            }
        }
        return sb.toString();
    }

    public Board stringToMatrix(String input) {
        if (input.length() != 225) {
            throw new IllegalArgumentException("Invalid input string length. Expected length: 225");
        }

        Tile[][] matrix = new Tile[15][15];
        int index = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col].letter = input.charAt(index++);
                matrix[row][col].score = input.charAt(index++);
            }
        }

        return new Board(matrix);
    }


    private int calculateScore(char c) {
        {
            switch (Character.toUpperCase(c)) {
                case 'A':
                    return 1;
                case 'B':
                    return 3;
                case 'C':
                    return 3;
                case 'D':
                    return 2;
                case 'E':
                    return 1;
                case 'F':
                    return 4;
                case 'G':
                    return 2;
                case 'H':
                    return 4;
                case 'I':
                    return 1;
                case 'J':
                    return 8;
                case 'K':
                    return 5;
                case 'L':
                    return 1;
                case 'M':
                    return 3;
                case 'N':
                    return 1;
                case 'O':
                    return 1;
                case 'P':
                    return 3;
                case 'Q':
                    return 10;
                case 'R':
                    return 1;
                case 'S':
                    return 1;
                case 'T':
                    return 1;
                case 'U':
                    return 1;
                case 'V':
                    return 4;
                case 'W':
                    return 4;
                case 'X':
                    return 8;
                case 'Y':
                    return 4;
                case 'Z':
                    return 10;

            }
            return 0;
        }
    }


    public void close() {
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
