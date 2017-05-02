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
    public ArrayList[][] dBoard;

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


    }

    public void cleanArray() {



        ArrayList<ArrayList<Byte>> dBoard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Byte> inner = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                inner.add((byte) 0);
            dBoard.add(inner);
        }
        dBoard.get(1).set(1, (byte) 1); // replace fordi Integer er immutable

        dBoard.forEach((l) -> System.out.println(l));
    }


    public void cleanArrayTest() {
        int x = canvasHeight;
        int y = canvasWidth;
        ArrayList<ArrayList<Byte>> cleanArray = new ArrayList<ArrayList<Byte>>();
        for (int i = 0; i < x; i++) {
            ArrayList<Byte> rowArrayList = new ArrayList<Byte>();
            for (int j = 0; j < y; j++) {
                rowArrayList.add((byte) 0);
            }
            cleanArray.add(rowArrayList);
        }
        cleanArray.forEach((l) -> System.out.println(l));
    }

    public void cleanArrayTest2() {

        System.out.println(" ");

        int x = 10;
        int y = 10;
        ArrayList[][] cleanArray = new ArrayList[x][y];
        cleanArray[0][0] = new ArrayList();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cleanArray[i][j] = new ArrayList();
                cleanArray[i][j].add((byte)0);
            }
        }
        for (ArrayList[] inner : cleanArray)
            System.out.println(Arrays.toString(inner));

        dBoard = cleanArray;

    }
    public void randomBoard() {

        System.out.println(" ");

        int x = 10;
        int y = 10;
        ArrayList[][] randomBoard = new ArrayList[x][y];
        randomBoard[0][0] = new ArrayList();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                randomBoard[i][j] = new ArrayList();
                randomBoard[i][j].add((byte) (Math.random() * 2));
            }
        }
        for (ArrayList[] inner : randomBoard)
            System.out.println(Arrays.toString(inner));

        if (randomBoard[1][1].equals(1)) {
                    System.out.println("2,2 is true");
                }
    }






    /**
     * This method is used to make a new board with rules.
     *
     * @return Nothing.
     */
    public void nextGeneration() {
/*
        ArrayList[][] nextBoard = new ArrayList[canvasHeight][canvasWidth];

        for (int x = 1; x < canvasHeight - 1; x++) {
            for (int y = 1; y < canvasWidth - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += sBoard[x + i][y + j];
                    }
                }

                cellNeighbors -= sBoard[x][y];
                if ((sBoard[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((sBoard[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((sBoard[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = sBoard[x][y];
            }
        }

        sBoard = nextBoard;
        */

    }

    public ArrayList[][] getdBoard() {
        return dBoard;
    }

    public void setdBoard() {
        //this.dBoard = dBoard;
    }


}




