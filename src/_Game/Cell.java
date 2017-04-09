package _Game;


import javafx.scene.paint.Color;

public class Cell {
    private int cellSize;
    private int cellGap;
    private Color cellColor;

    public Cell(){

        cellSize = 12;
        cellGap = 1;
        cellColor = Color.ALICEBLUE;
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
