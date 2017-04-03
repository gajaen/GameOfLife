package _Game;


import javafx.scene.paint.Color;

public class Cell {
    private int cellSize;
    private int cellGap;
    private Color cellColor;

    public void drawCells() {

        //cleanBoard();
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(getCellColor());


        for (int i = 0; i < getHEIGHT(); i++) {
            for (int j = 0; j < getWIDTH(); j++) {
                if (Board.getBoard()[i][j] == 1) {
                    gc.fillRect(getCellSize() * j - getCellSize(), getCellSize() * i - getCellSize(), getCellSize() - getCellGap(), getCellSize() - getCellGap());
                }
            }
        }
    }


    public Color getCellColor() {
        return cellColor;
    }

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getCellGap() {
        return cellGap;
    }

    public void setCellGap(int cellGap) {
        this.cellGap = cellGap;
    }




}
