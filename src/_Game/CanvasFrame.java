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
import java.applet.Applet;
import java.io.File;
import java.util.*;


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
    private StaticBoard staticBoard;
    public DynamicBoard dynamicBoard;
    private DrawCanvas drawCanvas;
    private Cell cell;
    private Timeline timeline;
    private double lineWidth;
    private int canvasWidth, canvasHeight, TIME, FPS;
    Thread thread;
    public List<List<Byte>> dynamic;

    /**
     *
     *  Constructs and init a canvas with width, height and gc
     *
     *  @param height is the first parameter in CanvasFrame constructor
     *  @param width is the second parameter in CanvasFrame constructor
     *  @param gcContext is the third parameter used for drawing
     */


    public CanvasFrame(int height, int width, GraphicsContext gcContext){


        dynamic = new ArrayList<List<Byte>>();

        this.canvasHeight = height;
        this.canvasWidth = width;
        this.gc = gcContext;
        lineWidth = 0.3;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;

        staticBoard = new StaticBoard(new byte [this.canvasWidth] [this.canvasHeight], this.canvasWidth, this.canvasHeight);
        staticBoard.setBoard(new byte[canvasHeight][canvasWidth]);

        drawCanvas = new DrawCanvas(this.canvasHeight, this.canvasWidth, staticBoard.getBoard());


        dynamicBoard = new DynamicBoard(this.canvasHeight,this.canvasWidth,dynamic);
        //dboard.setdBoard();

        setGc(this.gc);
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
       // dynamicBoard.setCellSize(canvasWidth/dynamicBoard.dynamicBoard.size());

        clearArray();
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
                    dynamicBoard.moveCellsUp();
                    break;
                case DOWN:
                    dynamicBoard.moveCellsDown();
                    break;
                case LEFT:
                    dynamicBoard.moveCellsLeft();
                    break;
                case RIGHT:

                    dynamicBoard.moveCellsRight();
                    break;
            }
         //   dynamicBoard.drawCells(gc);
        //    drawCanvas();

        }
    }

    /**
     * Changing the cell color depending on user input from colorPicker
     * @param colorPicker is choosing color
     */

    public void colorPicker(ColorPicker colorPicker){
        dynamicBoard.setCellColor(colorPicker.getValue());
        clearCanvas();
        dynamicBoard.drawCells(gc);
        dynamicBoard.drawLines(this.gc, this.lineWidth,this.lineColor);
    }

    public void cellSize(double size){
       dynamicBoard.setCellSize(size);
    }

    /**
     * Clearing the current canvas and applying background
     */

    public void clearCanvas() {

        gc.clearRect(0, 0, this.canvasWidth, this.canvasHeight);
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, this.canvasWidth, this.canvasHeight);

    }

    /**
     * Clear the current array with only 0's
     */

    public void clearArray(){
        drawCanvas = new DrawCanvas(canvasHeight, canvasWidth, staticBoard.getBoard());
        dynamicBoard.cleanArray();

        dynamicBoard.drawCells(gc);
        dynamicBoard.drawLines(this.gc, this.lineWidth,this.lineColor);
    }

    /**
     * Timeline
     */

     public Timeline SetTimeline() {

        TIME = 1000/getFPS();

        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {

            clickNoise();
            clearCanvas();
            dynamicBoard.nextGeneration();

            try{Thread.sleep(100);} catch (Exception a){}

            dynamicBoard.drawCells(this.gc);
            dynamicBoard.drawLines(this.gc, this.lineWidth,this.lineColor);
            timeline.playFromStart();


        }));



        return timeline;
    }

    public int getFPS() {
        if (FPS == 0){
            FPS = 30;
        }
        return FPS;
    }




    /**
     * Create's a new random array
     */

    public void RandomButtonAction() {
        /*clearCanvas();
        for (int i = 0; i < this.getHEIGHT(); i++) {
            for (int j = 0; j < this.getWIDTH(); j++) {

                staticBoard.setBoardRandom(i,j);
            }
        }*/


        //drawCanvas.drawCells(this.gc);
        clearCanvas();


        dynamicBoard.randomButton();

        dynamicBoard.drawCells(gc);
        dynamicBoard.drawLines(gc,lineWidth,lineColor);

        System.out.println("test 1");


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
        dynamicBoard.CanvasPressed(a);
        dynamicBoard.drawCells(gc);
        dynamicBoard.drawLines(this.gc, this.lineWidth, this.lineColor);
    }

    /**
     * Draw's the pattern from ReadGameBoard
     * @param pattern is drawing on the booard
     */

    public void drawPattern(int [][] pattern){
        clearArray();
        drawCanvas();
        dynamicBoard.drawPattern(pattern,gc);
        dynamicBoard.drawCells(gc);

    }

    /**
     * Just a combonation of drawLines and drawCells
     */

    public void drawCanvas(){
        drawCanvas = new DrawCanvas(canvasHeight, canvasWidth, staticBoard.getBoard());
        clearCanvas();
        drawCanvas.drawCells(gc);
        drawCanvas.drawLines(gc, lineWidth, lineColor);

    }

    public byte[][] getBoard() {
        return drawCanvas.staticBoard.getBoard();
    }






    public int getHEIGHT() {
        return canvasHeight;
    }

    public int getWIDTH() {
        return canvasWidth;
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


    public void moveCellsUp(){
        clickNoise();
        dynamicBoard.moveCellsUp();

    }

    public void moveCellsLeft(){
        clickNoise();
        dynamicBoard.moveCellsLeft();
    }

    public void moveCellsRight(){
        clickNoise();
        dynamicBoard.moveCellsRight();
    }

    public void moveCellsDown(){
        clickNoise();
        dynamicBoard.moveCellsDown();
    }

    public void start(){
        thread = new Thread();
        thread.start();


    }



    public void run() {

            while(true){
                staticBoard.nextGeneration();
                drawCanvas.drawCells(gc);
            }


            /*try  {

               // Thread.sleep(15);

              /*  TIME = 1000/getFPS();

                timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
                    drawCanvas = new DrawCanvas(canvasHeight, canvasWidth, staticBoard.getBoard());
                    clickNoise();
                    clearCanvas();
                    staticBoard.nextGeneration();
                    drawCanvas.drawCells(this.gc);
                    drawCanvas.drawLines(this.gc, this.lineWidth,this.lineColor);
                    timeline.playFromStart();

            System.out.println("Utskrift av statisk 2D tabell:");
            for(byte[] inner : staticBoard.getBoard())
                System.out.println(Arrays.toString(inner));

                }));

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }*/

        }





}
