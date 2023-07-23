package com.example.clientside.view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class BoardViewController implements Observer, Initializable {


    @FXML
    BoardView boardView;
    @FXML
    TilesView tilesView;


    public String[][] boardData;
    public String[] tilesArray;
    public String[][] boardTiles;

    public BoardViewController() {
        boardData = new String[][]{
                {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15"},
                {"01","1", "0", "0", "3", "0", "0", "0", "1", "0", "0", "0", "3", "0", "0", "1"},
                {"02","0", "4", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "4", "0"},
                {"03","0", "0", "4", "0", "0", "0", "3", "0", "3", "0", "0", "0", "4", "0", "0"},
                {"04","3", "0", "0", "4", "0", "0", "0", "3", "0", "0", "0", "4", "0", "0", "3"},
                {"05","0", "0", "0", "0", "4", "0", "0", "0", "0", "0", "4", "0", "0", "0", "0"},
                {"06","0", "0", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "0", "0"},
                {"07","0", "0", "0", "0", "0", "0", "3", "0", "3", "0", "0", "0", "0", "0", "0"},
                {"08","1", "0", "0", "3", "0", "0", "0", "5", "0", "0", "0", "3", "0", "0", "1"},
                {"09","0", "0", "0", "0", "0", "0", "3", "0", "3", "0", "0", "0", "0", "0", "0"},
                {"10","0", "0", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "0", "0"},
                {"11","0", "0", "0", "0", "4", "0", "0", "0", "0", "0", "4", "0", "0", "0", "0"},
                {"12","3", "0", "0", "4", "0", "0", "0", "3", "0", "0", "0", "4", "0", "0", "3"},
                {"13","0", "0", "4", "0", "0", "0", "3", "0", "3", "0", "0", "0", "4", "0", "0"},
                {"14","0", "4", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "4", "0"},
                {"15","1", "0", "0", "3", "0", "0", "0", "1", "0", "0", "0", "3", "0", "0", "1"},
        };
        boardTiles=new String[][]{
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
                {"n","n","n","n","n","n","n","n","n","n","n","n","n","n","n","n"},
        };
        tilesArray = new String[]{"a"};
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setBoard(boardData,boardTiles);
        tilesView.setTiles(tilesArray);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Update logic here
    }

}
