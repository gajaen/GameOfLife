package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class GameOfLife implements Initializable{
    public Stage stage;
    public Canvas CanvasId;
    public GraphicsContext gc;
    public int cellSize, TIME, cellGap, HEIGHT, WIDTH;
    public double lineWidth;
    public int[][] board, cleanBoard;
    public Color cellColor, lineColor, backgroundColor;
    public Timeline timeline;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}



