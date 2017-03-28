package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GameOfLife {
    public Stage stage;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public Button startButton, stopButton, randomButton, clearButton;
    public int cellSize, TIME, cellGap, HEIGHT, WIDTH, oldJ, oldI;
    public double lineWidth;
    public int[][] board, cleanBoard;
    public Color cellColor, lineColor, backgroundColor;
    public Slider cellSlider, sliderFPS;
    public Timeline timeline;
    public ColorPicker colorPicker;

    public int gen = 0;
    public int FPS = 120;

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

        CanvasFrame drawCell = new CanvasFrame();

        drawCell.drawCells();
        drawCell.drawLines();
        Timeline();
    }


    public void Timeline() {
        TIME = 1000 / FPS;
        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            CanvasFrame drawCell = new CanvasFrame();

            drawCell.nextGeneration();
            timeline.playFromStart();

        }));

    }

}



