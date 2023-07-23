package com.example.clientside.view;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class BoardView extends Canvas {
    String [][] board;
    double W;
    double H ;
    double w ;
    String[][] boardTiles;
    private StringProperty starImage;
    double h ;
    GraphicsContext gc = getGraphicsContext2D();

    public String getStarImage() {
        return starImage.get();
    }
    public void setStarImage(String starImage) {
        this.starImage.set(starImage);
    }
    public BoardView(){
        this.starImage = new SimpleStringProperty();
    }

    public void setBoard(String [][] board,String[][] boardTiles){
        this.boardTiles=boardTiles;
        this.board = board;
        this.W = getWidth();
        this.H = getHeight();
        this.w = W/board[0].length;
        this.h = H/board.length;
        reDraw();

    }
    public void newTile(double boardWidth,double h,int i,int j,boolean vertical,String text){
        if (vertical)
            for(char c: text.toCharArray()){
                fillText(this.gc,boardWidth,h,i,j, String.valueOf(c));
                i++;
            }
        else
            for(char c: text.toCharArray()){
                fillText(this.gc,boardWidth,h,i,j, String.valueOf(c));
                j++;
            }
    }
    private void fillGC(GraphicsContext gc, double w, double h, int i, int j, Paint paint){
        gc.setFill(paint); // Set the fill color to green
        gc.fillRect(j*w,i*h,w,h);
    }
    public void fillText(GraphicsContext gc, double boardWidth, double h, int i, int j,String text){
        gc.setFill(Color.BLACK);
        gc.fillText(text, j * boardWidth + (boardWidth /4), i * h + (2*h / 3));
    }
    public void reDrawTilesBoard(String[][]board1) {
        if (this.boardTiles.equals(board1))
            System.out.println("this.boardTiles.equals(board)");
        else {
            //System.out.println("else this.boardTiles.equals(board)");
            for (int i = 0; i < board1.length; i++) {
                for (int j = 1; j <board1[i].length; j++) {
                    if (!board1[i][j].equals("n")&&(boardTiles[i][j].equals("n"))) {
                        boardTiles[i][j]=board1[i][j];
                        System.out.println("the board cell is -" + board1[i][j]);
                        fillText(this.gc, w, h, i + 1, j + 1, board1[i][j]);
                    }
                }
            }
        }
    }
    private void reDraw() {
        if(board != null) {
            for (int i =0; i<board.length; i++)
                for (int j =0; j< board[i].length; j++){
                    switch (board[i][j]) {
                        case "0":
                            fillGC(this.gc, w, h, i, j, Color.GREEN);
                            break;
                        case "1":
                            fillGC(this.gc, w, h, i, j, Color.RED);
                            break;
                        case "2":
                            fillGC(this.gc, w, h, i, j, Color.DARKBLUE);
                            break;
                        case "3":
                            fillGC(this.gc, w, h, i, j, Color.LIGHTBLUE);
                            break;
                        case "4":
                            fillGC(this.gc, w, h, i, j, Color.YELLOW);
                            break;
                        case "5":
                            Image star=null;
                            try {
                                star=new Image(new FileInputStream(starImage.get()));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            gc.drawImage(star,i*h,j*w,w,h);
                            break;

                        default: //Add number of rows and columns
                            fillGC(this.gc, w, h, i, j, Color.WHITE);
                            fillText(this.gc, w, h, i, j, board[i][j]);
                    }
                }

        }
    }
}