/**
 * The Game Of Life program created for HIOA final project
 * The Board class creates arrays for the board.
 * it does also drawing each cell and line.
 *
 * @author  Sivert Allergodt Borgeteien & Gajaen Chandrasegaram
 * Studentnr : S315325 & S315285
 * @version 1.0
 * @since   2017-01-14
 */

package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;


public class StaticBoard {

    private Cell cell;
    private Controller controller;
    private CanvasFrame canvasFrame;
    private DynamicBoard dynamicBoard;
    byte[][] board;
    int canvasWidth;
    int canvasHeight;
    int oldJ;
    int oldI;

    /**
     *
     *  Constructs and initializes a board with array, width and height
     *
     *  @param boardArray creating a array for Board
     *  @param canWidth canvas width
     *  @param canHeight canvas height
     *  @return nothing.
     */


    public StaticBoard(byte[][] boardArray, int canWidth, int canHeight) {

        this.board = boardArray;
        this.canvasHeight = canHeight;
        this.canvasWidth = canWidth;
        this.cell = new Cell();

    }


    /**
     * This method is used to make a new board with rules.
     *
     * @return Nothing.
     */


    public void nextGeneration() {

        byte[][] nextBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += board[x + i][y + j];
                    }
                }

                cellNeighbors -= getBoard()[x][y];
                if ((board[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((board[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((board[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = board[x][y];
            }
        }

        board = nextBoard;
    }

    /**
     * This method is used to draw a cell on canvas.
     *
     * @return Nothing.
     *
     */


    public void drawCells(GraphicsContext gc) {


        gc.setFill(cell.getCellColor());

        for (int i = 0; i < this.canvasHeight; i++) {
            for (int j = 0; j < this.canvasWidth; j++) {
                if (board[i][j] == 1) {
                    gc.fillRect(cell.getCellSize() * j - cell.getCellSize(), cell.getCellSize() * i - cell.getCellSize(), cell.getCellSize() - cell.getCellGap(), cell.getCellSize() - cell.getCellGap());
                }
            }
        }
    }

    /**
     * This method is used to draw a vertical and horizontal lines on canvas.
     *
     * @return Nothing.
     * @param gc is the first parameter to draw on canvas
     * @param lineWidth is the second choosing the thickness of line
     * @param lineColor is the third parameter and it is choosing the color of line
     *
     */


    public void drawLines(GraphicsContext gc, double lineWidth, Color lineColor) {

        gc.setStroke(lineColor);
        gc.setLineWidth(3);
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
     * This method is used to zero the numbers in board array.
     *
     * @return Nothing.
     *
     */

    public void cleanArray() {
        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * This method is used to draw the reaadgameboard pattern.
     *
     * @return Nothing.
     * @param pattern is the first parameter and it is the array for the new pattern.
     * @param gc is the second parameter and is used to drawing on the canvas.
     *
     */

    public  void drawPattern(int[][] pattern, GraphicsContext gc)
    {
        for (int row=0; row < pattern.length; row++)
        {
            for (int col=0; col < pattern[row].length; col++)
            {
                if(pattern[row][col] ==1)
                {
                    board[row][col] = 1;

                }
            }
        }

        drawCells(gc);

    }

    /**
     * This method is used to draw when clicked on canvas.
     *
     * @return Nothing.
     * @exception Exception On input error.
     * @see Exception
     */

    public void CanvasPressed(MouseEvent a) throws Exception {

    try {

    int j = (int)(a.getX() / cell.getCellSize()) + 1;
    int i = (int)(a.getY() / cell.getCellSize()) + 1;

    if (i <= 0) {
        return;
    }
    if (j <= 0) {
        return;
    }

    if (j != oldJ || i != oldI) {
        if (board[i][j] == 0) {
            board[i][j] = 1;
        }
    }

    oldJ = j;
    oldI = i;
    }

    catch (Exception e){
    //System.err.println(" Exeption: " + e.getMessage());
    System.out.println("Task interrupted");

}

    }

    /**
     * This method is used to set a random board.
     *
     * @param i this is the first parameter for setBoardRandom method
     * @param j this is the second parameter for setBoardRandom method
     * @return int this returns a random array multiplied with 2.
     */

    public int setBoardRandom(int i, int j) {

        return  board[i][j] = (byte) (Math.random() * 2);

    }

    /**
     * This method is used to change cell color.
     *
     * @param color this parameter changes the cell color in cell class
     * @return Nothing.
     */

    public void setCellColor(Color color){

        this.cell.setCellColor(color);

    }

    /**
     * This method is used to change cell size.
     *
     * @param size this parameter changes the cell size
     * @return Nothing.
     */

    public void setCellSize(double size){
        this.cell.setCellSize(size);
    }

    /**
     * This method is used to assign a new board.
     *
     * @param board this parameter is used to change board
     * @return Nothing.
     *
     */

    public void setBoard(byte[][] board) {
        this.board = board;
    }

    /**
     * This method is used to get current board.
     *
     * @return int this returns current board.
     */

    public byte[][] getBoard() {
        return board;
    }



    public void moveCellsUp(){

        byte[][] leftBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((board[x][y] == 1))leftBoard[x-1][y] = 1;
            }
    }
    board = leftBoard;
    }
    public void moveCellsLeft(){
        byte[][] leftBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((board[x][y] == 1))leftBoard[x][y-1] = 1;

            }
        }
        board = leftBoard;
    }
    public void moveCellsDown(){
        byte[][] leftBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 0; x < canvasHeight; x++) {
            for (int y = 0; y < canvasWidth; y++){
                if ((board[x][y] == 1))leftBoard[x+1][y] = 1;
            }
        }
        board = leftBoard;
    }
    public void moveCellsRight(){
        byte[][] leftBoard = new byte[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++){
                if ((board[x][y] == 1))leftBoard[x][y+1] = 1;
            }
        }
        board = leftBoard;
    }




}
