package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import javax.swing.*;




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
    private Timeline timeline;
    private double lineWidth;
    private int HEIGHT, WIDTH, TIME, FPS;

    public CanvasFrame(int height, int width, GraphicsContext gccontext) {

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


        /*gc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveCell(evt);
            }
        });
        */



    }

    /**
     * The method for colorPicker
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

    public void CanvasPressed(MouseEvent a) throws Exception {
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

    public int getFPS() {
        if (FPS == 0){
            FPS = 30;
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

    public void moveCell(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                System.out.println("down");
                break;

            case KeyEvent.VK_UP:
                System.out.println("down");
                break;

            case KeyEvent.VK_LEFT:
                System.out.println("down");
                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("down");
                break;
        }

    }}




