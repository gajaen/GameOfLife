package _Game;

import javafx.scene.paint.Color;

/**
 * The Game Of Life program created for HIOA final project
 * The cell class have all the variables of the cell property.
 *
 * @version 1.0
 * @since   2017-01-14
 * @author  S315325 & S315285
 */

public class Cell {

    private Color cellColor;
    private double cellSize;
    private int cellGap;

    /**
     *  Constructs and initializes cellSize, cellGap and cellColor
     */

    public Cell(){
        cellSize  = 10;
        cellGap   = 1;
        cellColor = Color.WHITE;
    }

    /**
     * Sets the cell Color
     * @param cellColor
     */

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    /**
     * @return cellsize
     */

    public double getCellSize() {
        return cellSize;
    }

    /**
     * @return cellColor
     */

    public Color getCellColor() {
        return cellColor;
    }

    /**
     * Sets CellSize
     * @param cellSize
     */

    public void setCellSize(double cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * @return cellGap
     */

    public int getCellGap() {
        if (getCellSize() < 2){
            cellGap = 0;}
        else{
            cellGap = 1;}
        return cellGap;
    }
}
