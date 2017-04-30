package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.util.*;


/**
 * The Game Of Life program created for HIOA final project
 * The CanvasFrame class is drawing defined properties on canvas.
 *
 * @author Sivert Allergodt Borgeteien & Gajaen Chandrasegaram
 *         Studentnr : S315325 & S315285
 * @version 1.0
 * @since 2017-01-14
 */

public class CanvasFrame {

    private GraphicsContext gc;
    private Color lineColor, backgroundColor;
    private StaticBoard sBoard;
    public DynamicBoard dBoard;
    private DrawCanvas drawCanvas;
    private Timeline timeline;
    private Cell cell;
    private double lineWidth;
    private int HEIGHT, WIDTH, TIME, FPS;

    /**
     * Constructs and init a canvas with width, height and gc
     *
     * @param height    is the first parameter in CanvasFrame constructor
     * @param width     is the second parameter in CanvasFrame constructor
     * @param gcContext is the third parameter used for drawing
     */


    public CanvasFrame(int height, int width, GraphicsContext gcContext) {

        this.HEIGHT = height;
        this.WIDTH = width;
        this.gc = gcContext;
        lineWidth = 0.3;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;

        sBoard = new StaticBoard(new byte[this.WIDTH][this.HEIGHT], this.WIDTH, this.HEIGHT);
        sBoard.setBoard(new byte[getHEIGHT()][getWIDTH()]);
        cell = new Cell();
        drawCanvas = new DrawCanvas();


        setGc(this.gc);
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);

        clearArray();
        dBoard = new DynamicBoard(height, width, sBoard.getBoard());

        dboard();
    }


    public void clickNoise() {
        String musicFile = "sound.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }


    public void dboard() {
        dBoard.Dynamic();

        //    for(byte[] inner : sBoard.getBoard())
        //        System.out.println(Arrays.toString(inner));


    }

    public void key(KeyEvent event) {
        {
            switch (event.getCode()) {
                case UP:
                    moveCellsUp(event);
                    break;
                case DOWN:
                    moveCellsDown();
                    break;
                case LEFT:
                    moveCellsLeft();
                    break;
                case RIGHT:

                    moveCellsRight();
                    break;
            }
            drawCanvas();
        }
    }

    /**
     * Changing the cell color depending on user input from colorPicker
     *
     * @param colorPicker is choosing color
     */

    public void colorPicker(ColorPicker colorPicker) {
        cell.setCellColor(colorPicker.getValue());
        drawCanvas.clearCanvas();
        drawCanvas.drawCells(gc);
        drawCanvas.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    public void cellSize(double size) {
        cell.setCellSize(size);
    }

    /**
     * Clearing the current canvas and applying background
     */


    /**
     * Clear the current array with only 0's
     */

    public void clearArray() {
        sBoard.cleanArray();
        drawCanvas.drawCells(gc);
        drawCanvas.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    /**
     * Timeline
     */

    public Timeline SetTimeline() {

        TIME = 1000 / getFPS();

        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            clickNoise();
            clearCanvas();
            sBoard.nextGeneration();
            drawCanvas.drawCells(this.gc);
            drawCanvas.drawLines(this.gc, this.lineWidth, this.lineColor);
            timeline.playFromStart();
        }));

        return timeline;
    }

    public int getFPS() {
        if (FPS == 0) {
            FPS = 30;
        }
        return FPS;
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);

    }


    /**
     * Create's a new random array
     */

    public void RandomButtonAction() {
        clearCanvas();
        for (int i = 0; i < this.getHEIGHT(); i++) {
            for (int j = 0; j < this.getWIDTH(); j++) {

                sBoard.setBoardRandom(i, j);
            }
        }
        drawCanvas.drawCells(this.gc);
        drawCanvas.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    /**
     * This method is used to draw when clicked on canvas.
     *
     * @param a is getting mouse clicked input from the user.
     * @return Nothing.
     * @throws Exception On input error.
     * @see Exception
     */
    public void CanvasPressed(MouseEvent a) throws Exception {
        clearCanvas();
        sBoard.CanvasPressed(a);
        drawCanvas.drawCells(gc);
        drawCanvas.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    /**
     * Draw's the pattern from ReadGameBoard
     *
     * @param pattern is drawing on the booard
     */

    public void drawPattern(int[][] pattern) {
        clearArray();
        clearCanvas();

        drawCanvas.drawPattern(pattern, gc);
        drawCanvas.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    /**
     * Just a combonation of drawLines and drawCells
     */

    public void drawCanvas() {
        clearCanvas();
        drawCanvas.drawCells(gc);
        drawCanvas.drawLines(gc, lineWidth, lineColor);

    }


    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }


    public void moveCellsUp(KeyEvent e) {
        clickNoise();
        moveCellsUp();
    }

    public void moveCellsLeft(KeyEvent e) {
        clickNoise();
        moveCellsLeft();
    }

    public void moveCellsRight(KeyEvent e) {
        clickNoise();
        moveCellsRight();
    }

    public void moveCellsDown(KeyEvent e) {
        clickNoise();
        moveCellsDown();
    }


//*********************************************************************************************************************
//*********************************************************************************************************************

    public void moveCellsUp() {

        byte[][] upBoard = new byte[sBoard.canvasHeight][sBoard.canvasWidth];

        for (int x = 1; x < sBoard.canvasHeight - 1; x++) {
            for (int y = 1; y < sBoard.canvasWidth - 1; y++) {
                if ((sBoard.getsBoard()[x][y] == 1)) upBoard[x - 1][y] = 1;
            }
        }
        sBoard.setsBoard() = upBoard;
    }

    public void moveCellsLeft() {
        byte[][] leftBoard = new byte[sBoard.canvasHeight][sBoard.canvasWidth];

        for (int x = 1; x < sBoard.canvasHeight - 1; x++) {
            for (int y = 1; y < sBoard.canvasWidth - 1; y++) {
                if ((sBoard.getsBoard()[x][y] == 1)) leftBoard[x][y - 1] = 1;

            }
        }
        sBoard.setsBoard() = leftBoard;
    }

    public void moveCellsDown() {
        byte[][] downBoard = new byte[sBoard.canvasHeight][sBoard.canvasWidth];

        for (int x = 0; x < sBoard.canvasHeight; x++) {
            for (int y = 0; y < sBoard.canvasWidth; y++) {
                if ((sBoard.getsBoard()[x][y] == 1)) downBoard[x + 1][y] = 1;
            }
        }
        sBoard.setsBoard() = downBoard;
    }

    public void moveCellsRight() {
        byte[][] rightBoard = new byte[sBoard.canvasHeight][sBoard.canvasWidth];

        for (int x = 1; x < sBoard.canvasHeight - 1; x++) {
            for (int y = 1; y < sBoard.canvasWidth - 1; y++) {
                if ((sBoard.getsBoard()[x][y]] == 1)) rightBoard[x][y + 1] = 1;
            }
        }
        sBoard.setsBoard()  = rightBoard;
    }
}













