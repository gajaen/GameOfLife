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

import java.util.*;


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
     * Constructs and initializes a board with array, width and height
     *
     * @param boardArray creating a array for Board
     * @param canWidth   canvas width
     * @param canHeight  canvas height
     * @return nothing.
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

                cellNeighbors -= getsBoard()[x][y];
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


    /**
     * This method is used to set a random board.
     *
     * @param i this is the first parameter for setBoardRandom method
     * @param j this is the second parameter for setBoardRandom method
     * @return int this returns a random array multiplied with 2.
     */

    public int setBoardRandom(int i, int j) {

        return board[i][j] = (byte) (Math.random() * 2);

    }


    public void setsBoard(byte[][] board) {
        this.board = board;
    }

    /**
     * This method is used to get current board.
     *
     * @return int this returns current board.
     */

    public byte[][] getsBoard() {
        return board;
    }


    /**
     * This method is used to zero the numbers in board array.
     *
     * @return Nothing.
     */

    public void cleanArray() {
        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                board[i][j] = 0;
            }
        }
    }
}




