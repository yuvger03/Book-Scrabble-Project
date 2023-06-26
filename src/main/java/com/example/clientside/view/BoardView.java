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
    double W = getWidth();
    double H = getHeight();
    double w = W/board[0].length;
    double h = H/board.length;
    GraphicsContext gc = getGraphicsContext2D();
    public BoardView(){

    }

    public void setBoard(String [][] board){
        this.board = board;
        this.gc = gc;
        this.W = W;
        this.H = H;
        this.w = w;
        this.h = h;
        reDraw();

    }
    public void newTile(double boardWidth, double h, int i, int j,String text,boolean vertical){
        if (vertical)
            for(char c: text.toCharArray()){
                fillText(this.gc,boardWidth,h,i,j,text);
                i++;
        }
        else
            for(char c: text.toCharArray()){
                fillText(this.gc,boardWidth,h,i,j,text);
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
