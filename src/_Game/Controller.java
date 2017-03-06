package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.util.Duration;

public class Controller {
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Button startButton, stopButton, circleButton, randomButton, clearButton;
    public int columns, rows, canvasBorder, distanceCells, cellSize;
    public byte[][] board, cleanBoard;
    public byte[][] circleBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public Controller()
    {
        //Variabler for spillbrettet
        cellSize = 10;
        columns = 50;
        rows = 50;
        canvasBorder = 1;
        distanceCells = -1;
    }

    public void cleanBoard()
    {
        //Farger alle celler grå
        gc.setFill(Color.GREY);
        for (int i = 0; i < cleanBoard.length; i++) {
                for (int j = 0; j < cleanBoard.length; j++) {
                    if (cleanBoard[i][j] == 1)
                        gc.fillRect(cellSize * j + canvasBorder, cellSize * i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
                }
            }
    }

    public void initialize()
    {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        board = new byte[columns][rows];
        cleanBoard = new byte[columns][rows];

        //Starter spillet med å med å lage et brett
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                cleanBoard[i][j] = (1);
            }
        }
        cleanBoard();
    }

    public  void nextGeneration()
    {
        //Enkel løkke som gjøre 0 til 1, og 1 til 0.
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
            for (int j = 0; j < board.length; j++) {
                if( board[i][j] == 1) gc.fillRect(cellSize*j + canvasBorder, cellSize*i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
            }
        }
    }

    public void clickedRandomButton()
    {
        System.out.println("You Clicked RANDOM");

        initialize();

        //Lager en ny random array for hver gang start er trykket.
        for (int i =0;i < board.length;i++) {
            for (int j =0;j < board.length;j++) {
                board[i][j] = (byte)(Math.random()*2);
            }
        }
        draw();
    }

    public void clickedClearButton()
    {
        System.out.println("You Clicked CLEAR");
        initialize();
    }

    public void clickedCircleButton()
    {
        initialize();
        System.out.println("You Clicked CIRCLE");
        board = circleBoard;
        draw();
    }

    public void clickedStartButton()
    {
        System.out.println("You Clicked START");
        timeline.play();
        // Start Animasjon
    }

    public void clickedInvertButton()
    {
        System.out.println("You Clicked INVERT");
         nextGeneration();
    }

    public void clickedStopButton()
    {
        System.out.println("You Clicked STOP");
        // stop Animasjon
    }

    private Timeline timeline = new Timeline( new KeyFrame(Duration.millis(1000), e -> {
        nextGeneration();
        draw();
        cleanBoard();

    }));

}






