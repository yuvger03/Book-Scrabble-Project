package com.example.clientside.view;
import com.example.Game.Board;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class BoardView extends Canvas {
    String [][] board;

    public BoardView(){
    }

    public void setBoard(String [][] board){
        this.board = board;
        reDraw();

    }
    private void fillGC(GraphicsContext gc, double w, double h, int i, int j, Paint paint){
        gc.setFill(paint); // Set the fill color to green
        gc.fillRect(j*w,i*h,w,h);
    }
    private void reDraw() {
        if(board == null) {
            System.out.println("null");
            return;
        }
        else{
            double W = getWidth();
            double H = getHeight();
            System.out.println("not null");
            System.out.println("W: " + W);
            System.out.println("H: " + H);
            double w = W/board[0].length;
            double h = H/board.length;

            GraphicsContext gc = getGraphicsContext2D();
            System.out.println("board.length: " + board.length);
            for (int i =0; i<board.length; i++)
                for (int j =0; j< board[i].length; j++){
                    switch (board[i][j]) {
                        case "0":
                            fillGC(gc, w, h, i, j, Color.WHITE);
                            break;
                        case "1":
                            fillGC(gc, w, h, i, j, Color.LIGHTBLUE);
                            break;
                        case "2":
                            fillGC(gc, w, h, i, j, Color.DARKBLUE);
                            break;
                        case "3":
                            fillGC(gc, w, h, i, j, Color.GREEN);
                            break;
                        case "4":
                            fillGC(gc, w, h, i, j, Color.LIGHTPINK);
                            break;
                        default:
                            fillGC(gc, w, h, i, j, Color.PURPLE);
                            gc.setFill(Color.BLACK);
                            gc.fillText(board[i][j], j * w + (w / 2), i * h + (h / 2));
                        }
                    }

            }
        }
    }
