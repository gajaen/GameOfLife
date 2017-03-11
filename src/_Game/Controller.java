package _Game;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.text.View;
import java.awt.*;

public class Controller implements Stroke{
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
        columns = 150;
        rows = 150;
        canvasBorder = 1;
        distanceCells = -1;

    }
/*
    public void cleanBoard()
    {


        //Farger alle celler grå
       gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
       gc.setFill(Color.GREY);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.stroke();
        for (int i = 0; i < cleanBoard.length; i++) {
                for (int j = 0; j < cleanBoard.length; j++) {
                    if (cleanBoard[i][j] == 1)
                        gc.fillRect(cellSize * j + canvasBorder, cellSize * i + canvasBorder, cellSize + distanceCells, cellSize + distanceCells);
                }
            }
    }
*/
    public void cleanBoard(){
        gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());



         /*   for (int j = 0; j > CanvasId.(); j++) {
                gc.strokeRect(10, 10, 10, 10);
                gc.stroke();
            }*/


    }


    public void initialize()
    {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
//        gc.strokeRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        board = new byte[columns][rows];
        cleanBoard = new byte[columns][rows];

   //     gc.fillRect();

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

    public void clickedClearButton() throws InterruptedException {
        System.out.println("You Clicked CLEAR");
        initialize();
        timeline.stop();
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
            // Start Animasjon
        timeline.playFromStart();
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
        timeline.stop();
    }


    private Timeline timeline;

    {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            nextGeneration();
            draw();
            timeline.playFromStart();

        }));
    }

    @Override
    public Shape createStrokedShape(Shape pixel) {
        createStrokedShape(pixel);
        return pixel;
    }
}






