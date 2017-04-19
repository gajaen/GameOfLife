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
    private double lineWidth;
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
        lineWidth = 0.5;
        lineColor = Color.BLACK;
        backgroundColor = Color.GREY;





        board = new Board(new int [this.WIDTH] [this.HEIGHT], this.WIDTH, this.HEIGHT);

        board.setBoard(new int[getHEIGHT()][getWIDTH()]);
        setGc(this.gc);

        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);


        clearArray();

        System.out.println(getFPS());


        FPS = getFPS();


    }


    public void colorPicker(ColorPicker colorPicker){
        System.out.println(newColor + " 2");
        board.setCellColor(colorPicker.getValue());
        clearCanvas();
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);
    }




    public void clearCanvas() {

        gc.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        gc.setFill(getBackgroundColor());
        gc.fillRect(0, 0, this.WIDTH, this.HEIGHT);
    }


    public void clearArray(){
        board.cleanArray();
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);

    }


    public Timeline SetTimeline() {

        TIME = 1000 / getFPS();

        timeline = new Timeline(new KeyFrame(Duration.millis(TIME), e -> {
            clearCanvas();

            board.nextGeneration();

            board.drawCells(this.gc);
            board.drawLines(this.gc, this.lineWidth,this.lineColor);

            SetTimeline();
            timeline.playFromStart();

        }));

        return timeline;
    }

    public void RandomButtonAction() {
        clearCanvas();
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < this.getHEIGHT(); i++) {
            for (int j = 0; j < this.getWIDTH(); j++) {

                board.setBoardRandom(i,j);
            }
        }
        board.drawCells(this.gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);



    }

    public void CanvasPressed(MouseEvent a) {

        clearCanvas();
        board.CanvasPressed(a);
        board.drawCells(gc);
        board.drawLines(this.gc, this.lineWidth,this.lineColor);
        timeline.stop();
    }




    public void boardopen(){

        board.opened();

    }

    public void setCellColor(Color color){
        this.board.setCellColor(color);
    }


    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }


    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public GraphicsContext getGc() {
        return gc;
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




