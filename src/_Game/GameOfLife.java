package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GameOfLife {
    public Stage stage;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public int cellSize, TIME, cellGap, HEIGHT, WIDTH, oldJ, oldI;
    public double lineWidth;
    public int[][] board, cleanBoard;
    public Color cellColor, lineColor, backgroundColor;
    public Timeline timeline;

    public int gen = 0;
    public int FPS = 120;


    public void cleanBoard() {
        gc.clearRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

    }


    public void initialize() {

        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());

        //Variabler til spillbrettet
        cellSize = 10;
        cellGap = 1;
        lineWidth = 0.4;

        HEIGHT = ((int) CanvasId.getHeight());
        WIDTH = ((int) CanvasId.getWidth());

        board = new int[HEIGHT][WIDTH];
        cleanBoard = new int[HEIGHT][WIDTH];

        System.out.println("CanvasHeight = " + (int) CanvasId.getHeight());
        System.out.println("CanvasWidth = " + (int) CanvasId.getWidth());
        System.out.println("Current FPS = " + FPS);

        //FARGER
        cellColor = Color.ALICEBLUE;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;

        drawCells();
        drawLines();
        Timeline();
    }


    public void CanvasPressed(MouseEvent a) {

        int j = ((int) a.getX() / cellSize) + 1;
        int i = ((int) a.getY() / cellSize) + 1;

        if( j != oldJ || i != oldI ) {

            if (board[i][j] == 0) {
                board[i][j] = 1;
            }
        }
        oldJ = j;
        oldI = i;

        drawCells();
        drawLines();

    }


    public void nextGeneration() {
        cleanBoard();
        //System.out.println("Generation = " + gen);
        gen++;
        int[][] nextBoard = new int[HEIGHT][WIDTH];

        for (int x = 1; x < HEIGHT - 1; x++) {
            for (int y = 1; y < WIDTH - 1; y++)

            {
                int cellNeighbors = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        cellNeighbors += board[x + i][y + j];
                    }
                }

                cellNeighbors -= board[x][y];
                if ((board[x][y] == 1) && (cellNeighbors < 2)) nextBoard[x][y] = 0;           // Mindre enn 2 rundt
                else if ((board[x][y] == 1) && (cellNeighbors > 3))
                    nextBoard[x][y] = 0;           // Fler enn 3 rundt seg
                else if ((board[x][y] == 0) && (cellNeighbors == 3))
                    nextBoard[x][y] = 1;           // Akkurat 3 rundt seg
                else nextBoard[x][y] = board[x][y];
            }
        }
        board = nextBoard;

        drawCells();
        drawLines();
        Timeline();
    }


    public void drawCells() {

        cleanBoard();
        gc = CanvasId.getGraphicsContext2D();
        gc.setFill(cellColor);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == 1) {
                    gc.fillRect(cellSize * j - cellSize, cellSize * i - cellSize, cellSize - cellGap, cellSize - cellGap);}
            }
        }
    }

    public void drawLines() {
        gc.setStroke(lineColor);
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, CanvasId.getWidth(), CanvasId.getHeight());
        gc.setLineWidth(lineWidth);

        int a = cellSize;
        int b = cellSize;

        for (int i = 0; i < HEIGHT; i++) {
            gc.strokeLine(0, a, CanvasId.getWidth(), a);
            a += cellSize;
        }
        for (int i = 0; i < WIDTH; i++) {
            gc.strokeLine(b, 0, b, CanvasId.getHeight());
            b += cellSize;
        }
    }

    public void Timeline() {
        FPSClicked();
        TIME = 1000 / FPS;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            nextGeneration();
            timeline.playFromStart();

        }));

    }



