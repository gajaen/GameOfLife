package _Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class DynamicBoard {

    private StaticBoard staticBoard;
    private Controller controller;
    private CanvasFrame canvasFrame;
    private Cell cell;
    private int canvasHeight;
    private int canvasWidth;
    private byte[][] sBoard;
    private List<List<Byte>> dBoard;

    public DynamicBoard(int height, int width, byte[][] board) {

        this.canvasHeight = height;
        this.canvasWidth = width;
        this.sBoard = board;
        this.cell = new Cell();

        //byte[][] sBoard = new staticBoard.getBoard();
    }

    public void DynamicTest() {



        /*System.out.println(sBoard);
        int x = 10;
        int y = 10;

        int x1 = 10; //Testtall
        int y1 = 10; //Testtall

        for (int i = 0; i < x; i++) {
            List<Byte> inner = new ArrayList<Byte>();
            for (int j = 0; j < y; j++)
                inner.add((byte)0);
            dBoard.add(inner);
        }

        dBoard.get(1).set(1, (byte)1); // replace fordi Integer er immutable

        System.out.println("\nDynamisk 2D tabell:");
        dBoard.forEach((l) -> System.out.println(l));

        dBoard.get(2).add((byte)1);
        System.out.println("\nJagged 2D tabell:");
        dBoard.forEach((l) -> System.out.println(l));
        */
        cleanArray();
        testCleanArray();

    }

    public void cleanArray() {
        List<List<Byte>> dBoard = new ArrayList<List<Byte>>();
        for (int i = 0; i < 10; i++) {
            List<Byte> inner = new ArrayList<Byte>();
            for (int j = 0; j < 10; j++)
                inner.add((byte)0);
            dBoard.add(inner);
        }
        dBoard.get(1).set(1, (byte)1); // replace fordi Integer er immutable

        System.out.println("\nDynamisk 2D tabell:");
        dBoard.forEach((l) -> System.out.println(l));
    }


    public void testCleanArray(){
        /*int x = 10;
        int y = 10;
        ArrayList[][] tBoard = new ArrayList[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tBoard[i][j].add((byte)0);
            }
        }
        */




    }

    /**
     * This method is used to make a new board with rules.
     *
     * @return Nothing.
     */


    public void nextGeneration() {

       /*
        ArrayList[][] nextdBoard = new ArrayList[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += dBoard[x + i][y + j];
                    }
                }

                cellNeighbors -= dBoard[x][y];
                if ((dBoard[x][y] == 1) && (cellNeighbors < 2)) nextdBoard[x][y].add((byte)0);           // Mindre enn 2 rundt
                else if ((dBoard[x][y] == 1)) && (cellNeighbors > 3)) nextdBoard[x][y].add((byte)0);           // Fler enn 3 rundt seg
                else if ((dBoard[x][y] == 0) && (cellNeighbors == 3)) nextdBoard[x][y].add((byte)0);           // Akkurat 3 rundt seg
                else nextdBoard[x][y] = dBoard[x][y];
            }
        }

        dBoard = nextdBoard;
        */

    }


    public List<List<Byte>> getdBoard() {
        return dBoard;
    }

    public void setdBoard(List<List<Byte>> dBoard) {
        this.dBoard = dBoard;
    }
}




