package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;



public class CanvasFrame  {

    private GraphicsContext gc;
    private int HEIGHT;
    private int WIDTH;
    public double lineWidth;
    private Color lineColor;
    private Color backgroundColor;
    private Board board;
    private Timeline timeline;
    private GUI gui;
    public Controller controller;
    int TIME;
    private Cell cell;
    public int FPS;
    public Color newColor;


    public CanvasFrame(int height, int width, GraphicsContext gccontext){

        this.HEIGHT = height;
        this.WIDTH = width;
        this.gc = gccontext;
        lineWidth = 0.3;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;


        board = new Board(new int [this.WIDTH] [this.HEIGHT], this.WIDTH, this.HEIGHT);

        board.setBoard(new int[getHEIGHT()][getWIDTH()]);
        setGc(this.gc);

        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);


        clearArray();

    }

    /**
     * The method for colorPicker
     */

    public void colorPicker(ColorPicker colorPicker){
        System.out.println(newColor + " 2");
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
     *
     * Timeline
     */

    public Timeline SetTimeline() {
        TIME = 1000/getFPS();

        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
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


    public void CanvasPressed(MouseEvent a) throws ArrayIndexOutOfBoundsException {
        clearCanvas();
        board.CanvasPressed(a);
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth, this.lineColor);
        timeline.stop();
    }


    /**
     * Draw's the pattern from ReadGameBoard
     * @param pattern
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

    public int getFPS() {
        if (FPS == 0){
            FPS = 30;
        }
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }



}




