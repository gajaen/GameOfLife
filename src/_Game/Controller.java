package _Game;

import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;

public class Controller {
    public Canvas CanvasId;
    public Slider cellSlider;
    public GraphicsContext gc;
    public Button startButton, stopButton, circleButton;
    public int columns, rows, canvasBorder, distanceCells, cellSize;
    public byte[][] board, cleanBoard;


    public byte[][] boardCircle = {
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

    public Controller()
    {
        //Variabler for spillbrettet
        cellSize = 10;
        columns = 50;
        rows = 50;
        canvasBorder = 0;
        distanceCells = -1;
    }


    public void cleanBoard()
    {
        //Fjerner alle celler
        gc.setFill(Color.GREY);
        for (int i = 0; i < cleanBoard.length; i++) {
                for (int j = 0; j < cleanBoard[0].length; j++) {
                    if (cleanBoard[i][j] == 1)
                        gc.fillRect(cellSize * j + canvasBorder, cellSize * i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
                }
            }
    }

    public void initialize() {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        board = new byte[columns][rows];
        cleanBoard = new byte[columns][rows];


        cellSlider.setMin(1);
        cellSlider.setMax(50);
        cellSlider.setValue(20);

        //Starter spillet med å med å lage et brett
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                cleanBoard[i][j] = (1);
            }
        }
        cleanBoard();
    }


    public  void nextGeneration() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)

                if (board[i][j] == 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            cleanBoard();
            draw();

        }
    }


    public void draw()
    {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.LIMEGREEN);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if( board[i][j] == 1) gc.fillRect(cellSize*j + canvasBorder, cellSize*i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }
        }
    }

    public void clickedStartButton()
    {
        System.out.println("You Clicked RANDOM");
        cleanBoard();

        //Lager en ny random array for hver gang start er trykket.
        for (int i =0;i < columns;i++) {
            for (int j =0;j < rows;j++) {
                board[i][j] = (byte)(Math.random()*2);
            }
        }
        draw();
    }

    public void clickedStopButton()
    {
        System.out.println("You Clicked CLEAR");
        nextGeneration();
    }

    public void clickedCircleButton()
    {
        System.out.println("You Clicked CIRCLE");

        cleanBoard();

        gc.setFill(Color.LIMEGREEN);
        for (int i = 0; i < boardCircle.length; i++) {
            for (int j = 0; j < boardCircle[0].length; j++) {
                if( boardCircle[i][j] == 1) gc.fillRect(cellSize*j + canvasBorder, cellSize*i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }

        }
    }
}






