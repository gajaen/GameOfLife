package _Game;

import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.beans.binding.*;



public class Controller {

    public Button startButton;
    public Button stopButton;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Slider cellSlider;
    public double cellSize = 47;

    //Nyttbrett
    public int w = 10;
    int columns, rows;

    int[][] board;

    //Variabler til Spillebrettet
    public double distanceCells = -1;
    public int canvasBorder = 7;


    public byte[][] boardCell = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    };

    public byte[][] boardClean = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };


    public void initialize() {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        int width = 100;
        int height = 100;

        columns = width/w;
        rows = height/w;
        board = new int[columns][rows];


        cellSlider.setMin(1);
        cellSlider.setMax(50);
        cellSlider.setValue(20);

        gc.setFill(Color.GREY);
        for (int i = 0; i < boardClean.length; i++) {
            for (int j = 0; j < boardClean[0].length; j++) {
                if (boardClean[i][j] == 1)
                    gc.fillRect(cellSize * j + canvasBorder, cellSize * i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }
        }
    }


    public void nextGeneration() {

    }

    public void draw(){

    }


    public void clickedStartButton() {
        //Fjerner alle celler før ny randomisering
        gc.setFill(Color.GREY);
        for (int i = 0; i < boardClean.length; i++) {
            for (int j = 0; j < boardClean[0].length; j++) {
                if (boardClean[i][j] == 1) gc.fillRect(cellSize * j + canvasBorder, cellSize * i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }
        }

        //Lager en ny random array for hver gang start er trykket.
        for (int i =1;i < columns;i++) {
            for (int j =1;j < rows;j++) {
                board[i][j] = (int)(Math.random()*2);
            }
        }

        //Tenger det nye brettet
        System.out.println("You pressed START");
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.LIMEGREEN);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if( board[i][j] == 1) gc.fillRect(cellSize*j + canvasBorder, cellSize*i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }

        }
    }

    public void clickedStopButton() {

        System.out.println("You pressed STOP");
        gc.setFill(Color.GREY);
        for (int i = 0; i < boardClean.length; i++) {
            for (int j = 0; j < boardClean[0].length; j++) {
                if (boardClean[i][j] == 1) gc.fillRect(cellSize * j + canvasBorder, cellSize * i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }
        }
    }



}






