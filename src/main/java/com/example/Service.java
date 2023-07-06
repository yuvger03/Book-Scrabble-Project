package com.example;

import com.example.Game.Tile;
import com.example.Game.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    public String WordToString(Word word) { //make obj WORD to string- "word,row,col,vertical"
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
        sb.append(",").append(row).append(",").append(col);
        sb.append(",").append(direction);
        return sb.toString();
    }

    public String tilesArrToString(ArrayList<Tile> ptiles){
        String s ="";
        for(int i = 0; i< ptiles.size(); i++ )
            s += TileToString(ptiles.get(i));
        return s;
    }

    public Word stringToWord(String wordString) { //word,row-1,col-1,vertical
        int length = wordString.length();
        String[] array = wordString.split(",");
        String wordText=array[0];
        int score;
        Tile[] tiles = new Tile[wordText.length()];
        for (int i = 0; i < wordText.length(); i++) {
            score = calculateScore(wordText.charAt(i));
            tiles[i] = new Tile(wordText.charAt(i), score);
        }
        int row = Integer.parseInt(array[1])-1;
        int col=Integer.parseInt(array[2])-1;
        boolean vertical;
        if(array[3].equals("F"))
            vertical = false;
        else vertical=true;
        Word word = new Word(tiles,row, col,vertical);
        return word;
    }

    public String matrixToString(Tile [][] t) {
        StringBuilder sb = new StringBuilder();
        //Tile [][] t = board.getTiles();
        for (Tile[] tiles : t) {
            for (int col = 0; col < tiles.length; col++) {
                if (tiles[col] != null) {
                    sb.append(tiles[col].letter);
                } else {
                    sb.append("n");
                }
            }
        }
        return sb.toString();
    }

//    public Tile[][] stringToMatrix(String input) {
//        if (input.length() != 225) {
//            throw new IllegalArgumentException("Invalid input string length. Expected length: 225");
//        }
//        Tile[][] matrix = new Tile[15][15];
//        int index = 0;
//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < matrix[row].length; col++) {
//                if(input.charAt(index)!='n') {
//                    matrix[row][col]=new Tile(input.charAt(index),calculateScore(input.charAt(index)));
//                }
//                else {
//                    matrix[row][col]=null;
//                }
//                index++;
//            }
//        }
//        return matrix;
//    }
    public String[][] stringToMatrixS(String input) {
        if (input.length() != 225) {
            throw new IllegalArgumentException("Invalid input string length. Expected length: 225");
        }

        String[][] matrix = new String[15][15];
        int index = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(input.charAt(index)!='n') {
                    matrix[row][col] = ""+input.charAt(index);
                }
                else {
                    matrix[row][col]="n";
                }
                //System.out.println( matrix[row][col]);
                index++;
            }
        }
        return matrix;
    }


    public int calculateScore(char c) {
        {
            switch (Character.toUpperCase(c)) {
                case 'A', 'L', 'E', 'I', 'N', 'O', 'R', 'S', 'T', 'U':
                    return 1;
                case 'B', 'C', 'M', 'P':
                    return 3;
                case 'D', 'G':
                    return 2;
                case 'F', 'W', 'H', 'V', 'Y':
                    return 4;
                case 'J', 'X':
                    return 8;
                case 'K':
                    return 5;
                case 'Q', 'Z':
                    return 10;
            }
            return 0;
        }
    }

    public static boolean validateWord(String word, List<Tile> tiles) {
        // Create a frequency map for tiles characters
        Map<Character, Integer> tileFrequencyMap = new HashMap<>();
        for (Tile tile : tiles) {
            char character = tile.letter;
            tileFrequencyMap.put(character, tileFrequencyMap.getOrDefault(character, 0) + 1);
        }

        // Check if all letters in the word are present in tiles
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!tileFrequencyMap.containsKey(letter) || tileFrequencyMap.get(letter) == 0) {
                return false;
            }
            tileFrequencyMap.put(letter, tileFrequencyMap.get(letter) - 1);
        }

        return true;
    }

    public String TileToString(Tile tile) {
        return String.valueOf(tile.letter);
    }

    public Tile stringToTile(String s) {
        char c = s.charAt(0);
        Tile t = new Tile(c, calculateScore(c));
        return t;
    }

    public Tile[] StringToTilesArray(String tiles) {
        String[] array=tiles.split("");
        Tile[] tilesArray = new Tile[array.length];
        for(int i=0;i<array.length;i++){
            tilesArray[i]=stringToTile(array[i]);
        }
        return tilesArray;
    }
    public String TilessArrayToSTring(ArrayList<Tile> pTiles) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<pTiles.size();i++) {
            stringBuilder.append(pTiles.get(i).letter);
        }
        return stringBuilder.toString();
    }

    public String getWordString(String word) { //get the word string from string in struct word- "word,row,col,vertical"
        String[]s1=word.split(",");
        return s1[0];
    }
}