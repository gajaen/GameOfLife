package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class DynamicBoard{

    private Cell cell;
    private int canvasHeight;
    private int canvasWidth;
    private int  oldJ ;
    private int  oldI ;
    public List<List<Byte>> dynamicBoard;


    /**
     *  Constructs and initializes a board with height, width and array
     *
     *  @param width canvas width
     *  @param height canvas height
     *  @param dynamic creating a array for Board
     *  @return nothing.
     *
     */

    public DynamicBoard(int height, int width, List<List<Byte>> dynamic)  {

        this.dynamicBoard = dynamic;
        this.canvasHeight = height;
        this.canvasWidth  = width;
        this.cell         = new Cell();
        fillBoard(dynamicBoard);
    }

    /**
     * Filling array list
     *
     * @param  board
     *
     */

    public void fillBoard(List<List<Byte>> board){

        for(int x = 0; x < canvasHeight; x++){
            List<Byte> row = new ArrayList<Byte>();
            for(int y = 0; y < canvasWidth; y++){
                row.add((byte)0);
            }
            board.add(row);
        }
    }


    /**
     * This method is used to draw a cell on canvas.
     *
     * @param gc for draw on canvas
     *
     */

    public void drawCells(GraphicsContext gc) {

        gc.setFill(cell.getCellColor());
        for (int i = 0; i <  dynamicBoard.size(); i++) {

            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {

                if (dynamicBoard.get(i).get(j) == 1) {

                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
    }

    /**
     * This method is used to draw a vertical and horizontal lines on canvas.
     *
     * @param gc is the first parameter to draw on canvas
     * @param lineWidth is the second choosing the thickness of line
     * @param lineColor is the third parameter and it is choosing the color of line
     *
     */

    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, this.canvasWidth, this.canvasHeight);

        gc.setLineWidth(lineWidth);

        double a = cell.getCellSize();
        double b = cell.getCellSize();

        for (int i = 0; i < this.canvasHeight; i++) {
            gc.strokeLine(0, a, this.canvasWidth, a);
            a += cell.getCellSize();
        }
        for (int i = 0; i < this.canvasWidth; i++) {
            gc.strokeLine(b, 0, b, this.canvasHeight);
            b += cell.getCellSize();
        }
    }

    /**
     * This method is used to make a new board with rules.
     *
     */
    public void nextGeneration() {
        List<List<Byte>> nextBoard =  new ArrayList<List<Byte>>();

        for(int i = 0; i < canvasHeight; i++) {
            List<Byte> inner = new ArrayList<Byte>();
            for (int j = 0; j < canvasWidth; j++)
                inner.add((byte) 0);
            nextBoard.add(inner);
        }

        for (int x = 1; x < dynamicBoard.size(); x++) {
            for (int y = 1; y < dynamicBoard.get(x).size(); y++) {
                int cellNeighbors = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(x+i>=0 && y+j >=0) {
                            if (x+i < dynamicBoard.size() && y+j < dynamicBoard.get(x).size()) {
                                cellNeighbors += dynamicBoard.get(x + i).get(y + j);
                            }
                        }
                    }
                }

                cellNeighbors -= dynamicBoard.get(x).get(y);
                //Mindre en 2 rundt -> cellen dør
                //Less than 2 Neighbours -
                if ((dynamicBoard.get(x).get(y) == 1) && (cellNeighbors < 2))
                    nextBoard.get(x).set(y, (byte) 0);

                else if ((dynamicBoard.get(x).get(y)  == 1) && (cellNeighbors > 3))
                    nextBoard.get(x).set(y, (byte) 0);
                    //Fler en tre rundt -> cellen dør

                else if ((dynamicBoard.get(x).get(y)  == 0) && (cellNeighbors == 3))
                    nextBoard.get(x).set(y, (byte) 1);
                    //Cellen er lik 3 -> cellen kommer til live

                else {
                    //Cellen lever
                    nextBoard.get(x).set(y, dynamicBoard.get(x).get(y));
                    //dynamicBoard.get(x).set(y, nextBoard.get(x).get(y));
                }
            }
        }
        dynamicBoard = nextBoard;

    }

    /**
     * This method is used to zero the numbers in board array.
     *
     */

    public void cleanArray() {
        for (int i = 0; i < dynamicBoard.size(); i++) {
            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {
                dynamicBoard.get(i).set(j, (byte) 0);
            }
        }
    }

    /**
     * This method is used to draw the reaadGameBoard pattern.
     *
     * @param pattern is the first parameter and it is the array for the new pattern.
     * @param gc is the second parameter and is used to drawing on the canvas.
     *
     */

    public  void drawPattern(int[][] pattern, GraphicsContext gc) {
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                if (pattern[row][col] == 1) {
                    dynamicBoard.get(row).set(col, (byte) 1);
                }
            }
        }
    }


    /**
     * This method is used to draw the make a random board by using math random.
     *
     */

    public void randomButton(){
        for (int i = 0; i < dynamicBoard.size(); i++) {
            for (int j = 0; j < dynamicBoard.get(i).size(); j++) {
                dynamicBoard.get(i).set(j, (byte) (Math.random() * 2));
            }
        }
    }

    /**
     * Filling array list with 1 when clicked or 0 when filled.
     *
     * @param mouseEvent getting points from mouse click
     */

    public void CanvasPressed(MouseEvent mouseEvent)  {
        try {

            int j = (int)(mouseEvent.getX() / cell.getCellSize()) + 1;
            int i = (int)(mouseEvent.getY() / cell.getCellSize()) + 1;

            if (i <= 0) {
                return;
            }
            if (j <= 0) {
                return;
            }
            if (j != oldJ || i != oldI) {
                if (dynamicBoard.get(i).get(j) == 0){
                    dynamicBoard.get(i).set(j,(byte)1);
                }
                else{ dynamicBoard.get(i).set(j,(byte)0);
                }
            }
            oldJ = j;
            oldI = i;
        }

        catch (Exception e){
        }}

    /**
     * Moves the whole array list to right
     *
     */

    public void moveCellsRight(){
        List<List<Byte>> rightBoard =  new ArrayList<List<Byte>>();
        fillBoard(rightBoard);
        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){
                if (dynamicBoard.get(x).get(y) == 1){
                    rightBoard.get(x).set(y + 1,(byte)1);
                }
            }
        }
        dynamicBoard = rightBoard;
    }

    /**
     * Moves the whole array list to left
     *
     */

    public void moveCellsLeft() {
        List<List<Byte>> leftBoard = new ArrayList<List<Byte>>();
        fillBoard(leftBoard);
        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){
                if (dynamicBoard.get(x).get(y) == 1){
                    leftBoard.get(x).set(y - 1,(byte)1);
                }
            }

        }
        dynamicBoard = leftBoard;
    }


    /**
     * Moves the whole array list up
     *
     */

    public void moveCellsUp(){

        List<List<Byte>> upBoard =  new ArrayList<List<Byte>>();
        fillBoard(upBoard);
        for (int x = 0; x < dynamicBoard.size(); x++) {
            for (int y = 0; y < dynamicBoard.get(x).size(); y++){
                if (dynamicBoard.get(x).get(y) == 1){

                    upBoard.get(x).set(y -1,(byte)1);
                }
            }
        }
        dynamicBoard = upBoard;
    }

    /**
     * Moves the whole array list down.
     *
     */

    public void moveCellsDown() {
        List<List<Byte>> downBoard = new ArrayList<List<Byte>>();
        fillBoard(downBoard);

        for (int x = 1; x < dynamicBoard.size(); x++) {
            for (int y = 1; y < dynamicBoard.get(x).size(); y++){

                if (dynamicBoard.get(y).get(x) == 1) {
                    downBoard.get(x).set(y - 1, (byte) 1);
                }
            }
            dynamicBoard = downBoard;
        }
    }


    public void setCellSize(double size){
        this.cell.setCellSize(size);
    }


    public void setCellColor(Color color){
        this.cell.setCellColor(color);
    }

}




