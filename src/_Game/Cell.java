package _Game;


import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Cell {
    private int cellSize;
    private int cellGap;
    private int FPS;
    public Color cellColor;
    public Color newColor;
    public Controller controller;
    public CanvasFrame canvasFrame;

    public Cell(){

        cellSize = 10;
        cellGap = 1;
        cellColor = Color.WHITE;
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
        if (getCellSize() < 2){
            cellGap = 0;
        }
        else{
            cellGap = 1;
        }
        return cellGap;
    }

    public void setCellGap(int cellGap) {
        this.cellGap = cellGap;
    }




}
