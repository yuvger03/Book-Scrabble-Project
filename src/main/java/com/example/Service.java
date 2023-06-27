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
public String[][] StringTOString2DAraay(String s1 ,int row,int col) {
    String[][] array = new String[row][col];
    int index = 0;
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++)
            array[i][j] = String.valueOf(s1.charAt(index));
        index++;
    }
    return array;
}
    public Word stringToWord(String wordString) {
        int length = wordString.length();
        String[] array = wordString.split(",");
        String wordText=array[0];
        int score;
        Tile[] tiles = new Tile[wordText.length()];
        for (int i = 0; i < wordText.length(); i++) {
            score = calculateScore(wordText.charAt(i));
            tiles[i] = new Tile(wordText.charAt(i), score);
        }
        int row = Integer.parseInt(array[1]);
        int col=Integer.parseInt(array[2]);
        //int row = Character.getNumericValue(wordString.charAt(length - 4));
        //int col = Character.getNumericValue(wordString.charAt(length - 3));
        boolean vertical;
        if(array[3].equals("F"))
            vertical = false;
        else vertical=true;
        //String wordText = wordString.substring(0, length - 4);

        Word word = new Word(tiles,row, col, vertical);
        return word;
    }

    public String matrixToString(Tile [][] t) {
        StringBuilder sb = new StringBuilder();
        //Tile [][] t = board.getTiles();
        for (int row = 0; row < t.length; row++) {
            for (int col = 0; col < t[row].length; col++) {
                if (t[row][col] != null) {
                    sb.append(t[row][col].letter);
                } else {
                    sb.append("n");
                }
            }
        }
        return sb.toString();
    }

    public Tile[][] stringToMatrix(String input) {
        if (input.length() != 225) {
            throw new IllegalArgumentException("Invalid input string length. Expected length: 225");
        }

        Tile[][] matrix = new Tile[15][15];
        int index = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(input.charAt(index)!='n') {
                    matrix[row][col].letter = input.charAt(index++);
                    matrix[row][col].score = input.charAt(index++);
                }
                else {
                    matrix[row][col]=null;
                }
            }
        }
        return matrix;
    }


    private int calculateScore(char c) {
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

    public String TileToString(Tile tile) { //make obj WORD to string- "word,row,col,vertical"
        return String.valueOf(tile.letter);
    }

    public Tile stringToTile(String s) {
        char c = s.charAt(0);
        Tile t = new Tile(c, calculateScore(c));
        return t;
    }

    public Tile[] StringToTilesArray(String missingTiles) {
        return stringToWord(missingTiles).getTiles();
    }


    public String TilessArrayToSTring(ArrayList<Tile> pTiles) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<pTiles.size();i++) {
            stringBuilder.append(pTiles.get(i).letter);
        }
        return stringBuilder.toString();
    }

}
