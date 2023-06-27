package com.example.clientside.view;
import com.example.Game.Board;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;


public class BoardView extends Canvas {
    String [][] board;
    double W;
    double H ;
    double w ;
    String[][] boardsTiles;

    double h ;
    GraphicsContext gc = getGraphicsContext2D();
    public BoardView(){

    }

    public void setBoard(String [][] board){
        this.board = board;
        this.gc = gc;
        this.W = getWidth();
        this.H = getHeight();
        this.w = W/board[0].length;
        this.h = H/board.length;;
        reDraw();

    }
    public void newTile(double boardWidth,double h,int i,int j,boolean vertical,String text){
        if (vertical)
            for(char c: text.toCharArray()){
                fillText(this.gc,boardWidth,h,i,j,""+c);
                i++;
        }
        else
            for(char c: text.toCharArray()){
                fillText(this.gc,boardWidth,h,i,j,""+c);
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
    public void reDrawTilesBoard(String[][]board) {
        if (this.boardsTiles.equals(board))
            return;
        else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 1; j <board[i].length; j++) {
                    if (!board[i][j].equals("n"))
                        fillText(this.gc, w, h, i+1, j+1, board[i][j]);
                }
            }
        }
    }
    private void reDraw() {
        if(board == null)
            return;
        else{
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
                        default: //Add number of rows and columns
                            fillGC(this.gc, w, h, i, j, Color.WHITE);
                            fillText(this.gc, w, h, i, j, board[i][j]);
                    }
                }

        }
    }
}
