package _Game;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Controller implements Initializable {
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Button startButton, stopButton, circleButton, randomButton, clearButton;
    public int columns, rows, canvasBorder, distanceCells, cellSize;
    public byte[][] board, cleanBoard;
    private int currentX, currentY, oldX, oldY;
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

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
        });


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

        drawer();
        drawer();
        drawer();




        for (int j = 0; j > CanvasId.getWidth(); j++) {
              drawer();

              gc.stroke();
            }


    }

    public void drawer(){
        gc.strokeRect(10, 10, 10, 10);

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


        GraphicsContext gc =
                CanvasId.getGraphicsContext2D();
        draw( gc );
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

    @FXML
    private void drawCanvas(ActionEvent event) {
        gc.setFill(Color.AQUA);
        gc.fillRect(10,10,100,100);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        System.out.println("color set to black");
        gc.fillRect(50, 50, 100, 100);
        System.out.println("draw rectangle");
    }




    /**
     * Drawing the shapes
     */
    private void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(50, 100, 200, 200);
        gc.setFill(Color.RED);
        gc.fillRect(300, 100, 200, 200);
    }


}






