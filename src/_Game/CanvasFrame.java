package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

/**
 * The Game Of Life program created for HIOA final project
 * The CanvasFrame class is drawing defined properties on canvas.
 *
 * @author  Sivert Allergodt Borgeteien & Gajaen Chandrasegaram
 * Studentnr : S315325 & S315285
 * @version 1.0
 * @since   2017-01-14
 */

public class CanvasFrame  {

    private GraphicsContext gc;
    private Color lineColor, backgroundColor;
    private Board board;
    public DynamicBoard dynamicBoard;
    private Timeline timeline;
    private double lineWidth;
    private int HEIGHT, WIDTH, TIME, FPS;

    /**
     *
     *  Constructs and init a canvas with width, height and gc
     *
     *  @param height is the first parameter in CanvasFrame constructor
     *  @param width is the second parameter in CanvasFrame constructor
     *  @param gcContext is the third parameter used for drawing
     */


    public CanvasFrame(int height, int width, GraphicsContext gcContext){



        this.HEIGHT = height;
        this.WIDTH = width;
        this.gc = gcContext;
        lineWidth = 0.3;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;

        board = new Board(new byte [this.WIDTH] [this.HEIGHT], this.WIDTH, this.HEIGHT);
        board.setBoard(new byte[getHEIGHT()][getWIDTH()]);

        setGc(this.gc);
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);

        clearArray();

        board.dboard();







    }

    public void clickNoise(){
        String musicFile = "sound.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }

    public void key(KeyEvent event){
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
     * @param colorPicker is choosing color
     */

    public void colorPicker(ColorPicker colorPicker){
        board.setCellColor(colorPicker.getValue());
        clearCanvas();
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);
    }

    public void cellSize(int size){
        board.setCellSize(size);
    }

    /**
     * Clearing the current canvas and applying background
     */

    public void clearCanvas() {
        gc.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);

    }

    /**
     * Clear the current array with only 0's
     */

    public void clearArray(){
        board.cleanArray();
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);
    }

    /**
     * Timeline
     */

    public Timeline SetTimeline() {

        TIME = 1000/getFPS();

        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            clickNoise();
            clearCanvas();
            board.nextGeneration();
            board.drawCells(this.gc);
            board.drawLines(this.gc, this.lineWidth,this.lineColor);
            timeline.playFromStart();

        }));

        return timeline;
    }

    /**
     * Create's a new random array
     */

    public void RandomButtonAction() {
        clearCanvas();
        for (int i = 0; i < this.getHEIGHT(); i++) {
            for (int j = 0; j < this.getWIDTH(); j++) {

                board.setBoardRandom(i,j);
            }
        }
        board.drawCells(this.gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);
    }

    /**
     * This method is used to draw when clicked on canvas.
     *
     * @return Nothing.
     * @param a is getting mouse clicked input from the user.
     * @exception Exception On input error.
     * @see Exception
     */
    public void CanvasPressed(MouseEvent a) throws Exception {
        clearCanvas();
        board.CanvasPressed(a);
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    /**
     * Draw's the pattern from ReadGameBoard
     * @param pattern is drawing on the booard
     */

    public void drawPattern(int [][] pattern){
        clearArray();
        clearCanvas();
        board.drawLines(this.gc, this.lineWidth,this.lineColor);
        board.drawPattern(pattern,gc);
    }

    /**
     * Just a combonation of drawLines and drawCells
     */

    public void drawCanvas(){
        clearCanvas();
        board.drawCells(gc);
        board.drawLines(gc, lineWidth, lineColor);
    }

    public int getFPS() {
        if (FPS == 0){
            FPS = 5;
        }
        return FPS;
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

    public void moveCellsUp(KeyEvent e){
        clickNoise();
        board.moveCellsUp();
    }
    public void moveCellsLeft(){
        clickNoise();
        board.moveCellsLeft();
    }
    public void moveCellsRight(){
        clickNoise();
        board.moveCellsRight();}
    public void moveCellsDown(){
        clickNoise();
        board.moveCellsDown();}

}




