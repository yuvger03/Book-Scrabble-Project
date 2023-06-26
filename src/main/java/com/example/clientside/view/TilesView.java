package com.example.clientside.view;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



public class TilesView extends Canvas {
    private String[] tiles;
    public final int cellSize = 30;

    public TilesView() {
    }

    public void setTiles(String[] tiles) {
        this.tiles = tiles;
        reDraw();
    }

    private void reDraw() {
        if (tiles != null) {
            double width = getWidth();
            double height = getHeight();
            int rows = (int) Math.ceil((double) tiles.length / (width / cellSize));
            int columns = (int) Math.ceil((double) tiles.length / rows);

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, width, height);

            for (int i = 0; i < tiles.length; i++) {
                int row = i / columns;
                int col = i % columns;

                double rectX = col * cellSize;
                double rectY = row * cellSize;

                gc.setFill(Color.WHITE);
                gc.fillRect(rectX, rectY, cellSize, cellSize);

                gc.setFill(Color.BLACK);
                gc.setFont(new Font(14));
                if (tiles[i] != null)
                    gc.fillText(tiles[i], rectX + 5, rectY + cellSize - 5);
            }
        }
    }
}
