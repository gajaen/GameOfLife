package _Game;

import javafx.scene.paint.Color;

/**
 * The Game Of Life program created for HIOA final project
 * The Board class have all the variables of the cell property.
 *
 * @version 1.0
 * @since   2017-01-14
 */

public class Cell {
    private double cellSize;
    private int cellGap;
    public Color cellColor;

    /**
     *  Constructs and initializes cellSize, cellGap and cellColor
     */

    public Cell(){
        cellSize = 10;
        cellGap = 1;
        cellColor = Color.WHITE;
    }

    /**
     * Returns CellColor
     * @return
     */

    public Color getCellColor() {
        return cellColor;
    }

    /**
     * Sets the cell Color
     * @param cellColor
     */


    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    /**
     * Returns CellColor
     * @return
     */

    public double getCellSize() {
        return cellSize;
    }

    /**
     * Set Cell Size
     * @param cellSize
     */

    public void setCellSize(double cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * Returns CellGap
     * @return
     */

    public int getCellGap() {
        if (getCellSize() < 2){
            cellGap = 0;}
        else{
            cellGap = 1;}
        return cellGap;
    }
}
