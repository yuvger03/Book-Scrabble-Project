package com.example.clientside.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class BoardViewController implements Observer, Initializable {


    @FXML
    BoardView boardView;
    @FXML
    TilesView tilesView;
//    @FXML
//    Button doneButton;

    private String[][] boardData;
    private String[] tilesArray;
    private String letterclicked;

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
                {"08","1", "0", "0", "3", "0", "0", "0", "4", "0", "0", "0", "3", "0", "0", "1"},
                {"09","0", "0", "0", "0", "0", "0", "3", "0", "3", "0", "0", "0", "0", "0", "0"},
                {"10","0", "0", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "0", "0"},
                {"11","0", "0", "0", "0", "4", "0", "0", "0", "0", "0", "4", "0", "0", "0", "0"},
                {"12","3", "0", "0", "4", "0", "0", "0", "3", "0", "0", "0", "4", "0", "0", "3"},
                {"13","0", "0", "4", "0", "0", "0", "3", "0", "3", "0", "0", "0", "4", "0", "0"},
                {"14","0", "4", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "4", "0"},
                {"15","1", "0", "0", "3", "0", "0", "0", "1", "0", "0", "0", "3", "0", "0", "1"},
        };

        tilesArray = new String[7];
        letterclicked = "";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boardView.setBoard(boardData);
        boardView.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> boardView.requestFocus());

        boardView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double x, y;

            @Override
            public void handle(MouseEvent mouseEvent) {
                double cellWidth = boardView.getWidth() / boardData[0].length;
                double cellHeight = boardView.getHeight() / boardData.length;

                int row = (int) (mouseEvent.getY() / cellHeight);
                int column = (int) (mouseEvent.getX() / cellWidth);

                System.out.println("Clicked on row: " + row + ", column: " + column);
                if( letterclicked != ""){
                    boardData[row][column] = letterclicked;
                    letterclicked = "";


                }
                boardView.setBoard(boardData);

            }

        });

        tilesView.setTiles(tilesArray);
        tilesView.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> tilesView.requestFocus());

        tilesView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double x, y;

            @Override
            public void handle(MouseEvent mouseEvent) {

                // Calculate the actual cell width based on the available width and number of letters
                double cellWidth = tilesView.cellSize;

                double mouseX = mouseEvent.getX();

                // Calculate the index based on the mouse position and cell width
                int index = (int) (mouseX / cellWidth);

                System.out.println("Clicked on letter at index: " + index);
                letterclicked = tilesArray[index];
            }
        });
//        doneButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> doneButton.requestFocus());

//        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            double x, y;
//
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                checkBoard();
//            }
//
//        });
    }

    @Override
    public void update(Observable o, Object arg) {
        // Update logic here
    }

    public void checkBoard() {
        System.out.println("checkBoard");
        // check that all the words on the board are legal
    }
}

